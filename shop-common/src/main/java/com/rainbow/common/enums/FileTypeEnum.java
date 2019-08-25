package com.rainbow.common.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileTypeEnum implements IEnum<Integer> {

    PICTURE(1, "图片"),
    AUDIO(2, "音频"),
    VIDEO(3, "视频"),
    EXCEL(4, "Excel"),
    ;
    private Integer value;
    private String desc;

}
