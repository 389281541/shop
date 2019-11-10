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
@ApiModel(value = "ItemSimpleVO", description = "类别VO")
public class ItemSimpleVO extends IdNameVO {

    @ApiModelProperty(value = "编号")
    private Long itemNo;

    @ApiModelProperty(value = "级别")
    private String level;

    @ApiModelProperty(value = "是否父类")
    private Boolean parent;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "父类名称")
    private String parentName;

}
