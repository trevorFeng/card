package com.card.auth.service.impl;

import com.card.auth.service.UserService;
import com.card.bean.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByOpenidAndUserId(String openid , Integer userId) {
        if (StringUtils.isEmpty(openid) || userId == null) {
            throw new BizException(500 ,"openid或userId不能为空");
        }
        User user = userMapper.findByOpenidAndUserId(openid, userId);
        if (user == null) {
            throw new BizException(500 ,"没有该用户");
        }
        return user;
    }

    @Override
    public List<User> findUsersByIds(List<Integer> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return new ArrayList<>();
        }
        return userMapper.findUsersByIds(userIds);
    }

    @Override
    public User findUserById(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new BizException(500 ,"没有该用户");
        }
        return user;
    }

    @Override
    public User findPhoneNum(String phoneNum) {
        return userMapper.findByPhoneNum(phoneNum);
    }

    @Override
    public void saveUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public Boolean isExistByOpnenId(String openid) {
        Long count = userMapper.countByOpenid(openid);
        if (count == 0L) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean isExistByPhoneNum(String phoneNum) {
        Long count = userMapper.countByPhone(phoneNum);
        if (count == 0L) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

}
