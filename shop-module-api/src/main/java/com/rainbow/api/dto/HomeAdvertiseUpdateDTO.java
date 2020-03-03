package com.rainbow.api.dto;

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
@ApiModel(value = "HomeAdvertiseUpdateDTO", description = "首页广告更新DTO")
public class HomeAdvertiseUpdateDTO extends HomeAdvertiseSaveDTO {

    @ApiModelProperty(value = "主键ID")
    private Long id;
}
