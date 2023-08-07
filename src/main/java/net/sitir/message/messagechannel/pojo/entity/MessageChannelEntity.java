package net.sitir.message.messagechannel.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 消息渠道信息表
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("MC_MESSAGE_CHANNEL")
@KeySequence(value = "SEQ_MC_MESSAGE_CHANNEL")
public class MessageChannelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    @TableField(value = "NAME")
    private String name;

    @TableField(value = "CODE")
    private String code;

    @TableField(value = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @TableField(value = "COST_CAP")
    private BigDecimal costCap;

    @TableField(value = "CHANNEL_TYPE_ID")
    private Long channelTypeId;

    @TableField(value = "STATE")
    private Integer state;

    @TableField(value = "REMARK")
    private String remark;

    @TableField(value = "SEQ")
    private Long seq;

    @TableField(value = "DR", fill = FieldFill.INSERT)
    @TableLogic
    private Integer dr;

    @TableField(value = "CREATOR")
    private Long creator;

    @TableField(value = "CREATOR_NAME")
    private String creatorName;

    @TableField(value = "CREATION_TIME", fill = FieldFill.INSERT)
    private LocalDateTime creationTime;

    @TableField(value = "MODIFIER")
    private Long modifier;

    @TableField(value = "MODIFIER_NAME")
    private String modifierName;

    @TableField(value = "MODIFIED_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;

}
