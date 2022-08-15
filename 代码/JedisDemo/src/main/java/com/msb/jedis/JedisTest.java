package com.msb.jedis;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 金喆
 */

public class JedisTest {

    @Test
    public void testStandalone()
    {
        Jedis jedis = new Jedis("192.168.93.10",6379);
       // jedis.set("name" ,"msb-standalone");
        String value = jedis.get("name");

        System.out.println(value);

    }

    @Test
    public void testJedisPool()
    {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(3);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig , "192.168.93.10",6379 );

        Jedis jedis = jedisPool.getResource();
        jedis.set("name" , "msb-pool");

        String poolvalue = jedis.get("name");
        System.out.println(poolvalue);
    }

    @Test
    public void testCluster()
    {
        Set<HostAndPort> set = new HashSet<HostAndPort>();
        set.add(new HostAndPort("192.168.93.10",7001));
        set.add(new HostAndPort("192.168.93.10",7002));
        set.add(new HostAndPort("192.168.93.10",7003));
        set.add(new HostAndPort("192.168.93.10",7004));
        set.add(new HostAndPort("192.168.93.10",7005));
        set.add(new HostAndPort("192.168.93.10",7006));

        JedisCluster jedisCluster = new JedisCluster(set);
        jedisCluster.set("name" , "bjmsb");

        String value = jedisCluster.get("name");

        System.out.println(value);
    }
}
