package com.rainbow.api.dto;

import com.rainbow.common.dto.PageDTO;
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
@ApiModel(value = "ShopSearchDTO", description = "商铺搜索DTO")
public class ShopSearchDTO extends PageDTO {

    @ApiModelProperty(value = "店铺名称")
    private String name;

    @ApiModelProperty(value = "类型 1-自营 2-平台")
    private Integer type;

    @ApiModelProperty(value = "店主姓名")
    private String keeperName;

    @ApiModelProperty(value = "联系电话")
    private String phoneNumber;

    @ApiModelProperty(value = "状态 0审核拒绝，1正在审核，2审核通过")
    private Integer auditStatus;
}
