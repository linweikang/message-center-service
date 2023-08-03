package net.sitir.message.component.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 返回结果类
 * @author linweikang
 * @createDate 2023/8/3
 */
@ApiModel(value = "返回结果")
@Data
@AllArgsConstructor
@Builder
public class CommonResult<T> {

    @ApiModelProperty(value = "返回的状态。0：失败；1：成功。", example = "1", allowableValues = "0,1")
    private Integer status;
    @ApiModelProperty(value = "返回的消息", example = "成功")
    private String message;
    @ApiModelProperty(value = "返回的数据")
    private T resultBody;

    public static CommonResult<Object> success() {
        return new CommonResult<>(CommonConstant.STATUS_SUCCESS, CommonConstant.STATUS_SUCCESS_DESC);
    }

    public static CommonResult<Object> success(String message) {
        return new CommonResult<>(CommonConstant.STATUS_SUCCESS, message);
    }

    public CommonResult<T> success(String message, T resultBody) {
        CommonResult<T> result = new CommonResult<>(CommonConstant.STATUS_SUCCESS);
        result.setMessage(message);
        result.setResultBody(resultBody);
        return result;
    }

    public CommonResult<T> success(T resultBody) {
        CommonResult<T> result = new CommonResult<>();
        result.setStatus(CommonConstant.STATUS_SUCCESS);
        result.setMessage(CommonConstant.STATUS_SUCCESS_DESC);
        result.setResultBody(resultBody);
        return result;
    }

    public static CommonResult<Object> error() {
        return new CommonResult<>(CommonConstant.STATUS_ERROR, CommonConstant.STATUS_ERROR_DESC);
    }

    public static CommonResult<Object> error(String message) {
        CommonResult<Object> result = CommonResult.error();
        result.setMessage(message);
        return result;
    }

    public CommonResult() {
        this.status = CommonConstant.STATUS_ERROR;
        this.message = CommonConstant.STATUS_ERROR_DESC;
    }

    public CommonResult(String message) {
        this.status = CommonConstant.STATUS_ERROR;
        this.message = message;
    }

    public CommonResult(Integer status) {
        this.status = status;
    }

    public CommonResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
