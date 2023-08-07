package net.sitir.message.messagechannel.service.impl;

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

import net.sitir.message.messagechannel.mapper.MessageChannelMapper;
import net.sitir.message.messagechannel.pojo.dto.OperationMessageChannelDTO;
import net.sitir.message.messagechannel.pojo.dto.PageMessageChannelDTO;
import net.sitir.message.messagechannel.pojo.entity.MessageChannelEntity;
import net.sitir.message.messagechannel.pojo.vo.MessageChannelVO;
import net.sitir.message.messagechannel.service.MessageChannelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 消息渠道信息表服务实现类
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Service
public class MessageChannelServiceImpl extends ServiceImpl<MessageChannelMapper, MessageChannelEntity> implements MessageChannelService {
    
    @Override
    public Pagination<MessageChannelVO> getPage(PageMessageChannelDTO dto) {
        IPage<MessageChannelEntity> page = new Page<>(dto.getCurrent(), dto.getPageSize());
        // 查询条件
        LambdaQueryWrapper<MessageChannelEntity> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(dto.getKeyword())){
            String keyword = dto.getKeyword().toUpperCase();
            queryWrapper.and(wrapper -> wrapper.like(MessageChannelEntity::getName, keyword).or()
            .like(MessageChannelEntity::getCode, keyword));
        }
        queryWrapper.orderByDesc(MessageChannelEntity::getCreationTime).orderByDesc(MessageChannelEntity::getId);

        IPage<MessageChannelEntity> iPage = this.page(page, queryWrapper);

        List<MessageChannelVO> voList = new ArrayList<>();

        // 转为VO
        if (CollectionUtils.isEmpty(iPage.getRecords())){
            return new Pagination<>((long) voList.size(), voList);
        }

        for (MessageChannelEntity record : iPage.getRecords()) {
            MessageChannelVO vo = new MessageChannelVO();
            BeanUtils.copyProperties(record,vo);
//            vo.setActiveDesc(ActiveEnum.getDescByValue(Integer.valueOf(record.getActive())));
            voList.add(vo);
        }
        return new Pagination<>(iPage.getTotal(), voList);
    }

    @Override
    public Long saveEntity(OperationMessageChannelDTO dto) {
        String code = StringUtils.deleteWhitespace(dto.getCode().toUpperCase());
        String name = StringUtils.deleteWhitespace(dto.getName().toUpperCase());
        if (this.checkCode(null, code)){
            throw new APIException("消息渠道信息表编码已被占用");
        }
        if (this.checkName(null, name)){
            throw new APIException("消息渠道信息表名称已被占用");
        }

        MessageChannelEntity entity = new MessageChannelEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setCode(code);
        entity.setName(name);
        boolean res = this.save(entity);
        if (!res){
            throw new APIException("保存消息渠道信息表失败！");
        }
        return entity.getId();
    }

    @Override
    public Boolean updateEntity(OperationMessageChannelDTO dto) {
        String code = StringUtils.deleteWhitespace(dto.getCode().toUpperCase());
        String name = StringUtils.deleteWhitespace(dto.getName().toUpperCase());

        if (this.checkCode(dto.getId(), code)){
            throw new APIException("消息渠道信息表编码已被占用");
        }
        if (this.checkName(dto.getId(), name)){
            throw new APIException("消息渠道信息表名称已被占用");
        }

        MessageChannelEntity entity = new MessageChannelEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setCode(code);
        entity.setName(name);
        return this.updateById(entity);
    }


    public Boolean checkCode(Long id, String code) {
        LambdaQueryWrapper<MessageChannelEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MessageChannelEntity::getCode, code);
        queryWrapper.ne((id != null && id > 0L), MessageChannelEntity::getId, id);
        return !CollectionUtils.isEmpty(this.list(queryWrapper));
    }

    public Boolean checkName(Long id, String name) {
        LambdaQueryWrapper<MessageChannelEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MessageChannelEntity::getName, name);
        queryWrapper.ne((id != null && id > 0L), MessageChannelEntity::getId, id);
        return !CollectionUtils.isEmpty(this.list(queryWrapper));
    }

}
