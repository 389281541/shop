package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.entity.User;
import com.rainbow.admin.mapper.user.UserMapper;
import com.rainbow.admin.api.dto.LoginDTO;
import com.rainbow.admin.module.TokenModel;
import com.rainbow.admin.service.IUserService;
import com.rainbow.admin.util.JwtManager;
import com.rainbow.common.constant.Constant;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import com.rainbow.common.util.MD5Utils;
import com.rainbow.common.vo.IdNameTokenVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 后台管理用户表 服务实现类
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private JwtManager jwtManager;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过用户名密码登录
     * @param req
     * @return
     */
    @Override
    public IdNameTokenVO loginByPassword(LoginDTO req) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, req.getUserName());
        User user = this.getOne(queryWrapper);
        //用户不存在
        if (user == null) {
            throw new BusinessException(BaseErrorCode.NO_USER);
        }
        String encodePassword = MD5Utils.encodeByMd5AndSalt(req.getPassword(), user.getSalt());
        //密码错误
        if (!Objects.equals(user.getPassword(), encodePassword)) {
            throw new BusinessException(BaseErrorCode.ERROR_PASSWORD);
        }

        IdNameTokenVO idNameTokenVO = new IdNameTokenVO();
        idNameTokenVO.setId(user.getId());
        idNameTokenVO.setName(user.getUserName());
        idNameTokenVO.setToken(genToken(user));
        return idNameTokenVO;
    }


    /**
     * 生成token
     * @param user
     * @return
     */
    private String genToken(User user) {
        TokenModel tokenModel = new TokenModel();
        tokenModel.setUserId(user.getId());
        tokenModel.setUserName(user.getUserName());
        tokenModel.setMobile(user.getMobile());
        tokenModel.setToken(UUID.randomUUID().toString());
        tokenModel.setSessionTime(Constant.USER_SESSION_CACHE_TIME);

        String token = jwtManager.createTokenStr(tokenModel);
        String cacheKey = Constant.CACHE_USER_ID_PREFIX + user.getId();
        redisTemplate.opsForValue().set(cacheKey, token, tokenModel.getSessionTime(), TimeUnit.MINUTES);
        return token;
    }
}
