package com.rainbow.api.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 积分使用规则设置
 *
 * @author lujinwei
 * @since 2020/3/19
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HomeAdvertiseVO", description = "首页广告VO")
public class IntegrationRuleSettingVO extends BaseDTO {

    @ApiModelProperty(value = "每块元需要抵扣的积分数")
    private Integer deductionPerYuan;

    @ApiModelProperty(value = "每笔订单最高抵用百分比")
    private Integer maxPercentPerOrder;

    @ApiModelProperty(value = "使用积分最小单位100")
    private Integer useUnit;

    @ApiModelProperty(value = "是否可以和优惠券同用；0->不可以；1->可以")
    private Integer couponStatus;
}
