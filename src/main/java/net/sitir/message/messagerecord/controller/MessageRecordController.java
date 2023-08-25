package net.sitir.message.messagerecord.controller;

import net.sitir.message.email.domain.Email;
import net.sitir.message.email.service.EmailSendService;
import net.sitir.message.utils.SendSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import net.sitir.message.messagerecord.service.MessageRecordService;
import net.sitir.message.component.common.CommonConstant;
import net.sitir.message.component.common.CommonResult;
import net.sitir.message.component.common.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;

import net.sitir.message.messagerecord.pojo.dto.OperationMessageRecordDTO;
import net.sitir.message.messagerecord.pojo.dto.PageMessageRecordDTO;
import net.sitir.message.messagerecord.pojo.param.AddMessageRecordParam;
import net.sitir.message.messagerecord.pojo.param.EditMessageRecordParam;
import net.sitir.message.messagerecord.pojo.param.PageMessageRecordParam;
import net.sitir.message.messagerecord.pojo.vo.MessageRecordVO;
import net.sitir.message.component.aop.bo.AccountInfo;

/**
 * <p>
 * 消息记录信息表前端控制器
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@RestController
@Api(tags = "消息记录信息表接口")
@Validated
@RequestMapping("/mc/record")
public class MessageRecordController {
    @Autowired
    public MessageRecordService messageRecordService;

    @Autowired
    private EmailSendService emailSendService;

    @Autowired
    private SendSmsUtil smsUtil;

    @ApiOperation(value = "分页条件查询消息记录信息表",
            notes = "查看消息记录信息表，基本属性包括ID、名称、编码、状态等")
    @PostMapping("/get-page")
    public CommonResult<Pagination<MessageRecordVO>> getPage(@RequestBody PageMessageRecordParam param){

        PageMessageRecordDTO dto = PageMessageRecordDTO.builder()
                    .keyword(param.getKeyword())
                    .active(param.getActive())
                    .build();
        dto.setCurrent(param.getCurrent());
        dto.setPageSize(param.getPageSize());
        Pagination<MessageRecordVO> pagination = messageRecordService.getPage(dto);
        return new CommonResult<Pagination<MessageRecordVO>>().success(pagination);

    }

    /**
    * 新增消息记录信息表
    * @param param 保存参数
    * @return 是否添加成功
    */
    @ApiOperation(value = "新增消息记录信息表", notes = "新增消息记录信息表")
    @PostMapping("/add")
    public CommonResult<Object> addMessageRecord(@Valid @RequestBody AddMessageRecordParam param, @ApiIgnore AccountInfo accountInfo) {
        OperationMessageRecordDTO dto = new OperationMessageRecordDTO();
        BeanUtils.copyProperties(param,dto);
        dto.setCreator(accountInfo.getPersonId());
        dto.setCreatorName(accountInfo.getAccountName());

        messageRecordService.saveEntity(dto);
        return CommonResult.success("新增成功");

    }

    /**
    * 更新(根据主键id更新)
    * @param param 修改参数
    * @return 是否更改成功
    */
    @ApiOperation(value = "修改消息记录信息表", notes = "根据主键id修改消息记录信息表数据")
    @PostMapping("/edit")
    public CommonResult<Object> updateMessageRecord(@Valid @RequestBody EditMessageRecordParam param, @ApiIgnore AccountInfo accountInfo) {
        OperationMessageRecordDTO dto = new OperationMessageRecordDTO();
        BeanUtils.copyProperties(param,dto);

        messageRecordService.updateEntity(dto);
        return CommonResult.success("修改成功");

    }

    @ApiOperation(value = "根据邮箱发送邮件接口", notes = "根据邮箱发送邮件接口")
    @PostMapping("/sendEmail")
    public CommonResult<Object> sendEmail(@RequestBody Email email) throws Exception {
        emailSendService.send(email);
        return CommonResult.success("发送成功！！！");
    }

    @ApiOperation(value = "根据手机号码发送验证码短信接口", notes = "根据手机号码发送验证码短信接口")
    @GetMapping("/sendSms")
    public CommonResult<Object> sendSms(@ApiParam(value = "手机号码", required = true, example = "11122223333") @RequestParam("phone") String phone) {
        String res = smsUtil.sendSms(phone);
        return CommonResult.success(res);
    }

}

