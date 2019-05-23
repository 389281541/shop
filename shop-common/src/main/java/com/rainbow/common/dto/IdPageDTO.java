package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ID分页传输对象
 * @author lujinwei
 * @date 2019/2/14 上午11:26
 */
@Data
@ApiModel(value = "IdPageDTO", description = "ID分页传输对象")
public class IdPageDTO extends PageDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

}
