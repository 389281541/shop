package com.rainbow.common.dto.token;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户访问授权 DTO
 */
@Data
public class AuthToken extends BaseDTO {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "身份认证令牌")
    private String token;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String pwd;

}
