package com.hegp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wenhao.jpa.Specifications;
import com.hegp.core.jpa.SQLRepository;
import com.hegp.core.utils.sql.SqlUtils;
import com.hegp.entity.UserEntity;
import com.hegp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

//@EnableEurekaClient
@SpringBootApplication
public class Application implements CommandLineRunner {
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
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        insertOne01();
        insertOne02();
        insertOne03();

        insertMany01();

        System.out.println();
//        JpaRepository<SnowflakeTestEntity, Long> repository = new SimpleJpaRepository(SnowflakeTestEntity.class, entityManager);
//        SnowflakeTestEntity entity = new SnowflakeTestEntity();
//        Timestamp now = new Timestamp(System.currentTimeMillis());
//        entity.setCreateAt(now);
//        entity.setUpdateAt(now);
//        repository.save(entity);
//
//        Thread.sleep(100);
//        entity = new SnowflakeTestEntity();
//        now = new Timestamp(System.currentTimeMillis());
//        entity.setCreateAt(now);
//        entity.setUpdateAt(now);
//        repository.save(entity);
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setDel(false);
//        userEntity.setPhone("phone1");
//        userEntity.setNickname("nickname1");
//        userEntity.setUsername("username1");
//        userService.save(userEntity);
//
//        userService.delete(userEntity);
////        InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(UserEntity.class).save(userEntity);
//        // JPA世纪巨坑的方法,执行getOne(String id)方法居然不查数据库,此时任何的解释的都是苍白的,绝对不可以用这个方法
////        userRepository.getOne("0000");
//        testUser();
//        testTest();
//
//        LinkedList list = new LinkedList();
//
//        list.add(1);
//        list.addFirst(2);
//        list.removeLast();
//        list.toArray();
    }

    public void insertOne01() {
        String tableName = "sys_user";
        Map<String,Object> fieldValues = new HashMap();
        fieldValues.put("id", UUID.randomUUID().toString().replace("-", ""));
        fieldValues.put("del", true);
        fieldValues.put("create_at", new Timestamp(System.currentTimeMillis()));
        String sql = SqlUtils.getInsertOneSql(tableName, fieldValues);
        System.out.println(sql);
        namedParameterJdbcTemplate.update(sql, fieldValues);
    }

    public void insertOne02() {
        String tableName = "sys_user";
        Map<String,Object> fieldValues = new HashMap();
        fieldValues.put("id", UUID.randomUUID().toString().replace("-", ""));
        String sql = SqlUtils.getInsertOneSql(tableName, fieldValues);
        System.out.println(sql);
        namedParameterJdbcTemplate.update(sql, fieldValues);
    }

    public void insertOne03() {
        String tableName = "sys_user";
        Map<String,Object> fieldValues = new HashMap();
        fieldValues.put("id", UUID.randomUUID().toString().replace("-", ""));
        fieldValues.put("del", null);
        String sql = SqlUtils.getInsertOneSql(tableName, fieldValues);
        System.out.println(sql);
        namedParameterJdbcTemplate.update(sql, fieldValues);
    }

    public void insertMany01() {
        String tableName = "sys_user";
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String,Object> fieldValues = new HashMap();
            fieldValues.put("id", UUID.randomUUID().toString().replace("-", ""));
            fieldValues.put("del", null);
            list.add(fieldValues);
        }
        String sql = SqlUtils.getInsertOneSql(tableName, list.get(0));
        System.out.println(sql);
        namedParameterJdbcTemplate.batchUpdate(sql, list.toArray(new Map[]{}));
    }

    private void testUser() throws JsonProcessingException {
        UserEntity userEntity = new UserEntity();
        userEntity.setDel(false);
        userEntity.setPhone("phone2");
        userEntity.setNickname("nickname2");
        userEntity.setUsername("username2");
        userService.save(userEntity);
        userService.getRepository().findById(userEntity.getId());

        Specification specification = Specifications.and()
                .like("username", "%a%")
                .like("phone", "%%a")
                .like("nickname", "%a%")
                .build();
        List<UserEntity> users = userService.getRepository().findAll(specification);

        String usernameCondition = "";
        String condition = "";
        specification = Specifications.and()
                .like(StringUtils.hasText(usernameCondition), "username", "%"+usernameCondition+"%")
                .predicate(StringUtils.hasText(condition), Specifications.or()
                        .like("phone", "%"+condition+"%")
                        .like("nickname", "%"+condition+"%")
                        .build())
                .build();

        userService.getRepository().findAll(specification, PageRequest.of(1, 1));

        users = userService.getRepository().findAll(specification);
        System.out.println(mapper.writeValueAsString(users));

        specification = Specifications.and()
                .in("id", "1","2","3","4")
                .like("username", "%a%")
                .predicate(Specifications.or()
                        .like("phone", "%a%")
                        .like("nickname", "%a%")
                        .build())
                .build();
        users = userService.getRepository().findAll(specification);
    }

    private ObjectMapper mapper = new ObjectMapper();

    private void testTest() throws JsonProcessingException {
        String sql = " SELECT su.id, su.username, su.nickname, su.phone, su.del, sr.id role_id, sr.name role_name FROM sys_user_role_rel surr " +
                " LEFT JOIN sys_user su ON surr.user_id = su.id " +
                " LEFT JOIN sys_role sr ON surr.role_id = sr.id " +
                " WHERE su.del=? ";
        System.out.println(mapper.writeValueAsString(sqlRepository.queryPageResultList(sql,1,1, false)));
        System.out.println(mapper.writeValueAsString(sqlRepository.queryResultList("SELECT id, username FROM sys_user")));
        List<String> list = Arrays.asList("00","11","22","33","44");
        String id = "0000";
        System.out.println(mapper.writeValueAsString(sqlRepository.queryResultList("SELECT id, username FROM sys_user WHERE id IN ?1 AND id=?2", list, id)));

        sqlRepository.queryResultCount("SELECT COUNT(id) FROM sys_user");
    }
}

