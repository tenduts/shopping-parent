package com.nchu.redisexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisexampleApplicationTests {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void TestSetRedisValues() {
        String userName="zhangsang";
        redisTemplate.boundValueOps("user").set(userName);
    }
    @Test
    public void TestGetRedisValues() {
        String userName2=redisTemplate.boundValueOps("user").get().toString();
        System.out.println(userName2);
    }
    @Test
    public void TestSetStringRedisValues() {
        String userName="zhangsang";
        stringRedisTemplate.boundValueOps("user").set(userName);
        stringRedisTemplate.boundValueOps("user").expire(10,TimeUnit.SECONDS);
    }
    @Test
    public void TestupdateRedisValues() {
        String userName="zhang";
        stringRedisTemplate.boundValueOps("user").set(userName);
        stringRedisTemplate.boundValueOps("user").expire(20,TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate.boundValueOps("user").getExpire());
        //stringRedisTemplate.boundValueOps("user").expire(10,TimeUnit.SECONDS);
    }
    @Test
    public void TestDeleteRedisValues() {
        String userName2=redisTemplate.boundValueOps("user").get().toString();
        System.out.println(userName2);
        redisTemplate.delete("user");
    }
    @Test
    public void TestRedisSet(){
        String user1="zhangsang1";
        String user2="Lishi2";
        String user3="wangwu3";
        redisTemplate.boundSetOps("userSet").add(user1,user2);
        System.out.println("pop:"+redisTemplate.boundSetOps("userSet").pop().toString());
        redisTemplate.boundSetOps("userSet").add(user3);
        Set<Object> userSet=redisTemplate.boundSetOps("userSet").members();
        for(Object ob:userSet){
            System.out.println(ob.toString());
        }
    }
    @Test
    public void TestRedisList(){
        String user1="zhangsang1";
        String user2="Lishi2";
        String user3="wangwu3";
        //redisTemplate.boundListOps("userList").rightPush(user3);
        //redisTemplate.boundListOps("userList").leftPush(user3);
        //redisTemplate.boundListOps("userList").set(2,user2);
        //System.out.println(redisTemplate.boundListOps("userList").index(3));
        System.out.println(redisTemplate.boundListOps("userList").range(1,3));
    }
}
