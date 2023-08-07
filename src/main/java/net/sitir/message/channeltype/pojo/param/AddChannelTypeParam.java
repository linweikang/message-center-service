package net.sitir.message.channeltype.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 新增渠道类型信息表对象
 * </p>
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@ApiModel(value = "新增渠道类型信息表")
public class AddChannelTypeParam {

    @ApiModelProperty(value = "渠道类型名称")
    private String name;

    @ApiModelProperty(value = "渠道类型编码")
    private String code;

    @ApiModelProperty(value = "排序")
    private Long seq;

}
