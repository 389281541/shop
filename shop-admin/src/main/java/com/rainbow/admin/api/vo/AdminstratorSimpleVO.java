package com.rainbow.admin.api.vo;

import com.rainbow.admin.entity.Role;
import com.rainbow.common.vo.IdNameAvatarVO;
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
@ApiModel(value = "AdminstratorSimpleVO", description = "管理者信息VO")
public class AdminstratorSimpleVO extends IdNameAvatarVO {

    @ApiModelProperty(value = "角色")
    private List<IdNameVO> roles;
}
