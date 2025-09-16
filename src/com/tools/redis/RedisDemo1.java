package com.tools.redis;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisDemo1 {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
        test05();
        test06();
        test07();
        test08();
        test09();
        test10();
        test11();
        test12();
    }

    /**
     * 创建 Jedis 实例（基本单机连接）
     * Jedis(String host, int port)	创建单机连接	Jedis jedis = new Jedis("localhost", 6379);
     * Jedis(String host, int port, int connectionTimeout, int soTimeout)	带超时设置的连接	Jedis jedis = new Jedis("localhost", 6379, 2000, 2000);
     * ping()	测试连接是否存活	jedis.ping(); // 返回 "PONG"
     */
    public static void test01(){
        System.out.println("基本连接");
        Jedis jedis = new Jedis("192.168.56.101", 6379); // 默认端口 6379
        System.out.println(jedis.ping()); // 测试连接，返回 "PONG"
        jedis.close(); // 关闭连接
    }

    /**
     * 使用连接池（推荐生产环境使用）
     * Jedis 支持连接池（JedisPool），避免频繁创建和销毁连接，提高性能。
     * JedisPool(JedisPoolConfig poolConfig, String host, int port)	创建连接池	JedisPool pool = new JedisPool(config, "localhost", 6379);
     * getResource()	从池中获取 Jedis 实例	try (Jedis jedis = pool.getResource()) { ... }
     * close()	关闭连接池	pool.close();
     *
     * 关键配置参数：
     * maxTotal：最大连接数
     * maxIdle：最大空闲连接数
     * minIdle：最小空闲连接数
     */
    public static void test02(){
        System.out.println("连接池");
        // 配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10); // 最大连接数
        poolConfig.setMaxIdle(5);   // 最大空闲连接数
        poolConfig.setMinIdle(1);   // 最小空闲连接数

        // 创建连接池
        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.56.101", 6379);

        // 从连接池获取 Jedis 实例
        try (Jedis jedis = jedisPool.getResource()) {
            System.out.println(jedis.ping());
        } // 自动归还连接到池中

        // 关闭连接池（程序结束时调用）
        jedisPool.close();
    }

    /**
     *String 类型
     * 方法	说明	适用场景	示例
     * set(String key, String value)	设置键值	缓存、配置存储	jedis.set("name", "Alice");
     * get(String key)	获取值	读取缓存	String name = jedis.get("name");
     * setex(String key, int seconds, String value)	设置带过期时间的键值	短期缓存、验证码	jedis.setex("token", 3600, "abc123");
     * setnx(String key, String value)	仅当键不存在时设置	分布式锁	jedis.setnx("lock", "1");
     * incr(String key)	自增（原子操作）	计数器	jedis.incr("page_views");
     * decr(String key)	自减	库存扣减	jedis.decr("stock");
     * append(String key, String value)	追加字符串	日志追加	jedis.append("log", "new entry");
     * mset(String... keysvalues)	批量设置键值	初始化多个配置	jedis.mset("k1", "v1", "k2", "v2");
     * mget(String... keys)	批量获取值	批量读取缓存	List<String> values = jedis.mget("k1", "k2");
     *
     * 缓存简单数据（如用户信息、配置项）
     * 计数器（如访问次数）
     * 分布式锁（结合 SETNX） 结合 setnx + expire（但推荐直接用 SET key value NX PX milliseconds 原子命令）。
     *
     */
    public static void test03(){
        System.out.println("String");
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            // 创建实例
            jedis = new Jedis("192.168.56.101", 6379);
//            jedis.auth("","");
            jedis.select(1);

            //
            jedis.set("string.test.test01:key01","字符串测试01");
            String str01 = jedis.get("string.test.test01:key01");
            System.out.println(str01);

            jedis.set("name", "Alice");
            String name = jedis.get("name");
            System.out.println(name); // Alice

            jedis.setex("session", 3600, "user123"); // 1小时后过期

            jedis.incr("page_views"); // 计数器自增
            jedis.incr("page_views");
            long views = Long.parseLong(jedis.get("page_views"));
            System.out.println("访问次数：" + views);

        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     * Hash哈希
     * Hash 适合存储对象（如用户信息），支持字段级操作
     * 方法	说明	适用场景	示例
     * hset(String key, String field, String value)	设置单个字段	存储对象属性	jedis.hset("user:1", "name", "Bob");
     * hget(String key, String field)	获取单个字段	读取对象属性	String name = jedis.hget("user:1", "name");
     * hmset(String key, Map<String, String> hash)	已过时，批量设置字段	旧代码兼容	jedis.hmset("user:1", fields);
     * hset(String key, Map<String, String> hash)	推荐，批量设置字段	新代码开发	jedis.hset("user:1", userFields);
     * hgetAll(String key)	获取所有字段和值	完整对象读取	Map<String, String> user = jedis.hgetAll("user:1");
     * hincrby(String key, String field, long increment)	字段值自增（整数）	计数器	jedis.hincrBy("user:1", "login_count", 1);
     * hdel(String key, String... fields)	删除字段	动态移除属性	jedis.hdel("user:1", "age");
     * hexists(String key, String field)	判断字段是否存在	权限检查	boolean hasEmail = jedis.hexists("user:1", "email");
     * hflush（key）：清空指定Hash中的所有字段
     * hlen（key）：返回指定Hash中字段的数量
     *
     * 存储对象（如用户信息、商品详情）
     * 避免序列化整个对象，节省内存
     */
    public static void test04(){
        System.out.println("Hash");
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            //
            jedis.hset("user:1001", "name", "Bob");
            jedis.hset("user:1001", "age", "30");
            String userName = jedis.hget("user:1001", "name");
            System.out.println(userName); // Bob

            Map<String, String> userInfo = jedis.hgetAll("user:1001");
            System.out.println(userInfo); // {name=Bob, age=30}


        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     * List（列表）
     * List 是有序的字符串集合，支持两端插入和弹出。
     *
     * lpush(String key, String... values)	从左侧插入	消息队列（最新消息优先）	jedis.lpush("tasks", "task1", "task2");
     * rpush(String key, String... values)	从右侧插入	消息队列（最早消息优先）	jedis.rpush("tasks", "task3");
     * lrange(String key, long start, long end)	获取范围元素	分页查询	List<String> tasks = jedis.lrange("tasks", 0, -1);
     * lpop(String key)	从左侧弹出	消费消息	String task = jedis.lpop("tasks");
     * rpop(String key)	从右侧弹出	消费消息	String task = jedis.rpop("tasks");
     * llen(String key)	获取列表长度	分页计算	long length = jedis.llen("tasks");
     * lindex(String key, long index)	获取指定索引元素	随机访问	String item = jedis.lindex("tasks", 0);
     * lrem(String key, long count, String value)	删除指定值	清理数据	jedis.lrem("tasks", 1, "task1");
     *
     * 消息队列（如任务队列、消息推送）
     * 最新消息排行（如微博最新动态）
     *
     */
    public static void test05(){
        System.out.println("List");
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            //
            jedis.lpush("queue", "task1", "task2");
            jedis.rpush("queue", "task3");

            List<String> tasks = jedis.lrange("queue", 0, -1);
            System.out.println(tasks); // [task2, task1, task3]

            String task = jedis.rpop("queue");
            System.out.println("处理任务：" + task); // task3

        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     * Set（集合）
     * Set 是无序且唯一的字符串集合
     * 方法	说明	适用场景	示例
     * sadd(String key, String... members)	添加元素	标签系统、好友关系	jedis.sadd("tags", "java", "redis");
     * smembers(String key)	获取所有成员	标签查询	Set<String> tags = jedis.smembers("tags");
     * sismember(String key, String member)	判断成员是否存在	权限验证	boolean hasJava = jedis.sismember("tags", "java");
     * srem(String key, String... members)	删除成员	取消标签	jedis.srem("tags", "java");
     * scard(String key)	获取集合大小	统计标签数量	long count = jedis.scard("tags");
     * srandmember(String key, int count)	随机获取成员	抽奖	String winner = jedis.srandmember("users", 1);
     * sinter(String... keys)	多集合交集	共同关注	Set<String> common = jedis.sinter("user1:tags", "user2:tags");
     *
     * 去重、标签系统、共同关注（结合 SINTER）、抽奖（随机抽取 SRANDMEMBER）
     */
    public static void test06(){
        System.out.println("set");
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            //
            jedis.sadd("article:101:tags", "java", "redis");
            Set<String> tags = jedis.smembers("article:101:tags");
            System.out.println(tags); // [java, redis]

            boolean hasJava = jedis.sismember("article:101:tags", "java");
            System.out.println("是否包含 java：" + hasJava); // true

        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     * Sorted Set（有序集合）
     * Sorted Set 是带权重的集合，适合排行榜和延迟队列
     *
     * 方法	说明	适用场景	示例
     * zadd(String key, double score, String member)	添加成员和分数	排行榜、延迟队列	jedis.zadd("leaderboard", 100, "player1");
     * zrange(String key, long start, long end)	按分数升序获取成员	排行榜（从低到高）	Set<String> leaders = jedis.zrange("leaderboard", 0, -1);
     * zrevrange(String key, long start, long end)	按分数降序获取成员	排行榜（从高到低）	Set<String> topPlayers = jedis.zrevrange("leaderboard", 0, -1);
     * zscore(String key, String member)	获取成员分数	查询排名	Double score = jedis.zscore("leaderboard", "player1");
     * zincrby(String key, double increment, String member)	分数自增	动态更新排名	jedis.zincrby("leaderboard", 10, "player1");
     * zrem(String key, String... members)	删除成员	移除玩家	jedis.zrem("leaderboard", "player1");
     *
     * 游戏排行榜、文章热度排名、延迟任务队列（用时间戳作为分数）
     */
    public static void test07(){
        System.out.println("Sorted set");
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            //
            jedis.zadd("game:leaderboard", 150, "playerA");
            jedis.zadd("game:leaderboard", 200, "playerB");

//            Set<String> topPlayers = jedis.zrevrange("game:leaderboard", 0, -1);
//            System.out.println("排行榜：" + topPlayers); // [playerB, playerA]

        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     * 事务（Transaction）
     * multi()	开启事务	Transaction tx = jedis.multi();
     * exec()	执行事务	tx.exec();
     * discard()    放弃事务	tx.discard();
     *
     * 需要原子性执行的多个命令（如扣减库存+记录日志）。
     */
    public static void test08(){
        System.out.println("Tran");
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            //
            Transaction tx = jedis.multi();
            tx.set("name", "Alice");
            tx.incr("visits");
            tx.exec(); // 提交事务

        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     * 管道（Pipeline）
     * pipeline()	创建管道	Pipeline pipeline = jedis.pipeline();
     * syncAndReturnAll()	执行并获取结果	List<Object> results = pipeline.syncAndReturnAll();
     * 批量操作（如批量写入缓存），显著提升性能。
     */
    public static void test09(){
        System.out.println("pipeline");
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            //
            Pipeline pipeline = jedis.pipelined();
            pipeline.set("foo", "bar");
            pipeline.get("foo");
            List<Object> responses = pipeline.syncAndReturnAll();
            System.out.println(responses); // [OK, bar]


        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     * 发布订阅（Pub/Sub）
     * subscribe(JedisPubSub listener, String... channels)	订阅频道	jedis.subscribe(new MyListener(), "news");
     * publish(String channel, String message)	发布消息	jedis.publish("news", "Hello!");
     *
     * 实时消息通知（如聊天室、系统告警）。
     * 发布订阅模式，适合消息广播
     */
    public static void test10(){
        System.out.println("pubsub");
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            //
            // 订阅者
            new Thread(() -> {
                Jedis subJedis = new Jedis("192.168.56.101");
                subJedis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        System.out.println("收到消息: " + message + " (来自频道: " + channel + ")");
                    }
                }, "news");
            }).start();

            // 发布者
            Jedis pubJedis = new Jedis("192.168.56.101");
            pubJedis.publish("news", "Hello, Redis Pub/Sub!");

        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     * Lua 脚本
     * eval(String script, int keyCount, String... params)	执行脚本	见前文示例
     * evalsha(String sha1, int keyCount, String... params)	通过 SHA1 执行脚本	见前文示例
     * scriptLoad(String script)	加载脚本并返回其 SHA1 校验码
     * scriptExists(String... sha1s)	检查脚本是否已加载
     * scriptFlush()	清除所有已加载的脚本
     * scriptKill()	强制终止正在执行的脚本（慎用）
     *
     * 原子性复杂操作（如秒杀库存扣减）、自定义算法。
     * 原子性、减少网络开销、复杂逻辑
     *
     * Redis 支持通过 Lua 脚本在服务端执行多个命令，具有以下优势：
     * 原子性：Lua 脚本中的所有命令会作为一个整体执行，不会被其他客户端的命令打断。
     * 减少网络开销：将多个命令打包成一个脚本一次性发送到 Redis，减少网络往返时间（RTT）。
     * 复杂逻辑：可以在 Redis 中实现复杂的业务逻辑，而无需在客户端处理。
     *
     * 注意事项
     * 脚本复杂度：避免在 Lua 脚本中执行耗时操作，否则会阻塞 Redis。
     * 错误处理：如果 Lua 脚本执行出错，Redis 会终止脚本并返回错误信息。
     * 参数传递：
     * KEYS：用于传递键名（通过 eval 的 keyCount 参数指定数量）。
     * ARGV：用于传递参数值。
     */
    public static void test11(){
        System.out.println("lua");
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            // 要实现一个原子性的计数器自增操作，并返回自增后的值。
            String script = "local current = redis.call('GET', KEYS[1]) " +
                    "if current == false then " +
                    "    current = 0 " +
                    "else " +
                    "    current = tonumber(current) " +
                    "end " +
                    "local new = current + tonumber(ARGV[1]) " +
                    "redis.call('SET', KEYS[1], new) " +
                    "return new";

            // 执行脚本
            Object result = jedis.eval(script, 1, "counter", "1");
            System.out.println("自增后的值：" + result); // 输出自增后的值

            // 为了避免每次都传输完整的 Lua 脚本，可以先加载脚本并缓存其 SHA1 校验码，后续通过 evalsha 执行。
            // 加载脚本并获取 SHA1
            String script1 = "local current = redis.call('GET', KEYS[1]) " +
                    "if current == false then " +
                    "    current = 0 " +
                    "else " +
                    "    current = tonumber(current) " +
                    "end " +
                    "local new = current + tonumber(ARGV[1]) " +
                    "redis.call('SET', KEYS[1], new) " +
                    "return new";

            String sha1 = jedis.scriptLoad(script1);
            System.out.println("脚本 SHA1：" + sha1);

            // 通过 SHA1 执行脚本
            Object result1 = jedis.evalsha(sha1, 1, "counter", "1");
            System.out.println("自增后的值：" + result1);


        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     * 集群支持（JedisCluster）
     * JedisCluster(Set<HostAndPort> nodes)	创建集群客户端	见前文示例
     * set(String key, String value)	集群键值操作	jedisCluster.set("key", "value");
     * set(String key, String value)	设置键值
     * get(String key)	获取值
     * hset(String key, String field, String value)	设置哈希字段
     * hget(String key, String field)	获取哈希字段
     *
     * 数据分片：数据自动分片到多个节点，突破单机内存限制。
     * 高可用：支持主从复制和自动故障转移。
     * 水平扩展：可以动态添加或移除节点。
     * 数据分片、高可用、水平扩展
     * 大数据量、高可用需求（如电商秒杀系统）。
     *
     * 限制：不支持跨槽操作、事务、管道
     *
     * 集群模式下的最佳实践
     * 使用哈希标签：确保相关键分配到同一槽，以支持批量操作。
     * 例如：{user:1000}:profile 和 {user:1000}:settings。
     * 避免大 Key：集群模式下大 Key 会影响性能，建议拆分。
     * 监控集群状态：使用 redis-cli --cluster check 检查集群健康状况。
     */
    public static void test12(){
        System.out.println("Cluster");
        // 定义集群节点
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7001));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7002));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7003));

        // 创建 JedisCluster 实例
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);

        // 设置键值
        jedisCluster.set("cluster_key", "cluster_value");

        // 获取值
        String value = jedisCluster.get("cluster_key");
        System.out.println("集群获取的值：" + value);

        // 关闭集群连接（实际生产环境通常不会显式关闭）
        jedisCluster.close();

        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            //


        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    /**
     *
     */
    public static void test(){
        // 创建Jedis对象，参数为Redis服务器地址和端口
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.56.101", 6379);
            //jedis.auth("","");
            jedis.select(1);

            //
            jedis.expire("key",10000);//设置过期时间
            long ttl = jedis.ttl("key");//返回过期时间


        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(jedis != null){
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }
}
