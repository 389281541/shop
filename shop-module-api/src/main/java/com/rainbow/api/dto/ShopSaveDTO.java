package com.rainbow.api.dto;

import com.rainbow.api.enums.ShopTypeEnum;
import com.rainbow.common.annotation.EnumValidAnnotation;
import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ShopSaveDTO", description = "商铺保存传输对象")
public class ShopSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "店铺名称")
    private String name;

    @ApiModelProperty(value = "店铺类型：1.自营，2.平台")
    @EnumValidAnnotation(message = "店铺类型错误" , allowNull = false, target = ShopTypeEnum.class)
    private Integer type;

    @ApiModelProperty(value = "店主姓名")
    @NotBlank(message = "店主姓名不能为空")
    private String keeperName;

    @ApiModelProperty(value = "联系电话")
    @NotBlank(message = "联系电话不能为空")
    private String phoneNumber;

    @ApiModelProperty(value = "供应商开户银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    @NotBlank(message = "银行账号不能为空")
    private String bankAccount;

    @ApiModelProperty(value = "店铺地址(默认发货地址)")
    private String address;

    @ApiModelProperty(value = "店铺商标")
    private String logo;

}
