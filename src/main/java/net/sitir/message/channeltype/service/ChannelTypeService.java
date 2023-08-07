package net.sitir.message.channeltype.service;

import com.baomidou.mybatisplus.extension.service.IService;

import net.sitir.message.component.common.Pagination;
import net.sitir.message.channeltype.pojo.dto.OperationChannelTypeDTO;
import net.sitir.message.channeltype.pojo.dto.PageChannelTypeDTO;
import net.sitir.message.channeltype.pojo.entity.ChannelTypeEntity;
import net.sitir.message.channeltype.pojo.vo.ChannelTypeVO;

/**
 * <p>
 * 渠道类型信息表服务类
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
public interface ChannelTypeService extends IService<ChannelTypeEntity> {

    /**
     * 分页条件查询渠道类型信息表
     * @param dto 查询条件
     * @return 渠道类型信息表
     */
    Pagination<ChannelTypeVO> getPage(PageChannelTypeDTO dto);

    /**
     * 新增渠道类型信息表
     * @param dto 渠道类型信息表
     * @return 返回插入的主键id 0：失败；> 0：成功
     */
    Long saveEntity(OperationChannelTypeDTO dto);

    /**
     * 修改渠道类型信息表
     * @param dto 渠道类型信息表
     * @return 操作结果 true: 成功；false: 失败；
     */
    Boolean updateEntity(OperationChannelTypeDTO dto);



}
