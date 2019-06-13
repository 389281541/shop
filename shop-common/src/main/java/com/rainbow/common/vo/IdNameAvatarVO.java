package com.rainbow.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 用户id、name和avatar
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "IdNameAvatarVO", description = "Id，名字，头像vo")
public class IdNameAvatarVO extends IdNameVO {

    @ApiModelProperty(value = "头像")
    private String avatar;
}
