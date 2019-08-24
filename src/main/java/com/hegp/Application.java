package com.hegp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wenhao.jpa.Specifications;
import com.hegp.core.jpa.SQLRepository;
import com.hegp.core.jpa.service.JPAService;
import com.hegp.entity.UserEntity;
import com.hegp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

//@EnableEurekaClient
@SpringBootApplication
public class Application implements CommandLineRunner {
//    @Autowired
//    private SimpleJpaRepository simpleJpaRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserRoleRelService userRoleRelService;
    @Autowired
    private RoleResourceRelService roleResourceRelService;
    @Autowired
    private SQLRepository sqlRepository;
    @PersistenceContext
    private EntityManager em;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
//    @Transactional
    public void run(String... args) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setDel(false);
        userEntity.setPhone("phone");
        userEntity.setNickname("nickname");
        userEntity.setUsername("username");
        userService.save(userEntity);
//        InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(UserEntity.class).save(userEntity);
        // JPA世纪巨坑的方法,执行getOne(String id)方法居然不查数据库,此时任何的解释的都是苍白的,绝对不可以用这个方法
        // userRepository.getOne(userEntity.getId());
//        testUser();
//        testTest();
    }

    private void testUser() {
        SimpleJpaRepository simpleJpaRepository = new SimpleJpaRepository(UserEntity.class, em);
        UserEntity userEntity = new UserEntity();
        userEntity.setDel(false);
        userEntity.setPhone("phone");
        userEntity.setNickname("nickname");
        userEntity.setUsername("username");
//        simpleJpaRepository.save(userEntity);

        userService.getSimpleJpaRepository().findById("00");
        Map map = JPAService.simpleJpaRepositoryMap;

        Specification<UserEntity> specification = Specifications.<UserEntity>and()
                .like("username", "%a%")
                .like("phone", "%%a")
                .like("nickname", "%a%")
                .build();
        List<UserEntity> users = userService.getSimpleJpaRepository().findAll(specification);

        simpleJpaRepository.findAll(specification);

        String usernameCondition = "";
        String condition = "";
        specification = Specifications.<UserEntity>and()
                .like(StringUtils.hasText(usernameCondition), "username", "%"+usernameCondition+"%")
                .predicate(StringUtils.hasText(condition), Specifications.or()
                        .like("phone", "%"+condition+"%")
                        .like("nickname", "%"+condition+"%")
                        .build())
                .build();
        users = userService.getSimpleJpaRepository().findAll(specification);

        specification = Specifications.<UserEntity>and()
                .in("id", "1","2","3","4")
                .like("username", "%a%")
                .predicate(Specifications.or()
                        .like("phone", "%a%")
                        .like("nickname", "%a%")
                        .build())
                .build();
        users = userService.getSimpleJpaRepository().findAll(specification);
    }

    private ObjectMapper mapper = new ObjectMapper();
    private void testTest() {
        String sql = " SELECT su.id, su.username, su.nickname, su.phone, su.del, sr.id role_id, sr.name role_name FROM sys_user_role_rel surr " +
                     " LEFT JOIN sys_user su ON surr.user_id = su.id " +
                     " LEFT JOIN sys_role sr ON surr.role_id = sr.id " +
                     " WHERE su.del=? ";
        sqlRepository.queryPageResultList(sql,1,1, false);
        try {
            System.out.println(mapper.writeValueAsString(sqlRepository.queryResultList("SELECT id, username FROM sys_user")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        sqlRepository.queryResultCount("SELECT COUNT(id) FROM sys_user");
    }
}

