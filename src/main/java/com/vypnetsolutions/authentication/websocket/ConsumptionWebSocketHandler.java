package com.vypnetsolutions.authentication.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumptionWebSocketHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>(); // sessionId como clave
    private static final Map<Long, Set<String>> userSessionIds = new ConcurrentHashMap<>(); // userId mapea a un set de
                                                                                            // sessionIds

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<Long, Set<String>> getUserSessionIds() {
        return userSessionIds;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        JsonNode jsonNode = objectMapper.readTree(payload);

        // Verificar que userId esté presente en el payload
        JsonNode userIdNode = jsonNode.get("userId");
        if (userIdNode == null) {
            System.err.println("User ID not found in message payload");
            return; // Salimos si no se encuentra el userId
        }

        // Verificar el tipo de userId
        if (!userIdNode.isNumber()) {
            System.err.println("User ID is not a valid number in message payload");
            return; // Salimos si no es un número
        }

        Long userId = userIdNode.asLong();
        String sessionId = jsonNode.get("sessionId").asText();
        String action = jsonNode.has("action") ? jsonNode.get("action").asText() : null;

        if (sessionId != null) {
            if ("logout".equals(action)) {
                // Manejo de cierre de sesión
                sessionMap.remove(sessionId); // Eliminar la sesión actual
                Set<String> sessions = userSessionIds.get(userId);
                if (sessions != null) {
                    sessions.remove(sessionId); // Eliminar el sessionId de userId
                    if (sessions.isEmpty()) {
                        userSessionIds.remove(userId); // Eliminar al usuario si no tiene más sesiones
                    }
                }
            } else {
                // Manejo de conexión y registro de la sesión
                sessionMap.put(sessionId, session); // Almacenar la sesión con el sessionId
                userSessionIds.computeIfAbsent(userId, k -> new HashSet<>()).add(sessionId);
            }
        } else {
            System.err.println("User ID or Session ID not found in message payload");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.values().remove(session);
        userSessionIds.forEach((userId, sessions) -> sessions.removeIf(sessionId -> sessionMap.get(sessionId) == null));
    }

    public void broadcastConsumptionData(Map<Long, Double> consumptionDataByUser) throws IOException {
        for (Map.Entry<Long, Double> entry : consumptionDataByUser.entrySet()) {
            Set<String> sessionIds = userSessionIds.get(entry.getKey());
            if (sessionIds != null) {
                for (String sessionId : sessionIds) {
                    WebSocketSession session = sessionMap.get(sessionId);
                    if (session != null && session.isOpen()) {
                        session.sendMessage(new TextMessage(entry.getValue().toString()));
                    } else {
                        System.err.println("Session is null or closed for sessionId: " + sessionId);
                    }
                }
            }
        }
    }
}