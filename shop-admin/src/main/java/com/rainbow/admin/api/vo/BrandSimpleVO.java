package com.rainbow.admin.api.vo;


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
@ApiModel(value = "BrandSimpleVO", description = "类别VO")
public class BrandSimpleVO extends IdNameVO {

    @ApiModelProperty(value = "品牌logo")
    private String logo;

    @ApiModelProperty(value = "品牌描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
