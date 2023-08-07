package net.sitir.message.channeltype.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 渠道类型信息表
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ChannelTypeVO", description = "渠道类型信息表")
public class ChannelTypeVO {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "渠道类型名称")
    private String name;

    @ApiModelProperty(value = "渠道类型编码")
    private String code;

    @ApiModelProperty(value = "排序")
    private Long seq;

    @ApiModelProperty(value = "是否活跃的中文值，启用或者停用", example = "启用")
    private String activeDesc;
}
