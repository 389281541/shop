package com.rainbow.common.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * Id，名字，头像，手机号
 *
 * @author lujinwei
 * @since 2019-02-19
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "IdNameAvatarTokenVO", description = "Id，名字，头像，token")
public class IdNameAvatarTokenVO extends IdNameAvatarVO {

    @ApiModelProperty("token")
    private String token;
}
