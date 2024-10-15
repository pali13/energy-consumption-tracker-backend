package com.vypnetsolutions.authentication.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vypnetsolutions.authentication.entity.Role;
import com.vypnetsolutions.authentication.entity.Role.ERole;
import com.vypnetsolutions.authentication.entity.Tariff;
import com.vypnetsolutions.authentication.entity.User;
import com.vypnetsolutions.authentication.repository.RoleRepository;
import com.vypnetsolutions.authentication.repository.TariffRepository;
import com.vypnetsolutions.authentication.repository.UserRepository;
import com.vypnetsolutions.authentication.repository.UserTariffDetailRepository;
import com.vypnetsolutions.authentication.request.LoginRequest;
import com.vypnetsolutions.authentication.request.SignupRequest;
import com.vypnetsolutions.authentication.response.JwtResponse;
import com.vypnetsolutions.authentication.service.UserService;
import com.vypnetsolutions.authentication.tariffdata.UserTariffDetail;
import com.vypnetsolutions.authentication.user.UserDetailsImpl;
import com.vypnetsolutions.authentication.utlis.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserService userService;

        @Autowired
        private TariffRepository tariffRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private UserTariffDetailRepository userTariffDetailRepository;

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        private PasswordEncoder encoder;

        @Autowired
        private JwtUtils jwtUtils;

        @CrossOrigin(origins = "http://localhost:8081")
        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
                // Verificar si el nombre de usuario ya existe
                if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                        return ResponseEntity
                                        .badRequest()
                                        .body("Error: Username is already taken!");
                }

                // Verificar si el email ya está en uso
                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                        return ResponseEntity
                                        .badRequest()
                                        .body("Error: Email is already in use!");
                }

                // Crear la cuenta de usuario nueva
                User user;
                if (signUpRequest.getBirthDate() != null) {
                        user = new User(signUpRequest.getUsername(),
                                        encoder.encode(signUpRequest.getPassword()),
                                        signUpRequest.getEmail(),
                                        signUpRequest.getBirthDate());
                } else {
                        user = new User(signUpRequest.getUsername(),
                                        encoder.encode(signUpRequest.getPassword()),
                                        signUpRequest.getEmail());
                }

                // Asignar roles al usuario
                Set<Role> roles = new HashSet<>();
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                user.setRoles(roles);

                // Guardar el usuario en la base de datos
                User savedUser = userRepository.save(user);

                // Asignar la tarifa básica por defecto
                Optional<Tariff> tariffOptional = tariffRepository.findById(2L); // Tarifa con ID 2
                if (!tariffOptional.isPresent()) {
                        return ResponseEntity
                                        .badRequest()
                                        .body("Error: Default tariff not found.");
                }

                // Crear y asignar el detalle de la tarifa del usuario
                Tariff defaultTariff = tariffOptional.get();
                UserTariffDetail userTariffDetail = new UserTariffDetail();
                userTariffDetail.setUser(savedUser);
                userTariffDetail.setTariff(defaultTariff);
                userTariffDetail.setPeakStartTime("17:00");
                userTariffDetail.setPeakEndTime("21:00");

                // Guardar el detalle de la tarifa del usuario
                userTariffDetailRepository.save(userTariffDetail);

                return ResponseEntity.ok("User registered successfully with default tariff!");
        }

        @CrossOrigin(origins = "http://localhost:8081")
        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                                .map(item -> item.getAuthority())
                                .collect(Collectors.toList());

                return ResponseEntity.ok(
                                new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                                                userDetails.getEmail(), roles));
        }

        @CrossOrigin(origins = "http://localhost:8081")
        @GetMapping("/{id}")
        public ResponseEntity<?> getUserById(@PathVariable Long id) {
                User user = userService.findById(id)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "User Not Found with username: " + id));

                return ResponseEntity.ok(user);
        }
}
