package com.rainbow.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TokenModel
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TokenModel", description = "登陆数据传输对象")
public class TokenModel {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户登录名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "用户绑定手机")
    private String mobile;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "session时间")
    private Integer sessionTime;

}
