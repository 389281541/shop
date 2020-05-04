package com.rainbow.api.vo;

import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 团购活动 VO
 *
 * @author lujinwei
 * @since 2020/5/2
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GrouponActivitySimpleVO", description = "团购活动VO")
public class GrouponActivitySimpleVO extends IdNameVO {

    @ApiModelProperty(value = "团购活动编号")
    private String activityNo;

    @ApiModelProperty(value = "商品id")
    private Long spuId;

    @ApiModelProperty(value = "封面url")
    private String coverUrl;

    @ApiModelProperty(value = "活动状态：0:下线 1：上线")
    private Integer status;

    @ApiModelProperty(value = "介绍（富文本）")
    private String introduction;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "课程原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "团购价格")
    private BigDecimal groupPrice;

    @ApiModelProperty(value = "拼团人数")
    private Integer unit;

    @ApiModelProperty(value = "参与人数")
    private Long joinMembersCount;
}
