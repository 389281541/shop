package com.rainbow.api.vo;

import com.rainbow.api.entity.Customer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 团购活动详情VO
 *
 * @author lujinwei
 * @since 2020/5/2
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GrouponActivityDetailVO", description = "团购活动VO")
public class GrouponActivityDetailVO extends GrouponActivitySimpleVO {

    @ApiModelProperty(value = "团购成员")
    private List<Customer> members;

}
