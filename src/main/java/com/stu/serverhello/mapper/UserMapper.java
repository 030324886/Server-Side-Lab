package com.stu.serverhello.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.serverhello.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis-Plus Mapper接口
 * 继承BaseMapper<User>，获得所有单表CRUD方法（无需手写SQL）
 */
@Mapper // 可选，启动类已加@MapperScan，二选一即可
public interface UserMapper extends BaseMapper<User> {

}