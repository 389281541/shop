package com.rainbow.admin.api.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.rainbow.common.vo.IdNameVO;
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
@ApiModel(value = "SpecValueSimpleVO", description = "属性值VO")
public class SpecValueSimpleVO  extends IdNameVO {

    @ApiModelProperty(value = "属性名ID")
    private Long specNameId;

    @ApiModelProperty(value = "排序ID")
    private Integer sortId;

}
