package net.sitir.message.messagechannel.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class OperationMessageChannelDTO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 消息渠道名称
     */
    private String name;

    /**
     * 消息渠道编码
     */
    private String code;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 费用上限
     */
    private BigDecimal costCap;

    /**
     * 渠道类型id
     */
    private Long channelTypeId;

    /**
     * 状态 0=启用；1=禁用；
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Long seq;

    /**
     * 创建人员id
     */
    private Long creator;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 最后修改人id
     */
    private Long modifier;

    /**
     * 最后修改人名称
     */
    private String modifierName;

}
