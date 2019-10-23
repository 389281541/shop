package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainbow.admin.api.dto.LoginDTO;
import com.rainbow.admin.entity.Administrator;
import com.rainbow.admin.entity.User;
import com.rainbow.admin.mapper.AdministratorMapper;
import com.rainbow.admin.module.TokenModel;
import com.rainbow.admin.service.IAdministratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.util.CookieUtil;
import com.rainbow.admin.util.JwtManager;
import com.rainbow.common.constant.Constant;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import com.rainbow.common.util.MD5Utils;
import com.rainbow.common.vo.IdNameTokenVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 后台管理用户表 服务实现类
 *
 * @author lujinwei
 * @since 2019-10-22
 */
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements IAdministratorService {
    @Resource
    private JwtManager jwtManager;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过用户名密码登录
     * @param loginRequest
     * @param httpResponse
     * @return
     */
    @Override
    public IdNameTokenVO loginByPassword(LoginDTO loginRequest, HttpServletResponse httpResponse) {
        LambdaQueryWrapper<Administrator> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Administrator::getUserName, loginRequest.getUserName());
        Administrator administrator = this.getOne(queryWrapper);
        //用户不存在
        if (administrator == null) {
            throw new BusinessException(BaseErrorCode.NO_USER);
        }
        String encodePassword = MD5Utils.encodeByMd5AndSalt(loginRequest.getPassword(), administrator.getSalt());
        //密码错误
        if (!Objects.equals(administrator.getPassword(), encodePassword)) {
            throw new BusinessException(BaseErrorCode.ERROR_PASSWORD);
        }

        IdNameTokenVO idNameTokenVO = new IdNameTokenVO();
        idNameTokenVO.setId(administrator.getId());
        idNameTokenVO.setName(administrator.getUserName());
        idNameTokenVO.setToken(genToken(administrator,httpResponse));
        return idNameTokenVO;
    }


    /**
     * 生成token
     * @param administrator
     * @param httpResponse
     * @return
     */
    private String genToken(Administrator administrator, HttpServletResponse httpResponse) {
        TokenModel tokenModel = new TokenModel();
        tokenModel.setUserId(administrator.getId());
        tokenModel.setUserName(administrator.getUserName());
        tokenModel.setMobile(administrator.getMobile());
        tokenModel.setToken(UUID.randomUUID().toString());
        tokenModel.setSessionTime(Constant.USER_SESSION_CACHE_TIME);

        String token = jwtManager.createTokenStr(tokenModel);
        String cacheKey = Constant.CACHE_USER_ID_PREFIX + administrator.getId();
        redisTemplate.opsForValue().set(cacheKey, token, tokenModel.getSessionTime(), TimeUnit.MINUTES);
        CookieUtil.addCookie(httpResponse,Constant.LOGIN_TOKEN_COOKIE_NAME, token);
        return token;
    }
}
