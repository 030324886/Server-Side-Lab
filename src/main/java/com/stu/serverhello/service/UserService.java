package com.stu.serverhello.service;

import com.stu.serverhello.common.Result;
import com.stu.serverhello.dto.UserDTO;
import com.stu.serverhello.entity.UserInfo;
import com.stu.serverhello.vo.UserDetailVO;

public interface UserService {
    Result<String> register(UserDTO userDTO);
    Result<String> login(UserDTO userDTO);
    Result<Object> getUserPage(Integer pageNum, Integer pageSize);

    // 任务7新增
    Result<UserDetailVO> getUserDetail(Long userId);
    Result<String> updateUserInfo(UserInfo userInfo);
    Result<String> deleteUser(Long userId);
}