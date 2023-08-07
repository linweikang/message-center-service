package net.sitir.message.channeltype.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sitir.message.component.common.PageBO;

/**
 * <p>
 * 渠道类型信息表对象
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Data
@ApiModel(value = "分页查询渠道类型信息表")
public class PageChannelTypeParam extends PageBO {

    @ApiModelProperty(value = "关键字", example = "jijiu")
    private String keyword;

    @ApiModelProperty(value = "是否活跃，1：是；0：否；", example = "1")
    private String active;
}
