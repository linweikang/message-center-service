package net.sitir.message.channeltype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import net.sitir.message.channeltype.service.ChannelTypeService;
import net.sitir.message.component.common.CommonConstant;
import net.sitir.message.component.common.CommonResult;
import net.sitir.message.component.common.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;

import net.sitir.message.channeltype.pojo.dto.OperationChannelTypeDTO;
import net.sitir.message.channeltype.pojo.dto.PageChannelTypeDTO;
import net.sitir.message.channeltype.pojo.param.AddChannelTypeParam;
import net.sitir.message.channeltype.pojo.param.EditChannelTypeParam;
import net.sitir.message.channeltype.pojo.param.PageChannelTypeParam;
import net.sitir.message.channeltype.pojo.vo.ChannelTypeVO;
import net.sitir.message.component.aop.bo.AccountInfo;

/**
 * <p>
 * 渠道类型信息表前端控制器
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@RestController
@Api(tags = "渠道类型信息表接口")
@Validated
@RequestMapping("/mc/channel-type")
public class ChannelTypeController {
    @Autowired
    public ChannelTypeService channelTypeService;

    @ApiOperation(value = "分页条件查询渠道类型信息表",
            notes = "查看渠道类型信息表，基本属性包括ID、名称、编码、状态等")
    @PostMapping("/get-page")
    public CommonResult<Pagination<ChannelTypeVO>> getPage(@RequestBody PageChannelTypeParam param){

        PageChannelTypeDTO dto = PageChannelTypeDTO.builder()
                    .keyword(param.getKeyword())
                    .active(param.getActive())
                    .build();
        dto.setCurrent(param.getCurrent());
        dto.setPageSize(param.getPageSize());
        Pagination<ChannelTypeVO> pagination = channelTypeService.getPage(dto);
        return new CommonResult<Pagination<ChannelTypeVO>>().success(pagination);

    }

    /**
     *
    * 新增渠道类型信息表
    * @param param 保存参数
    * @return 是否添加成功
    */
    @ApiOperation(value = "新增渠道类型信息表", notes = "新增渠道类型信息表")
    @PostMapping("/add")
    public CommonResult<Object> addChannelType(@Valid @RequestBody AddChannelTypeParam param, @ApiIgnore AccountInfo accountInfo) {
        OperationChannelTypeDTO dto = new OperationChannelTypeDTO();
        BeanUtils.copyProperties(param,dto);
        dto.setCreator(accountInfo.getPersonId());
        dto.setCreatorName(accountInfo.getAccountName());
        dto.setModifier(accountInfo.getPersonId());
        dto.setModifierName(accountInfo.getAccountName());

        channelTypeService.saveEntity(dto);
        return CommonResult.success("新增成功");

    }

    /**
    * 更新(根据主键id更新)
    * @param param 修改参数
    * @return 是否更改成功
    */
    @ApiOperation(value = "修改渠道类型信息表", notes = "根据主键id修改渠道类型信息表数据")
    @PostMapping("/edit")
    public CommonResult<Object> updateChannelType(@Valid @RequestBody EditChannelTypeParam param, @ApiIgnore AccountInfo accountInfo) {
        OperationChannelTypeDTO dto = new OperationChannelTypeDTO();
        BeanUtils.copyProperties(param,dto);
        dto.setModifier(accountInfo.getPersonId());
        dto.setModifierName(accountInfo.getAccountName());

        channelTypeService.updateEntity(dto);
        return CommonResult.success("修改成功");

    }


}

