package com.nchu.dao;

import com.nchu.mapper.AreaMapper;
import com.nchu.model.Area;
import com.nchu.model.AreaExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DaoApplicationTests {

    @Resource
    private AreaMapper areaMapper;
    @Test
    public void contextLoads() {

    }

}
