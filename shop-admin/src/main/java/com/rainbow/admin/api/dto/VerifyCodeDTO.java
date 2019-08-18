package com.rainbow.admin.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "VerifyCodeDTO", description = "图形验证码传输对象")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerifyCodeDTO  extends BaseDTO {

    @ApiModelProperty(value = "表单验证码")
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
}
