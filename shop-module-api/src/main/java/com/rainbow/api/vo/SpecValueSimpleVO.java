package com.rainbow.api.vo;


import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpecValueSimpleVO", description = "属性值VO")
public class SpecValueSimpleVO  extends IdNameVO {

    @ApiModelProperty(value = "排序ID")
    private Long sortId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
