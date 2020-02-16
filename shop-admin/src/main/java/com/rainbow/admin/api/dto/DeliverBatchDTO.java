package com.rainbow.admin.api.dto;


import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DeliverBatchDTO", description = "批量发货DTO")
public class DeliverBatchDTO extends BaseDTO {

    @ApiModelProperty(value = "订单批量发货参数")
    @NotEmpty(message = "订单批量发货参数不能为空")
    private List<OrderDeliverDTO> deliverList;

}
