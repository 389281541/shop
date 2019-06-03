package com.rainbow.common.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户id和name
 *
 * @author: lujinwei
 * @since: 2018-12-17
 */
@Data
@ApiModel(value = "IdNameVO", description = "ID和Name映射")
public class IdNameVO extends BaseDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    public IdNameVO() {

    }

    public IdNameVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
