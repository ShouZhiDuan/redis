package pool;

import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2022/3/30 17:41
 * @Description:
 */
public class JedisPoolTest {

    public static void main(String[] args) {
        //ordinaryPool();
        shardedPool();

    }

    /**
     * 普通連接池
     */
    public static void ordinaryPool(){
        JedisPool pool = new JedisPool("192.168.10.21",6379);
        Jedis jedis = pool.getResource();
        jedis.set("test_ordinary_pool","test_txt");
    }

    /**
     * sharded
     */
    public static void shardedPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("192.168.10.21",6379);
        //JedisShardInfo jedisShardInfo2 = new JedisShardInfo("192.168.10.21",6379);
        //JedisShardInfo jedisShardInfo3 = new JedisShardInfo("192.168.10.21",6379);
        List<JedisShardInfo> jedisShardInfos = Arrays.asList(jedisShardInfo1);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, jedisShardInfos);
        ShardedJedis jedis = shardedJedisPool.getResource();
        jedis.set("sharded-test","sharded");
    }

    /**
     * sentinel
     */
    public static void sentinelPool(){
        String masterName = "redis-master";
        HashSet<String> sentinels = new HashSet<>();
        sentinels.add("192.168.10.21:6379");//sentinel地址
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName,sentinels);
        Jedis jedis = jedisSentinelPool.getResource();
        //jedis to do
    }



}
