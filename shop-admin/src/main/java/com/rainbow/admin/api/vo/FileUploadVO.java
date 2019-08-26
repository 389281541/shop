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
@ApiModel(value = "FileUploadVO", description = "后端直传上传文件结果返回")
public class FileUploadVO extends BaseDTO {

    @ApiModelProperty(value = "文件地址")
    private String url;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件objectKey")
    private String objectKey;

    @ApiModelProperty(value = "文件大小(B)")
    private Long size;

    @ApiModelProperty(value = "宽")
    private Integer width;

    @ApiModelProperty(value = "高")
    private Integer height;

}
