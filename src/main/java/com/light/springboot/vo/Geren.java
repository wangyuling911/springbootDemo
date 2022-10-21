package com.light.springboot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("model的的描述")
public class Geren {

    @ApiModelProperty("账号")
    String userId;
    @ApiModelProperty("名称")
    String name;
}
