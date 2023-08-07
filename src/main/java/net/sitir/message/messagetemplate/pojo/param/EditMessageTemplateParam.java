package net.sitir.message.messagetemplate.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 修改消息模板信息表对象
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@ApiModel(value = "修改消息模板信息表对象")
public class EditMessageTemplateParam {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String name;

    @ApiModelProperty(value = "消息渠道id")
    private Long msgChannelId;

    @ApiModelProperty(value = "渠道类型id")
    private Long channelTypeId;

    @ApiModelProperty(value = "消息类型 1=通知类消息；2=营销类消息；3=验证码类消息；")
    private Long msgType;

    @ApiModelProperty(value = "消息模板内容 占位符用{$var}表示")
    private String msgContent;

    @ApiModelProperty(value = "状态 0=启用；1=禁用；")
    private Integer state;

}
