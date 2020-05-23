package com.rainbow.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.api.dto.CustomerRegisterDTO;
import com.rainbow.api.dto.CustomerUpdateDTO;
import com.rainbow.api.entity.Customer;
import com.rainbow.api.enums.AdminErrorCode;
import com.rainbow.api.vo.CustomerVO;
import com.rainbow.common.constant.Constant;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.util.CookieUtils;
import com.rainbow.common.util.EncryptUtils;
import com.rainbow.common.util.MD5Utils;
import com.rainbow.common.util.PasswordUtils;
import com.rainbow.common.vo.IdNameAvatarTokenVO;
import com.rainbow.portal.mapper.CustomerMapper;
import com.rainbow.portal.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 顾客登录表 服务实现类
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@Service
@Slf4j
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    private static final Integer SALT_LEN = 8;

    @Override
    public IdNameAvatarTokenVO loginByPassword(String username, String password) {
        Customer customer = getCustomerByUsername(username);
        if (customer == null) {
            throw new BusinessException(AdminErrorCode.USER_NOT_EXIST);
        }
        String encodePassword = MD5Utils.encodeByMd5AndSalt(password, customer.getSalt());
        log.info("db password={}, form password={}", customer.getPassword(), encodePassword);
        //密码错误
        if (!Objects.equals(customer.getPassword(), encodePassword)) {
            throw new BusinessException(AdminErrorCode.PASSWORD_ERROR);
        }
        //更新上次登录时间
        Customer customerUpdate = new Customer();
        customerUpdate.setUpdateTime(LocalDateTime.now());
        customerUpdate.setLastLoginTime(LocalDateTime.now());
        baseMapper.updateById(customerUpdate);

        String token = EncryptUtils.strEncode(customer.getId(), customer.getUserName(), customer.getMobile(), null);

        IdNameAvatarTokenVO idNameAvatarVO = new IdNameAvatarTokenVO();
        idNameAvatarVO.setId(customer.getId());
        idNameAvatarVO.setName(customer.getUserName());
        idNameAvatarVO.setAvatar(customer.getAvatar());
        idNameAvatarVO.setToken(token);
        return idNameAvatarVO;
    }

    private Customer getCustomerByUsername(String username) {
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Customer::getUserName, username);
        queryWrapper.eq(Customer::getUserStatus, DelFlagEnum.NO.getValue());
        Customer customer = this.getOne(queryWrapper);
        return customer;
    }

    @Override
    public Boolean register(CustomerRegisterDTO param) {
        Customer cst = getCustomerByUsername(param.getUserName());
        if (cst != null) {
            throw new BusinessException(AdminErrorCode.USER_ALREADY_EXIST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(param, customer);
        //使用状态
        customer.setUserStatus(BooleanEnum.NO.getValue());
        //加密盐
        String salt = PasswordUtils.randomStr(SALT_LEN);
        customer.setSalt(salt);
        //加密密码
        String dbPassword = MD5Utils.encodeByMd5AndSalt(param.getPassword(), salt);
        customer.setPassword(dbPassword);
        customer.setIntegration(0);
        customer.setCreateTime(LocalDateTime.now());
        Integer res = baseMapper.insert(customer);
        return res > 0;
    }

    @Override
    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, Constant.LOGIN_TOKEN_COOKIE_NAME);
        return Boolean.TRUE;
    }


    @Override
    public CustomerVO getInfo(Long userId) {
        CustomerVO customerVO = new CustomerVO();
        Customer customer = baseMapper.selectById(userId);
        BeanUtils.copyProperties(customer, customerVO);
        return customerVO;
    }


    @Override
    public Integer updateInfo(CustomerUpdateDTO param) {
        return baseMapper.updateInfo(param);
    }

}
