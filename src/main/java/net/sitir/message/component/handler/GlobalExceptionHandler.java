package net.sitir.message.component.handler;

import lombok.extern.slf4j.Slf4j;
import net.sitir.message.component.common.CommonResult;
import net.sitir.message.component.exception.APIException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

/**
 * @author linweikang
 * @since 2023/8/3
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(APIException.class)
    public CommonResult<Object> apiException(APIException e) {
        log.error("出现API异常，错误原因:", e);
        return CommonResult.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResult<Object> exception(Exception e) {
        log.error("出现Exception异常，错误原因:", e);
        return CommonResult.error(Optional.ofNullable(e.getMessage()).orElse("出现空消息异常"));
    }

    @ExceptionHandler(NullPointerException.class)
    public CommonResult<Object> nullPointerException(NullPointerException e) {
        log.error("出现空指针异常，错误原因:", e);
        return CommonResult.error("存在空指针数据错误");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResult<Object> illegalArgumentException(IllegalArgumentException e) {
        log.error("出现非法参数异常，错误原因:", e);
        return CommonResult.error("非法参数");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult<Object> constraintViolationException(ConstraintViolationException e) {
        log.error("出现参数校验异常，错误原因:", e);
        return CommonResult.error(e.getConstraintViolations().iterator().next().getMessage());
    }

    /**
     * Validator 参数校验异常处理-MethodArgumentNotValidException
     * @auth linweikang
     * @date 2020/12/14
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("出现参数校验异常，错误原因:", e);
        return CommonResult.error(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }
}
