package net.sitir.message.messagerecord.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 消息记录信息表
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationMessageRecordDTO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 消息模板id
     */
    private Long msgTemplateId;

    /**
     * 消息渠道id
     */
    private Long msgChannelId;

    /**
     * 渠道类型id
     */
    private Long channelTypeId;

    /**
     * 发送方
     */
    private String sender;

    /**
     * 供应商ID
     */
    private String supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 接收方
     */
    private String receiver;

    /**
     * 发送的内容
     */
    private String msgContent;

    /**
     * 计费条数
     */
    private Long chargingNum;

    /**
     * 回执内容
     */
    private String reportContent;

    /**
     * 结果状态 10=发送；20=成功；30=失败；
     */
    private Integer resultState;

    /**
     * 创建人员id
     */
    private Long creator;

    /**
     * 创建人姓名
     */
    private String creatorName;

}
