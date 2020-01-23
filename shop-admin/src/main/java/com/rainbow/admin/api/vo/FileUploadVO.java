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
@ApiModel(value = "FileUploadVO", description = "获取上传文件token")
public class FileUploadVO extends BaseDTO {

    @ApiModelProperty(value = "传入key")
    private String key;

    @ApiModelProperty(value = "上传Token")
    private String token;

    @ApiModelProperty(value = "")
    private String url;

}
