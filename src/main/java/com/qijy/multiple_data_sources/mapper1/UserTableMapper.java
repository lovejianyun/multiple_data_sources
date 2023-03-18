package com.qijy.multiple_data_sources.mapper1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qijy.multiple_data_sources.ro.UserTableRO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserTableMapper extends BaseMapper<UserTableRO> {
    List<UserTableRO> queryUserTable();
}
