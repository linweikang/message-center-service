package net.sitir.message.messagetemplate.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 消息模板信息表
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationMessageTemplateDTO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 标题
     */
    private String name;

    /**
     * 消息渠道id
     */
    private Long msgChannelId;

    /**
     * 渠道类型id
     */
    private Long channelTypeId;

    /**
     * 消息类型 1=通知类消息；2=营销类消息；3=验证码类消息；
     */
    private Long msgType;

    /**
     * 消息模板内容 占位符用{$var}表示
     */
    private String msgContent;

    /**
     * 状态 0=启用；1=禁用；
     */
    private Integer state;

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
