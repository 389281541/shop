package com.rainbow.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 用户id、name、avatar和token
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "IdNameTokenVO", description = "Id，名字，token")
public class IdNameTokenVO extends IdNameVO {

    @ApiModelProperty("token")
    private String token;
}
