package com.vypnetsolutions.authentication.tariffdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class TariffDataInitializer implements CommandLineRunner {
    // @Autowired
    // private UserRepository userRepository;

    // @Autowired
    // private TariffRepository tariffRepository;

    // @Autowired
    // private UserTariffDetailRepository userTariffDetailRepository;

    // @Autowired
    // private TariffDetailRepository tariffDetailRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Optional<User> userOptional = userRepository.findById(1L);
        // if (!userOptional.isPresent()) {
        //     throw new RuntimeException("User not found with id: " + 1);
        // }

        // Optional<Tariff> tariffOptional = tariffRepository.findById(2L);
        // if (!tariffOptional.isPresent()) {
        //     throw new RuntimeException("Tariff not found with id: " + 2);
        // }
        // UserTariffDetail userTariffDetail = userTariffDetailRepository.findByUserId(2L)
        //         .orElse(new UserTariffDetail());
                
        // User user = userOptional.get();
        // Tariff tariff = tariffOptional.get();

        // userTariffDetail.setUser(user);
        // userTariffDetail.setTariff(tariff);

        // userTariffDetailRepository.save(userTariffDetail);
        // User user = userRepository.findByUsername("username")
        // .orElseThrow(() -> new UsernameNotFoundException("User Not Found with
        // username: username"));

        // Tariff simpleTariff = new Tariff();
        // simpleTariff.setType(TariffType.SIMPLE);
        // tariffRepository.save(simpleTariff);

        // TariffDetail simpleDetail1 = new TariffDetail();
        // simpleDetail1.setPeriod("1-100 kWh");
        // simpleDetail1.setPricePerKwh(6.483);
        // simpleDetail1.setTariff(simpleTariff);
        // simpleTariff.setDetails(simpleDetail1);
        // tariffDetailRepository.save(simpleDetail1);

        // TariffDetail simpleDetail2 = new TariffDetail();
        // simpleDetail2.setPeriod("101-600 kWh");
        // simpleDetail2.setPricePerKwh(8.126);
        // simpleDetail2.setTariff(simpleTariff);
        // simpleTariff.setDetails(simpleDetail2);
        // tariffDetailRepository.save(simpleDetail2);

        // Optional<Tariff> simpleTariff =
        // tariffRepository.findByType(TariffType.SIMPLE);
        // if (!simpleTariff.isPresent()) {
        // throw new RuntimeException("Tariff not found with type: " +
        // TariffType.SIMPLE);
        // }
        // Tariff simpleTariffConfirmed = simpleTariff.get();
        // TariffDetail simpleDetail3 = new TariffDetail();
        // simpleDetail3.setPeriod("601+ kWh");
        // simpleDetail3.setPricePerKwh(10.132);
        // simpleDetail3.setTariff(simpleTariffConfirmed);
        // simpleTariffConfirmed.setDetails(simpleDetail3);
        // tariffDetailRepository.save(simpleDetail3);

        // Seguir acá
        // *******************************************************************************************************************************************

        /*
         * Optional<Tariff> simpleTariff = tariffRepository.findById(2L);
         * if (!simpleTariff.isPresent()) {
         * throw new RuntimeException("Tariff not found with ID: " + 2L);
         * }
         * 
         * Tariff simpleTariffConfirmed = simpleTariff.get();
         * Hibernate.initialize(simpleTariffConfirmed.getDetails());
         * 
         * // Crear un nuevo detalle de tarifa
         * TariffDetail simpleDetail3 = new TariffDetail();
         * simpleDetail3.setPeriod("601+ kWh");
         * simpleDetail3.setPricePerKwh(10.132);
         * simpleDetail3.setTariff(simpleTariffConfirmed);
         * 
         * // Agregar el detalle a la tarifa
         * simpleTariffConfirmed.addDetail(simpleDetail3);
         * 
         * // Guardar el detalle de tarifa
         * tariffDetailRepository.save(simpleDetail3);
         * 
         * // Guardar la tarifa actualizada (esto es opcional ya que cascade =
         * // CascadeType.ALL se encargará de esto)
         * tariffRepository.save(simpleTariffConfirmed);
         */
        /*
         * Tariff doubleHourlyTariff = new Tariff();
         * doubleHourlyTariff.setType(TariffType.DOUBLE_HOURLY);
         * tariffRepository.save(doubleHourlyTariff);
         * 
         * TariffDetail doubleHourlyDetail1 = new TariffDetail();
         * doubleHourlyDetail1.setPeriod("Horario Punta");
         * doubleHourlyDetail1.setPricePerKwh(11.032);
         * doubleHourlyDetail1.setTariff(doubleHourlyTariff);
         * doubleHourlyTariff.setDetails(doubleHourlyDetail1);
         * tariffDetailRepository.save(doubleHourlyDetail1);
         * 
         * TariffDetail doubleHourlyDetail2 = new TariffDetail();
         * doubleHourlyDetail2.setPeriod("Horario Fuera de Punta");
         * doubleHourlyDetail2.setPricePerKwh(4.422);
         * doubleHourlyDetail2.setTariff(doubleHourlyTariff);
         * doubleHourlyTariff.setDetails(doubleHourlyDetail2);
         * tariffDetailRepository.save(doubleHourlyDetail2);
         * 
         * tariffRepository.save(doubleHourlyTariff);
         * 
         * Tariff tripleHourlyTariff = new Tariff();
         * tripleHourlyTariff.setType(TariffType.TRIPLE_HOURLY);
         * tariffRepository.save(tripleHourlyTariff);
         * 
         * TariffDetail tripleHourlyDetail1 = new TariffDetail();
         * tripleHourlyDetail1.setPeriod("Horario Punta");
         * tripleHourlyDetail1.setPricePerKwh(11.032);
         * tripleHourlyDetail1.setTariff(tripleHourlyTariff);
         * tripleHourlyTariff.setDetails(tripleHourlyDetail1);
         * tariffDetailRepository.save(tripleHourlyDetail1);
         * 
         * TariffDetail tripleHourlyDetail2 = new TariffDetail();
         * tripleHourlyDetail2.setPeriod("Horario Valle");
         * tripleHourlyDetail2.setPricePerKwh(2.298);
         * tripleHourlyDetail2.setTariff(tripleHourlyTariff);
         * tripleHourlyTariff.setDetails(tripleHourlyDetail2);
         * tariffDetailRepository.save(tripleHourlyDetail2);
         * 
         * TariffDetail tripleHourlyDetail3 = new TariffDetail();
         * tripleHourlyDetail3.setPeriod("Horario Llano");
         * tripleHourlyDetail3.setPricePerKwh(5.036);
         * tripleHourlyDetail3.setTariff(tripleHourlyTariff);
         * tripleHourlyTariff.setDetails(tripleHourlyDetail3);
         * tariffDetailRepository.save(tripleHourlyDetail3);
         * 
         * tariffRepository.save(tripleHourlyTariff);
         */
    }
}