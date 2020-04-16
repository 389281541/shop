package com.rainbow.api.vo;

import com.rainbow.common.dto.BaseDTO;
import com.rainbow.common.model.HMS;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 秒杀主题VO
 *
 * @author lujinwei
 * @since 2020/4/14
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "FlashThemeVO", description = "秒杀活动VO")
public class FlashThemeVO extends BaseDTO {

    @ApiModelProperty(value = "秒杀活动主题")
    private String theme;

    @ApiModelProperty(value = "秒杀倒计时")
    private HMS hms;

    @ApiModelProperty(value = "秒杀进程")
    private Integer flashStatus;

    @ApiModelProperty(value = "秒杀商品列表")
    private List<FlashGoodsSimpleVO> flashGoodsList;

}
