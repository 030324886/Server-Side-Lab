package com.stu.serverhello.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.serverhello.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}