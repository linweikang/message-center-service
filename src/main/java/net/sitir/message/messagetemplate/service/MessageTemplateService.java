package net.sitir.message.messagetemplate.service;

import com.baomidou.mybatisplus.extension.service.IService;

import net.sitir.message.component.common.Pagination;
import net.sitir.message.messagetemplate.pojo.dto.OperationMessageTemplateDTO;
import net.sitir.message.messagetemplate.pojo.dto.PageMessageTemplateDTO;
import net.sitir.message.messagetemplate.pojo.entity.MessageTemplateEntity;
import net.sitir.message.messagetemplate.pojo.vo.MessageTemplateVO;

/**
 * <p>
 * 消息模板信息表服务类
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
public interface MessageTemplateService extends IService<MessageTemplateEntity> {

    /**
     * 分页条件查询消息模板信息表
     * @param dto 查询条件
     * @return 消息模板信息表
     */
    Pagination<MessageTemplateVO> getPage(PageMessageTemplateDTO dto);

    /**
     * 新增消息模板信息表
     * @param dto 消息模板信息表
     * @return 返回插入的主键id 0：失败；> 0：成功
     */
    Long saveEntity(OperationMessageTemplateDTO dto);

    /**
     * 修改消息模板信息表
     * @param dto 消息模板信息表
     * @return 操作结果 true: 成功；false: 失败；
     */
    Boolean updateEntity(OperationMessageTemplateDTO dto);


}
