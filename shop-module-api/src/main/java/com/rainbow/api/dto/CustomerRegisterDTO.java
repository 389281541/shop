package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CustomerRegisterDTO", description = "用户注册DTO")
public class CustomerRegisterDTO extends BaseDTO {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "输入密码")
    private String password;

    @ApiModelProperty(value = "用户电话")
    private String mobile;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "真实姓名")
    private String identityName;

    @ApiModelProperty(value = "证件类型：1 身份证，2 军官证，3 护照")
    private Integer identityType;

    @ApiModelProperty(value = "证件号码")
    private String identityNo;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别: 0-男 1-女")
    private Integer gender;

    @ApiModelProperty(value = "用户生日")
    private LocalDate birthday;

}
