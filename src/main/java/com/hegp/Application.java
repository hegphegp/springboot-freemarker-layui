package com.hegp;

import com.github.wenhao.jpa.Specifications;
import com.hegp.entity.UserEntity;
import com.hegp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRoleRelRepository userRoleRelRepository;
    @Autowired
    private RoleResourceRelRepository roleResourceRelRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setDel(false);
        userEntity.setPhone("phone");
        userEntity.setNickname("nickname");
        userEntity.setUsername("username");
        userRepository.save(userEntity);
        // JPA世纪巨坑的方法,getOne(String id)不打印SQL语句,执行getOne(String id)居然不查数据库,此时任何的解释的都是苍白的,通过这个方法,决不可以相信官方
        userRepository.getOne(userEntity.getId());
        userRepository.findById("id");
        testUser();
//        userRepository.f
//        UserEntity userEntity = userRepository.findById("id").get();
    }

    private void testUser() {
        Specification<UserEntity> specification = Specifications.<UserEntity>and()
                .like("username", "%a%")
                .like("phone", "%%a")
                .like("nickname", "%a%")
                .build();
        List<UserEntity> users = userRepository.findAll(specification);

        String usernameCondition = "";
        String condition = "";
        specification = Specifications.<UserEntity>and()
                .like(StringUtils.hasText(usernameCondition), "username", "%"+usernameCondition+"%")
                .predicate(StringUtils.hasText(condition), Specifications.or()
                        .like("phone", "%"+condition+"%")
                        .like("nickname", "%"+condition+"%")
                        .build())
                .build();
        users = userRepository.findAll(specification);

        specification = Specifications.<UserEntity>and()
                .like("username", "%a%")
                .predicate(Specifications.or()
                        .like("phone", "%a%")
                        .like("nickname", "%a%")
                        .build())
                .build();
        users = userRepository.findAll(specification);
    }
}
