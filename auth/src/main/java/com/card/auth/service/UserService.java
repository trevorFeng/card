package com.card.auth.service;


import com.card.bean.domain.User;

import java.util.List;

public interface UserService {

    User findByOpenidAndUserId(String openid, Integer userId);

    List<User> findUsersByIds(List<Integer> userIds);

    User findUserById(Integer userId);

    User findPhoneNum(String phoneNum);

    void saveUser(User user);

    Boolean isExistByOpnenId(String openid);

    Boolean isExistByPhoneNum(String phoneNum);

    void updateUser(User user);
}
