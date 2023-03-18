package com.qijy.multiple_data_sources.mapper2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qijy.multiple_data_sources.ro.CourseTableRO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseTableMapper extends BaseMapper<CourseTableRO> {
    List<CourseTableRO> queryCourseTable();
}
