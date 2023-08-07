package net.sitir.message.channeltype.service.impl;

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

import net.sitir.message.channeltype.mapper.ChannelTypeMapper;
import net.sitir.message.channeltype.pojo.dto.OperationChannelTypeDTO;
import net.sitir.message.channeltype.pojo.dto.PageChannelTypeDTO;
import net.sitir.message.channeltype.pojo.entity.ChannelTypeEntity;
import net.sitir.message.channeltype.pojo.vo.ChannelTypeVO;
import net.sitir.message.channeltype.service.ChannelTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 渠道类型信息表服务实现类
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Service
public class ChannelTypeServiceImpl extends ServiceImpl<ChannelTypeMapper, ChannelTypeEntity> implements ChannelTypeService {
    
    @Override
    public Pagination<ChannelTypeVO> getPage(PageChannelTypeDTO dto) {
        IPage<ChannelTypeEntity> page = new Page<>(dto.getCurrent(), dto.getPageSize());
        // 查询条件
        LambdaQueryWrapper<ChannelTypeEntity> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(dto.getKeyword())){
            String keyword = dto.getKeyword().toUpperCase();
            queryWrapper.and(wrapper -> wrapper.like(ChannelTypeEntity::getName, keyword).or()
            .like(ChannelTypeEntity::getCode, keyword));
        }
        queryWrapper.orderByDesc(ChannelTypeEntity::getCreationTime).orderByDesc(ChannelTypeEntity::getId);

        IPage<ChannelTypeEntity> iPage = this.page(page, queryWrapper);

        List<ChannelTypeVO> voList = new ArrayList<>();

        // 转为VO
        if (CollectionUtils.isEmpty(iPage.getRecords())){
            return new Pagination<>((long) voList.size(), voList);
        }

//        for (ChannelTypeEntity record : iPage.getRecords()) {
//            ChannelTypeVO vo = new ChannelTypeVO();
//            BeanUtils.copyProperties(record,vo);
////            vo.setActiveDesc(ActiveEnum.getDescByValue(Integer.valueOf(record.getActive())));
//            voList.add(vo);
//        }
        return new Pagination<>(iPage.getTotal(), voList);
    }

    @Override
    public Long saveEntity(OperationChannelTypeDTO dto) {
        String code = StringUtils.deleteWhitespace(dto.getCode().toUpperCase());
        String name = StringUtils.deleteWhitespace(dto.getName().toUpperCase());
        if (this.checkCode(null, code)){
            throw new APIException("渠道类型信息表编码已被占用");
        }
        if (this.checkName(null, name)){
            throw new APIException("渠道类型信息表名称已被占用");
        }

        ChannelTypeEntity entity = new ChannelTypeEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setCode(code);
        entity.setName(name);
        boolean res = this.save(entity);
        if (!res){
            throw new APIException("保存渠道类型信息表失败！");
        }
        return entity.getId();
    }

    @Override
    public Boolean updateEntity(OperationChannelTypeDTO dto) {
        String code = StringUtils.deleteWhitespace(dto.getCode().toUpperCase());
        String name = StringUtils.deleteWhitespace(dto.getName().toUpperCase());

        if (this.checkCode(dto.getId(), code)){
            throw new APIException("渠道类型信息表编码已被占用");
        }
        if (this.checkName(dto.getId(), name)){
            throw new APIException("渠道类型信息表名称已被占用");
        }

        ChannelTypeEntity entity = new ChannelTypeEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setCode(code);
        entity.setName(name);
        return this.updateById(entity);
    }


    public Boolean checkCode(Long id, String code) {
        LambdaQueryWrapper<ChannelTypeEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ChannelTypeEntity::getCode, code);
        queryWrapper.ne((id != null && id > 0L), ChannelTypeEntity::getId, id);
        return !CollectionUtils.isEmpty(this.list(queryWrapper));
    }

    public Boolean checkName(Long id, String name) {
        LambdaQueryWrapper<ChannelTypeEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ChannelTypeEntity::getName, name);
        queryWrapper.ne((id != null && id > 0L), ChannelTypeEntity::getId, id);
        return !CollectionUtils.isEmpty(this.list(queryWrapper));
    }

}
