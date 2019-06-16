package com.rainbow.admin.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户登陆DTO
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "LoginDTO", description = "登陆数据传输对象")
public class LoginDTO extends BaseDTO {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空！")
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空！")
    private String password;

    @ApiModelProperty(value = "图片验证码")
    @NotBlank(message = "图片验证码不能为空！")
    private String captcha;

}
