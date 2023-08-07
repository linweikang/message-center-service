package net.sitir.message.messagerecord.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 新增消息记录信息表对象
 * </p>
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@ApiModel(value = "新增消息记录信息表")
public class AddMessageRecordParam {

    @ApiModelProperty(value = "消息模板id")
    private Long msgTemplateId;

    @ApiModelProperty(value = "消息渠道id")
    private Long msgChannelId;

    @ApiModelProperty(value = "渠道类型id")
    private Long channelTypeId;

    @ApiModelProperty(value = "发送方")
    private String sender;

    @ApiModelProperty(value = "供应商ID")
    private String supplierId;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "接收方")
    private String receiver;

    @ApiModelProperty(value = "发送的内容")
    private String msgContent;

    @ApiModelProperty(value = "计费条数")
    private Long chargingNum;

    @ApiModelProperty(value = "回执内容")
    private String reportContent;

    @ApiModelProperty(value = "结果状态 10=发送；20=成功；30=失败；")
    private Integer resultState;

}
