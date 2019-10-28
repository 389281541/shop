package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.admin.api.dto.LoginDTO;
import com.rainbow.admin.entity.*;
import com.rainbow.admin.mapper.*;
import com.rainbow.admin.module.TokenModel;
import com.rainbow.admin.service.IAdministratorService;
import com.rainbow.admin.util.CookieUtil;
import com.rainbow.admin.util.JwtManager;
import com.rainbow.common.constant.Constant;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import com.rainbow.common.util.MD5Utils;
import com.rainbow.common.vo.IdNameTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    @Resource
    private AdministratorRoleMapper administratorRoleMapper;
    
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Autowired
    private UserDetailsService userDetailsService;


    /**
     * 通过用户名密码登录
     * @param username
     * @param password
     * @param httpResponse
     * @return
     */
    @Override
    public IdNameTokenVO loginByPassword(String username, String password, HttpServletResponse httpResponse) {
        LambdaQueryWrapper<Administrator> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Administrator::getUserName, username);
        Administrator administrator = this.getOne(queryWrapper);
        //用户不存在
        if (administrator == null) {
            throw new BusinessException(BaseErrorCode.NO_USER);
        }
        String encodePassword = MD5Utils.encodeByMd5AndSalt(password, administrator.getSalt());
        //密码错误
        if (!Objects.equals(administrator.getPassword(), encodePassword)) {
            throw new BusinessException(BaseErrorCode.ERROR_PASSWORD);
        }
        //添加认证
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

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
        tokenModel.setPwd(administrator.getPassword());
        tokenModel.setMobile(administrator.getMobile());
        tokenModel.setToken(UUID.randomUUID().toString());
        tokenModel.setSessionTime(Constant.USER_SESSION_CACHE_TIME);

        String token = jwtManager.createTokenStr(tokenModel);
        String cacheKey = Constant.CACHE_USER_ID_PREFIX + administrator.getId();
        redisTemplate.opsForValue().set(cacheKey, token, tokenModel.getSessionTime(), TimeUnit.MINUTES);
        CookieUtil.addCookie(httpResponse,Constant.LOGIN_TOKEN_COOKIE_NAME, token);
        return token;
    }


    @Override
    public List<Permission> getPermissionByUserId(Long userId) {
        LambdaQueryWrapper<AdministratorRole> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(AdministratorRole::getAdminId,userId);
        List<AdministratorRole> administratorRolesList = administratorRoleMapper.selectList(roleWrapper);
        List<Permission> permissionList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(administratorRolesList)) {
            Set<Long> roleIds = administratorRolesList.stream().map(AdministratorRole::getRoleId).collect(Collectors.toSet());
            LambdaQueryWrapper<RolePermission> permissionWrapper = new LambdaQueryWrapper<>();
            permissionWrapper.in(RolePermission::getRoleId,roleIds);
            List<RolePermission> rolePermissions = rolePermissionMapper.selectList(permissionWrapper);
            if(!CollectionUtils.isEmpty(rolePermissions)) {
                Set<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());
                permissionList = permissionMapper.selectBatchIds(permissionIds);
            }
        }
        return permissionList;
    }
}
