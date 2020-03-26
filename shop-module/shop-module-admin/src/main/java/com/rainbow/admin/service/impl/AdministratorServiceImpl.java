package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rainbow.admin.mapper.AdministratorMapper;
import com.rainbow.admin.model.TokenModel;
import com.rainbow.admin.service.IAdministratorService;
import com.rainbow.admin.util.JwtManager;
import com.rainbow.api.entity.Administrator;
import com.rainbow.api.entity.Permission;
import com.rainbow.api.entity.Role;
import com.rainbow.api.enums.AdminErrorCode;
import com.rainbow.api.vo.AdminstratorSimpleVO;
import com.rainbow.common.constant.Constant;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.util.CookieUtils;
import com.rainbow.common.util.MD5Utils;
import com.rainbow.common.vo.IdNameTokenVO;
import com.rainbow.common.vo.IdNameVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 后台管理用户表 服务实现类
 *
 * @author lujinwei
 * @since 2019-10-22
 */
@Service
@Slf4j
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements IAdministratorService {

    @Resource
    private JwtManager jwtManager;

    @Resource
    private AdministratorMapper administratorMapper;

    @Autowired
    private UserDetailsService userDetailsService;


    /**
     * 通过用户名密码登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public IdNameTokenVO loginByPassword(String username, String password) {
        Administrator administrator = getAdministratorByUsername(username);
        //用户不存在
        if (administrator == null) {
            throw new BusinessException(AdminErrorCode.USER_NOT_EXIST);
        }
        String encodePassword = MD5Utils.encodeByMd5AndSalt(password, administrator.getSalt());
        log.info("db password={}, form password={}", administrator.getPassword(), encodePassword);
        //密码错误
        if (!Objects.equals(administrator.getPassword(), encodePassword)) {
            throw new BusinessException(AdminErrorCode.PASSWORD_ERROR);
        }
        //添加认证
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        IdNameTokenVO idNameTokenVO = new IdNameTokenVO();
        idNameTokenVO.setId(administrator.getId());
        idNameTokenVO.setName(administrator.getUserName());
        idNameTokenVO.setToken(genToken(administrator));
        return idNameTokenVO;
    }


    @Override
    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, Constant.LOGIN_TOKEN_COOKIE_NAME);
        return Boolean.TRUE;
    }

    /**
     * 生成token
     *
     * @param administrator
     * @return
     */
    private String genToken(Administrator administrator) {
        TokenModel tokenModel = new TokenModel();
        tokenModel.setUserId(administrator.getId());
        tokenModel.setUserName(administrator.getUserName());
        tokenModel.setPwd(administrator.getPassword());
        tokenModel.setMobile(administrator.getMobile());
        tokenModel.setToken(UUID.randomUUID().toString());
        tokenModel.setSessionTime(Constant.USER_SESSION_CACHE_TIME);

        String token = jwtManager.createTokenStr(tokenModel);
        return token;
    }


    @Override
    public List<Permission> getPermissionByUserId(Long userId) {
        return administratorMapper.getPermissionByUserId(userId);
    }


    public List<Role> getRoleByUserId(Long userId) {
        return administratorMapper.getRoleByUserId(userId);
    }


    @Override
    public AdminstratorSimpleVO getInfoByUserName(String username) {
        AdminstratorSimpleVO adminstratorSimpleVO = new AdminstratorSimpleVO();
        Administrator administrator = getAdministratorByUsername(username);
        if (administrator == null) {
            throw new BusinessException(AdminErrorCode.USER_NOT_EXIST);
        }
        List<Role> roles = getRoleByUserId(administrator.getId());
        List<IdNameVO> idNameVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(roles)) {
            idNameVOList = roles.stream().map(x -> {
                IdNameVO idNameVO = new IdNameVO();
                idNameVO.setId(x.getId());
                idNameVO.setName(x.getName());
                return idNameVO;
            }).collect(Collectors.toList());
        }
        adminstratorSimpleVO.setId(administrator.getId());
        adminstratorSimpleVO.setName(administrator.getUserName());
        adminstratorSimpleVO.setAvatar(administrator.getAvatar());
        adminstratorSimpleVO.setRoles(idNameVOList);
        return adminstratorSimpleVO;
    }

    private Administrator getAdministratorByUsername(String username) {
        LambdaQueryWrapper<Administrator> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Administrator::getUserName, username);
        queryWrapper.eq(Administrator::getDelStatus, DelFlagEnum.NO.getValue());
        Administrator administrator = this.getOne(queryWrapper);
        return administrator;
    }
}
