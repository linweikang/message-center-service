package net.sitir.message.messagerecord.pojo.entity;

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
 * 消息记录信息表
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("MC_MESSAGE_RECORD")
@KeySequence(value = "SEQ_MC_MESSAGE_RECORD")
public class MessageRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    @TableField(value = "MSG_TEMPLATE_ID")
    private Long msgTemplateId;

    @TableField(value = "MSG_CHANNEL_ID")
    private Long msgChannelId;

    @TableField(value = "CHANNEL_TYPE_ID")
    private Long channelTypeId;

    @TableField(value = "SENDER")
    private String sender;

    @TableField(value = "SUPPLIER_ID")
    private String supplierId;

    @TableField(value = "SUPPLIER_NAME")
    private String supplierName;

    @TableField(value = "RECEIVER")
    private String receiver;

    @TableField(value = "MSG_CONTENT")
    private String msgContent;

    @TableField(value = "CHARGING_NUM")
    private Long chargingNum;

    @TableField(value = "REPORT_CONTENT")
    private String reportContent;

    @TableField(value = "RESULT_STATE")
    private Integer resultState;

    @TableField(value = "DR", fill = FieldFill.INSERT)
    @TableLogic
    private Integer dr;

    @TableField(value = "CREATOR")
    private Long creator;

    @TableField(value = "CREATOR_NAME")
    private String creatorName;

    @TableField(value = "CREATION_TIME", fill = FieldFill.INSERT)
    private LocalDateTime creationTime;

}
