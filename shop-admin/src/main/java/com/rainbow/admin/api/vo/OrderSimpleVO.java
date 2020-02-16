package com.rainbow.admin.api.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OrderSimpleVO", description = "订单VO")
public class OrderSimpleVO extends BaseDTO {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private Long orderNo;

    @ApiModelProperty(value = "用户名称")
    private Long customerName;

    @ApiModelProperty(value = "支付方式 0：支付宝，1：微信，2：银行卡")
    private Integer payType;

    @ApiModelProperty(value = "订单状态 0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;

    @ApiModelProperty(value = "订单类型：0->正常订单；1->秒杀订单")
    private Integer orderType;

    @ApiModelProperty(value = "订单金额")
    private Long totalAmount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
