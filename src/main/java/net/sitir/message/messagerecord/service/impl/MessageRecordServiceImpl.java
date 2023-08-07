package net.sitir.message.messagerecord.service.impl;

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

import net.sitir.message.messagerecord.mapper.MessageRecordMapper;
import net.sitir.message.messagerecord.pojo.dto.OperationMessageRecordDTO;
import net.sitir.message.messagerecord.pojo.dto.PageMessageRecordDTO;
import net.sitir.message.messagerecord.pojo.entity.MessageRecordEntity;
import net.sitir.message.messagerecord.pojo.vo.MessageRecordVO;
import net.sitir.message.messagerecord.service.MessageRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 消息记录信息表服务实现类
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Service
public class MessageRecordServiceImpl extends ServiceImpl<MessageRecordMapper, MessageRecordEntity> implements MessageRecordService {
    
    @Override
    public Pagination<MessageRecordVO> getPage(PageMessageRecordDTO dto) {
        IPage<MessageRecordEntity> page = new Page<>(dto.getCurrent(), dto.getPageSize());
        // 查询条件
        LambdaQueryWrapper<MessageRecordEntity> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(dto.getKeyword())){
            String keyword = dto.getKeyword().toUpperCase();
            queryWrapper.and(wrapper -> wrapper.like(MessageRecordEntity::getSender, keyword).or()
            .like(MessageRecordEntity::getMsgChannelId, keyword));
        }
//        queryWrapper.eq(StringUtils.isNotBlank(dto.getActive()), MessageRecordEntity::getActive, dto.getActive());
        queryWrapper.orderByDesc(MessageRecordEntity::getCreationTime).orderByDesc(MessageRecordEntity::getId);

        IPage<MessageRecordEntity> iPage = this.page(page, queryWrapper);

        List<MessageRecordVO> voList = new ArrayList<>();

        // 转为VO
        if (CollectionUtils.isEmpty(iPage.getRecords())){
            return new Pagination<>((long) voList.size(), voList);
        }

        for (MessageRecordEntity record : iPage.getRecords()) {
            MessageRecordVO vo = new MessageRecordVO();
            BeanUtils.copyProperties(record,vo);
//            vo.setActiveDesc(ActiveEnum.getDescByValue(Integer.valueOf(record.getActive())));
            voList.add(vo);
        }
        return new Pagination<>(iPage.getTotal(), voList);
    }

    @Override
    public Long saveEntity(OperationMessageRecordDTO dto) {

        MessageRecordEntity entity = new MessageRecordEntity();
        BeanUtils.copyProperties(dto,entity);

        boolean res = this.save(entity);
        if (!res){
            throw new APIException("保存消息记录信息表失败！");
        }
        return entity.getId();
    }

    @Override
    public Boolean updateEntity(OperationMessageRecordDTO dto) {

        MessageRecordEntity entity = new MessageRecordEntity();
        BeanUtils.copyProperties(dto,entity);

        return this.updateById(entity);
    }


}
