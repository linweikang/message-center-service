package net.sitir.message.email.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发送邮件对象
 * @author cwl
 * 2023-8-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "发送邮件")
public class Email {
    
    /**接收方邮件 */
    @ApiModelProperty(value = "接收方邮件")
	private String[] recipients;

    /** 主题 */
    @ApiModelProperty(value = "主题")
	private String subject;

    /** 邮件内容 */
    @ApiModelProperty(value = "邮件内容")
	private String content;

	//选填
	// private String template;//模板
	// private HashMap<String, String> kvMap;// 自定义参数
	
}
