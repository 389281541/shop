package com.rainbow.admin.api.vo;


import com.rainbow.common.dto.BaseDTO;
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
@ApiModel(value = "UploadImageVO", description = "上传VO")
public class UploadImageVO extends BaseDTO {

    @ApiModelProperty(value = "图片链接")
    private String path;
}
