package com.rainbow.admin.api.dto;

import com.rainbow.common.annotation.EnumValidAnnotation;
import com.rainbow.common.dto.BaseDTO;
import com.rainbow.common.enums.FileTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.ByteArrayOutputStream;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileUploadDTO extends BaseDTO {

    @ApiModelProperty(value = "文件名(带后缀)")
    private String fileName;

    @ApiModelProperty(value = "字节流")
    private ByteArrayOutputStream byteStream;

    @ApiModelProperty(value = "文件类型,1-图片;2-音频;3-视频;4-excel")
    @EnumValidAnnotation(message = "文件类型错误", allowNull = false, target = FileTypeEnum.class)
    private Integer type;

    @ApiModelProperty(value = "是否需要转码：0-不需要转码；1-需要转码(默认不转码)")
    private Integer transcode = 0;


}