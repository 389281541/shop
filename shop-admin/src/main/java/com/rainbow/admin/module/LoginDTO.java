package com.rainbow.admin.module;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * TODO:
 *
 * @author lujinwei
 * @since 2019-05-31
 */
public class LoginDTO extends BaseDTO {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String verifyCode;
}
