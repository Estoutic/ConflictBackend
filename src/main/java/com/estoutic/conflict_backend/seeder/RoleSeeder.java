package com.estoutic.conflict_backend.seeder;

import com.estoutic.conflict_backend.database.enitities.Role;
import com.estoutic.conflict_backend.database.repostiories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationRunner {

    private final RoleRepository roleRepository;

    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("role")) {
                seedRoles();
                log.info("Success run role seeder");
            }
        }else{
            log.info("Role seeder skipped");
        }
        }
    private void seedRoles(){
        List<String> roles = new ArrayList<>();

        roles.add("User");
        roles.add("Admin");

        for (var role : roles ) {
            if (!roleRepository.existsByRoleName(role)) {
                Role r = Role.builder()
                        .roleName(role)
                        .build();

                this.roleRepository.save(r);

                log.info("Success run RoleSeeder {}", role);
            }

        }
    }
}
