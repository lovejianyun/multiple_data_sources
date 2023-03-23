package com.qijy.multiple_data_sources;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qijy.multiple_data_sources.mapper1.UserTableMapper;
import com.qijy.multiple_data_sources.mapper2.CourseTableMapper;
import com.qijy.multiple_data_sources.ro.CourseTableRO;
import com.qijy.multiple_data_sources.ro.UserTableRO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
class MultipleDataSourcesApplicationTests {
    @Autowired
    private UserTableMapper userTableMapper;
    @Autowired
    private CourseTableMapper courseTableMapper;
    @Test
    public void demo001(){
        UserTableRO userTableRO = new UserTableRO();
        for (int i = 1; i <= 100; i++) {
            userTableRO.setId(i);
            userTableRO.setUsername("qijy"+i);
            userTableRO.setAge(5+i);
            userTableMapper.insert(userTableRO);
        }
    }
    @Test
    public void demo002(){
        CourseTableRO courseTableRO = new CourseTableRO();
        for (int i = 1; i <= 100; i++) {
            courseTableRO.setId(i);
            courseTableRO.setCoursename("数学"+i);
            courseTableRO.setCoursetype("文化"+i);
            courseTableMapper.insert(courseTableRO);
        }

    }
    @Test
    public void demo003(){
        List<UserTableRO> userTableROS = userTableMapper.queryUserTable();
        System.out.println(userTableROS.size());
    }

    @Test
    public void demo004(){
        List<CourseTableRO> courseTableROS = courseTableMapper.queryCourseTable();
        System.out.println(courseTableROS.size());
    }

    @Test
    public void demo005(){
        Page<UserTableRO> page = new Page<>(1,10);
        Page<UserTableRO> userTableROPage = userTableMapper.selectPage(page, new QueryWrapper<UserTableRO>().lambda().orderByDesc(UserTableRO::getId));
        System.out.println(userTableROPage.getTotal());
    }

    @Test
    public void demo006(){
        Page<CourseTableRO> page = new Page<>(1,10);
        Page<CourseTableRO> courseTableROPage = courseTableMapper.selectPage(page, new QueryWrapper<CourseTableRO>().lambda().orderByDesc(CourseTableRO::getId));
        System.out.println(courseTableROPage.getRecords());
    }

}
