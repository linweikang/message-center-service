package net.sitir.message.channeltype.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 渠道类型信息表
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationChannelTypeDTO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 渠道类型名称
     */
    private String name;

    /**
     * 渠道类型编码
     */
    private String code;

    /**
     * 排序
     */
    private Long seq;

    /**
     * 创建人员id
     */
    private Long creator;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 最后修改人id
     */
    private Long modifier;

    /**
     * 最后修改人名称
     */
    private String modifierName;

}
