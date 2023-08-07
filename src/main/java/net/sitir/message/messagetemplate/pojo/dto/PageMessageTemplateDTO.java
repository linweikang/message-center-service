package net.sitir.message.messagetemplate.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sitir.message.component.common.PageBO;

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
public class PageMessageTemplateDTO extends PageBO {
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 是否活跃，1：是；0：否；
     */
    private String active;
}
