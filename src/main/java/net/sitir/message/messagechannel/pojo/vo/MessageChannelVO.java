package net.sitir.message.messagechannel.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * <p>
 * 消息渠道信息表
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MessageChannelVO", description = "消息渠道信息表")
public class MessageChannelVO {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "消息渠道名称")
    private String name;

    @ApiModelProperty(value = "消息渠道编码")
    private String code;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "费用上限")
    private BigDecimal costCap;

    @ApiModelProperty(value = "渠道类型id")
    private Long channelTypeId;

    @ApiModelProperty(value = "状态 0=启用；1=禁用；")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "排序")
    private Long seq;

    @ApiModelProperty(value = "是否活跃的中文值，启用或者停用", example = "启用")
    private String activeDesc;
}
