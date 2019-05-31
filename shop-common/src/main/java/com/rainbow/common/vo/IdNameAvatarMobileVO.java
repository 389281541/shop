package com.rainbow.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * Id，名字，头像，手机号
 *
 * @author lujinwei
 * @Date 2019-02-19 19:04
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "IdNameAvatarMobileVO", description = "Id，名字，头像，手机号")
public class IdNameAvatarMobileVO extends IdNameAvatarVO {

    @ApiModelProperty("手机号")
    private String mobile;
}
