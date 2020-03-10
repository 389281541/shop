package com.rainbow.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 商品详情VO
 *
 * @author lujinwei
 * @since 2020/3/8
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GoodsDetailVO", description = "商品详情VO")
public class GoodsDetailVO extends GoodsSimpleVO {



}
