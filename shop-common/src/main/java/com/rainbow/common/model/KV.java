package com.rainbow.common.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "KV", description = "KV传输对象")
public class KV<K,V> {

    @ApiModelProperty(value = "key")
    private K k;

    @ApiModelProperty(value = "value")
    private V v;
}
