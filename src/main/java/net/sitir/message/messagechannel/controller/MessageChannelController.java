package net.sitir.message.messagechannel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import net.sitir.message.messagechannel.service.MessageChannelService;
import net.sitir.message.component.common.CommonConstant;
import net.sitir.message.component.common.CommonResult;
import net.sitir.message.component.common.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;

import net.sitir.message.messagechannel.pojo.dto.OperationMessageChannelDTO;
import net.sitir.message.messagechannel.pojo.dto.PageMessageChannelDTO;
import net.sitir.message.messagechannel.pojo.param.AddMessageChannelParam;
import net.sitir.message.messagechannel.pojo.param.EditMessageChannelParam;
import net.sitir.message.messagechannel.pojo.param.PageMessageChannelParam;
import net.sitir.message.messagechannel.pojo.vo.MessageChannelVO;
import net.sitir.message.component.aop.bo.AccountInfo;

/**
 * <p>
 * 消息渠道信息表前端控制器
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@RestController
@Api(tags = "消息渠道信息表接口")
@Validated
@RequestMapping("/mc/channel")
public class MessageChannelController {
    @Autowired
    public MessageChannelService messageChannelService;

    @ApiOperation(value = "分页条件查询消息渠道信息表",
            notes = "查看消息渠道信息表，基本属性包括ID、名称、编码、状态等")
    @PostMapping("/get-page")
    public CommonResult<Pagination<MessageChannelVO>> getPage(@RequestBody PageMessageChannelParam param){

        PageMessageChannelDTO dto = PageMessageChannelDTO.builder()
                    .keyword(param.getKeyword())
                    .active(param.getActive())
                    .build();
        dto.setCurrent(param.getCurrent());
        dto.setPageSize(param.getPageSize());
        Pagination<MessageChannelVO> pagination = messageChannelService.getPage(dto);
        return new CommonResult<Pagination<MessageChannelVO>>().success(pagination);

    }

    /**
    * 新增消息渠道信息表
    * @param param 保存参数
    * @return 是否添加成功
    */
    @ApiOperation(value = "新增消息渠道信息表", notes = "新增消息渠道信息表")
    @PostMapping("/add")
    public CommonResult<Object> addMessageChannel(@Valid @RequestBody AddMessageChannelParam param, @ApiIgnore AccountInfo accountInfo) {
        OperationMessageChannelDTO dto = new OperationMessageChannelDTO();
        BeanUtils.copyProperties(param,dto);
        dto.setCreator(accountInfo.getPersonId());
        dto.setCreatorName(accountInfo.getAccountName());
        dto.setModifier(accountInfo.getPersonId());
        dto.setModifierName(accountInfo.getAccountName());

        messageChannelService.saveEntity(dto);
        return CommonResult.success("新增成功");

    }

    /**
    * 更新(根据主键id更新)
    * @param param 修改参数
    * @return 是否更改成功
    */
    @ApiOperation(value = "修改消息渠道信息表", notes = "根据主键id修改消息渠道信息表数据")
    @PostMapping("/edit")
    public CommonResult<Object> updateMessageChannel(@Valid @RequestBody EditMessageChannelParam param, @ApiIgnore AccountInfo accountInfo) {
        OperationMessageChannelDTO dto = new OperationMessageChannelDTO();
        BeanUtils.copyProperties(param,dto);
        dto.setModifier(accountInfo.getPersonId());
        dto.setModifierName(accountInfo.getAccountName());

        messageChannelService.updateEntity(dto);
        return CommonResult.success("修改成功");

    }



}

