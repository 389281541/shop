package com.rainbow.admin.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ShopDetailVO", description = "商铺详情VO")
public class ShopDetailVO extends ShopSimpleVO {

    @ApiModelProperty(value = "供应商开户银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "店铺地址(默认发货地址)")
    private String address;

}
