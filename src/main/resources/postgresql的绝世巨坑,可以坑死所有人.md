```
@Query(value = "SELECT * FROM sys_user WHERE (:username is null or username = :username)", nativeQuery = true)
List<Map> query(@Param("username") String username);
```
#### 以上SQL当username为null时, postgresql就会变成傻逼加脑残, 居然把 null 识别成 bytea 类型, SQL直接抛错, postgresql真是辣鸡数据库, 害人不浅
#### 如果有十几个动态参数, 用postgresql的话, 是不是要写2的十几次方的@Query方法

```
String sql = "update sys_user set username=?":
Query query = entityManager.createNativeQuery(sql);
query.setParameter("username", null);
query.executeUpdate()
```

# 痛恨一切的垃圾, 对一切的垃圾软件深恶痛绝