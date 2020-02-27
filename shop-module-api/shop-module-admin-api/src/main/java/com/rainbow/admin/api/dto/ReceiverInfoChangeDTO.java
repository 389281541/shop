package com.rainbow.admin.api.dto;

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
@ApiModel(value = "ReceiverInfoChangeDTO", description = "收件人信息修改")
public class ReceiverInfoChangeDTO extends BaseDTO {

    @ApiModelProperty(value = "订单ID")
    private Long id;

    @ApiModelProperty(value = "收件人名字")
    private String receiverName;

    @ApiModelProperty(value = "收件人电话")
    private String receiverPhone;

    @ApiModelProperty(value = "收件人邮编")
    private String receiverPostCode;

    @ApiModelProperty(value = "收件人详细地址")
    private String receiverDetailAddress;

    @ApiModelProperty(value = "收件人省")
    private String receiverProvince;

    @ApiModelProperty(value = "收件人城市")
    private String receiverCity;

    @ApiModelProperty(value = "收件人区域")
    private String receiverRegion;

}
