package com.rainbow.common.dto.token;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户访问授权 DTO
 *
 * @author lujinwei
 * @since 2019-06-13
 */
@Data
public class AuthToken extends BaseDTO {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "openId", notes = "用户的openId，h5里是公众号openId，小程序里是小程序openId")
    private String openId;

    @ApiModelProperty(value = "当前时间long")
    private Long ct;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "当前环境，online、beta、dev、test、pre")
    private String env;

}
