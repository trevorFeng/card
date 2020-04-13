package com.card.auth.controller;


import com.card.bean.bo.JsonEntity;
import com.card.bean.domain.User;
import com.card.bean.service.RedisService;
import com.google.common.collect.Lists;
import com.poke.common.bean.bo.JsonEntity;
import com.poke.common.bean.bo.ResponseHelper;
import com.poke.common.bean.domain.mysql.PersonalCard;
import com.poke.common.bean.domain.mysql.User;
import com.poke.common.bean.enums.MessageCodeEnum;
import com.poke.common.client.PersonalCardDbClient;
import com.poke.common.client.UserDbClient;
import com.poke.common.util.RandomUtils;
import com.poke.common.util.TokenUtil;
import com.poke.pokeAuth.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 一句话描述该类作用:【】
 *
 * @author: trevor
 * @create: 2019-03-14 0:56
 **/
@RestController
public class TestLoginController {

    @Resource
    private UserDbClient userDbClient;

    @Resource
    private PersonalCardDbClient personalCardDbClient;

    @Resource
    private RedisService redisService;

    /**
     * 根据手机号注册用户
     * @param phoneNum
     * @return
     */
    @RequestMapping(value = "/api/zhuCe/phoneNum/{phoneNum}", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonEntity<Object> zhuCe(@PathVariable(value = "phoneNum") String phoneNum){
        User user = userDbClient.findByPhoneNum(phoneNum).getData();
        if (user != null) {
            return ResponseHelper.withErrorInstance(MessageCodeEnum.NAME_REPEAT);
        }

        String openid = System.currentTimeMillis() + "";

        String name = RandomUtils.getRandNum();

        List<String> tupianList = Lists.newArrayList();
        tupianList.add("http://hbimg.b0.upaiyun.com/fc4285d30a2d667304b9ef3c0d820b97a6e402933669d-QfsNiI_fw658");
        tupianList.add("http://pic31.nipic.com/20130725/2929309_105611417128_2.jpg");
        tupianList.add("http://img3.imgtn.bdimg.com/it/u=4098886459,2746584588&fm=26&gp=0.jpg");
        tupianList.add("http://img3.imgtn.bdimg.com/it/u=809705136,1148759487&fm=26&gp=0.jpg");
        tupianList.add("http://img5.imgtn.bdimg.com/it/u=3275347102,446490913&fm=26&gp=0.jpg");

        user = new User();
        user.setOpenId(openid);
        user.setAppName(name);
        user.setPhoneNumber(phoneNum);
        user.setAppPictureUrl(tupianList.get(RandomUtils.getRandNumMax(tupianList.size())));


        user.setType((byte) 1);
        user.setFriendManageFlag((byte)0);
        userDbClient.save(user);

        PersonalCard personalCard = new PersonalCard();
        personalCard.setUserId(user.getId());
        personalCard.setRoomCardNum(0);

        personalCardDbClient.save(personalCard);

        return ResponseHelper.createInstanceWithOutData(MessageCodeEnum.HANDLER_SUCCESS);
    }

    @ApiOperation("根据手机号登陆")
    @RequestMapping(value = "/api/login/phoneNum/{phoneNum}", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonEntity<String> login(@PathVariable String phoneNum){
        User user = userDbClient.findByPhoneNum(phoneNum).getData();
        if (user == null) {
            return ResponseHelper.withErrorInstance(MessageCodeEnum.NAME_NOT_EXISTS);
        }
        Map<String,Object> claims = new HashMap<>(2<<4);
        claims.put("userid" ,user.getId());
        claims.put("openid" ,user.getOpenId());
        claims.put("timestamp" ,System.currentTimeMillis());
        String token = TokenUtil.generateToken(claims);

        //存入redis，过期时间为7天
        redisService.setValueWithExpire("acess_token:" + token ,String.valueOf(System.currentTimeMillis())
                ,7 * 24 * 60 * 60L , TimeUnit.MILLISECONDS);
        return ResponseHelper.createInstance(token , MessageCodeEnum.HANDLER_SUCCESS);
    }


}
