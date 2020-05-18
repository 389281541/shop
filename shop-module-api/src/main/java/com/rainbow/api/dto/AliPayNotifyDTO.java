package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付宝通知DTO
 *
 * @author lujinwei
 * @since 2020/5/18
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AliPayNotifyDTO", description = "支付宝消息通知DTO")
public class AliPayNotifyDTO extends BaseDTO {

    @ApiModelProperty(value = "商户订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "订单金额")
    private String totalAmount;

    @ApiModelProperty(value = "编码")
    private String charset;

    @ApiModelProperty(value = "签名")
    private String sign;

    @ApiModelProperty(value = "支付宝交易号")
    private String tradeNo;

    @ApiModelProperty(value = "交易状态")
    private String tradeStatus;

    @ApiModelProperty(value = "authAppId")
    private String authAppId;

    @ApiModelProperty(value = "版本")
    private String version;

    @ApiModelProperty(value = "appId")
    private String appId;

    @ApiModelProperty(value = "签名类型")
    private String signType;

    @ApiModelProperty(value = "卖家支付宝用户号")
    private String sellerId;

    @ApiModelProperty(value = "时间")
    private String timestamp;
}
