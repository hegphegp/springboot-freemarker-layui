```
@Query(value = "SELECT * FROM sys_user WHERE (:username is null or username = :username)", nativeQuery = true)
List<Map> query(@Param("username") String username);
```
#### 以上SQL当username为null时, postgresql就会变成傻逼加脑残, 居然把 null 识别成 byte 类型, SQL直接抛错, postgresql真坑爹

```
String sql = "update sys_user set username=?":
Query query = entityManager.createNativeQuery(sql);
query.setParameter("username", null);
query.executeUpdate()
```

# 痛恨一切的垃圾, 对一切的垃圾软件深恶痛绝