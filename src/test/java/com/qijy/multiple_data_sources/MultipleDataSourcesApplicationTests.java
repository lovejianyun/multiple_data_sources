package com.qijy.multiple_data_sources;

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
        userTableRO.setId(2);
        userTableRO.setUsername("qijy");
        userTableRO.setAge(18);
        userTableMapper.insert(userTableRO);
    }
    @Test
    public void demo002(){
        CourseTableRO courseTableRO = new CourseTableRO();
        courseTableRO.setId(2);
        courseTableRO.setCoursename("数学");
        courseTableRO.setCoursetype("文化");
        courseTableMapper.insert(courseTableRO);
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

}
