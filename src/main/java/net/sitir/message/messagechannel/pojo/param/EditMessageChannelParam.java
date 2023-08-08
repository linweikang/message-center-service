package net.sitir.message.messagechannel.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

/**
 * <p>
 * 修改消息渠道信息表对象
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@ApiModel(value = "修改消息渠道信息表对象")
public class EditMessageChannelParam {

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

}
