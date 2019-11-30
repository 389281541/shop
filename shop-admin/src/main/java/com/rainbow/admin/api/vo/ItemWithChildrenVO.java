package com.rainbow.admin.api.vo;

import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ItemWithSubVO", description = "类别VO")
public class ItemWithChildrenVO extends IdNameVO {

    @ApiModelProperty(value = "子类名列表")
    private List<IdNameVO> children;
}
