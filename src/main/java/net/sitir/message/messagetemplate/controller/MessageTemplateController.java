package net.sitir.message.messagetemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import net.sitir.message.messagetemplate.service.MessageTemplateService;
import net.sitir.message.component.common.CommonConstant;
import net.sitir.message.component.common.CommonResult;
import net.sitir.message.component.common.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;

import net.sitir.message.messagetemplate.pojo.dto.OperationMessageTemplateDTO;
import net.sitir.message.messagetemplate.pojo.dto.PageMessageTemplateDTO;
import net.sitir.message.messagetemplate.pojo.param.AddMessageTemplateParam;
import net.sitir.message.messagetemplate.pojo.param.EditMessageTemplateParam;
import net.sitir.message.messagetemplate.pojo.param.PageMessageTemplateParam;
import net.sitir.message.messagetemplate.pojo.vo.MessageTemplateVO;
import net.sitir.message.component.aop.bo.AccountInfo;

/**
 * <p>
 * 消息模板信息表前端控制器
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@RestController
@Api(tags = "消息模板信息表接口")
@Validated
@RequestMapping("/quality/messageTemplateEntity")
public class MessageTemplateController {
    @Autowired
    public MessageTemplateService messageTemplateEntityService;

    @ApiOperation(value = "分页条件查询消息模板信息表",
            notes = "查看消息模板信息表，基本属性包括ID、名称、编码、状态等")
    @PostMapping("/get-page")
    public CommonResult<Pagination<MessageTemplateVO>> getPage(@RequestBody PageMessageTemplateParam param){

        PageMessageTemplateDTO dto = PageMessageTemplateDTO.builder()
                    .keyword(param.getKeyword())
                    .active(param.getActive())
                    .build();
        dto.setCurrent(param.getCurrent());
        dto.setPageSize(param.getPageSize());
        Pagination<MessageTemplateVO> pagination = messageTemplateEntityService.getPage(dto);
        return new CommonResult<Pagination<MessageTemplateVO>>().success(pagination);

    }

    /**
    * 新增消息模板信息表
    * @param param 保存参数
    * @return 是否添加成功
    */
    @ApiOperation(value = "新增消息模板信息表", notes = "新增消息模板信息表")
    @PostMapping("/add")
    public CommonResult<Object> addMessageTemplate(@Valid @RequestBody AddMessageTemplateParam param, @ApiIgnore AccountInfo accountInfo) {
        OperationMessageTemplateDTO dto = new OperationMessageTemplateDTO();
        BeanUtils.copyProperties(param,dto);
        dto.setCreator(accountInfo.getPersonId());
        dto.setCreatorName(accountInfo.getAccountName());
        dto.setModifier(accountInfo.getPersonId());
        dto.setModifierName(accountInfo.getAccountName());

        messageTemplateEntityService.saveEntity(dto);
        return CommonResult.success("新增成功");

    }

    /**
    * 更新(根据主键id更新)
    * @param param 修改参数
    * @return 是否更改成功
    */
    @ApiOperation(value = "修改消息模板信息表", notes = "根据主键id修改消息模板信息表数据")
    @PostMapping("/edit")
    public CommonResult<Object> updateMessageTemplate(@Valid @RequestBody EditMessageTemplateParam param, @ApiIgnore AccountInfo accountInfo) {
        OperationMessageTemplateDTO dto = new OperationMessageTemplateDTO();
        BeanUtils.copyProperties(param,dto);
        dto.setModifier(accountInfo.getPersonId());
        dto.setModifierName(accountInfo.getAccountName());

        messageTemplateEntityService.updateEntity(dto);
        return CommonResult.success("修改成功");

    }



}

