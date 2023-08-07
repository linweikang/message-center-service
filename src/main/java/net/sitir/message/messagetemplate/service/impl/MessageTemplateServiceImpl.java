package net.sitir.message.messagetemplate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sitir.message.component.common.CommonConstant;
import net.sitir.message.component.common.Pagination;
import net.sitir.message.component.exception.APIException;
import net.sitir.message.component.common.enums.ActiveEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import net.sitir.message.messagetemplate.mapper.MessageTemplateMapper;
import net.sitir.message.messagetemplate.pojo.dto.OperationMessageTemplateDTO;
import net.sitir.message.messagetemplate.pojo.dto.PageMessageTemplateDTO;
import net.sitir.message.messagetemplate.pojo.entity.MessageTemplateEntity;
import net.sitir.message.messagetemplate.pojo.vo.MessageTemplateVO;
import net.sitir.message.messagetemplate.service.MessageTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 消息模板信息表服务实现类
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Service
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplateEntity> implements MessageTemplateService {
    
    @Override
    public Pagination<MessageTemplateVO> getPage(PageMessageTemplateDTO dto) {
        IPage<MessageTemplateEntity> page = new Page<>(dto.getCurrent(), dto.getPageSize());
        // 查询条件
        LambdaQueryWrapper<MessageTemplateEntity> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(dto.getKeyword())){
            String keyword = dto.getKeyword().toUpperCase();
            queryWrapper.and(wrapper -> wrapper.like(MessageTemplateEntity::getMsgContent, keyword).or()
            .like(MessageTemplateEntity::getName, keyword));
        }
        queryWrapper.orderByDesc(MessageTemplateEntity::getCreationTime).orderByDesc(MessageTemplateEntity::getId);

        IPage<MessageTemplateEntity> iPage = this.page(page, queryWrapper);

        List<MessageTemplateVO> voList = new ArrayList<>();

        // 转为VO
        if (CollectionUtils.isEmpty(iPage.getRecords())){
            return new Pagination<>((long) voList.size(), voList);
        }

        for (MessageTemplateEntity record : iPage.getRecords()) {
            MessageTemplateVO vo = new MessageTemplateVO();
            BeanUtils.copyProperties(record,vo);
//            vo.setActiveDesc(ActiveEnum.getDescByValue(Integer.valueOf(record.getActive())));
            voList.add(vo);
        }
        return new Pagination<>(iPage.getTotal(), voList);
    }

    @Override
    public Long saveEntity(OperationMessageTemplateDTO dto) {
        String name = StringUtils.deleteWhitespace(dto.getName().toUpperCase());
        if (this.checkName(null, name)){
            throw new APIException("消息模板信息表名称已被占用");
        }

        MessageTemplateEntity entity = new MessageTemplateEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setName(name);
        boolean res = this.save(entity);
        if (!res){
            throw new APIException("保存消息模板信息表失败！");
        }
        return entity.getId();
    }

    @Override
    public Boolean updateEntity(OperationMessageTemplateDTO dto) {
        String name = StringUtils.deleteWhitespace(dto.getName().toUpperCase());

        if (this.checkName(dto.getId(), name)){
            throw new APIException("消息模板信息表名称已被占用");
        }

        MessageTemplateEntity entity = new MessageTemplateEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setName(name);
        return this.updateById(entity);
    }


    public Boolean checkName(Long id, String name) {
        LambdaQueryWrapper<MessageTemplateEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MessageTemplateEntity::getName, name);
        queryWrapper.ne((id != null && id > 0L), MessageTemplateEntity::getId, id);
        return !CollectionUtils.isEmpty(this.list(queryWrapper));
    }

}
