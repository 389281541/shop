package com.rainbow.api.vo;

import com.rainbow.common.vo.IdNameVO;
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
@ApiModel(value = "ShopSimpleVO", description = "商铺VO")
public class ShopSimpleVO extends IdNameVO {

    @ApiModelProperty(value = "类型 1-自营 2-平台")
    private Integer type;

    @ApiModelProperty(value = "logo")
    private String logo;

    @ApiModelProperty(value = "店主姓名")
    private String keeperName;

    @ApiModelProperty(value = "联系电话")
    private String phoneNumber;

    @ApiModelProperty(value = "状态 0审核拒绝，1正在审核，2审核通过")
    private Integer auditStatus;

    @ApiModelProperty(value = "线上注册时间(审核通过时间)")
    private LocalDateTime registerTime;

}
