package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
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
@ApiModel(value = "CustomerAddressSaveDTO", description = "用户地址保存传输对象")
public class CustomerAddressSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    @ApiModelProperty(value = "邮编")
    private String zip;

    @ApiModelProperty(value = "地区表中省份")
    private String province;

    @ApiModelProperty(value = "地区表中城市")
    private String city;

    @ApiModelProperty(value = "地区表中的区")
    private String district;

    @ApiModelProperty(value = "具体的地址门牌号")
    private String address;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "是否默认 0-否 1-是")
    private Integer isDefault;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long customerId;
}
