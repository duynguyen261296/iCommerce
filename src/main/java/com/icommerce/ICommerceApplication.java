package com.icommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ICommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ICommerceApplication.class, args);
    }


//    @Bean
//    CommandLineRunner initDatabase(UserRepository userRepository, UserRoleRepository userRoleRepository) {
//        return args -> {
//            List<UserRole> userRoleList = userRoleRepository.findAll();
//            userRepository.findAll().forEach(user -> {
//                if ("user_1".equals(user.getUsername()) || "user_2".equals(user.getUsername())) {
//                    user.setUserRole(userRoleList.stream()
//                            .filter(userRole -> Role.USER.equals(userRole.getRole()))
//                            .collect(Collectors.toSet()));
//                } else {
//                    user.setUserRole(userRoleList.stream()
//                            .filter(userRole -> Role.ADMIN.equals(userRole.getRole()))
//                            .collect(Collectors.toSet()));
//                }
//            });
//        };
//    }
}
