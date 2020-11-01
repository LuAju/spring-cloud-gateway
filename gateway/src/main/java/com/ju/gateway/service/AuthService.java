package com.ju.gateway.service;

import com.ju.gateway.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private AuthDao authDao;

    // 从数据中获取权限
    public List<String> getAuths(int userId){
        return authDao.getAuths(userId);
    }

 }
