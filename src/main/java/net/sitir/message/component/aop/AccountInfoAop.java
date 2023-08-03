package net.sitir.message.component.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.sitir.idsc.servers.bubalus.dto.out.PersonnelInfoDTO;
import net.sitir.message.component.aop.bo.AccountInfo;
import net.sitir.message.component.exception.APIException;
import net.sitir.message.utils.TokenTools;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @description: 控制层与accountInfo参数赋值切面类
 * @author linweikang
 * @date 2023/8/3
 **/
@Aspect
@Slf4j
@Component
public class AccountInfoAop {

    public static final String HEADER_ACCOUNT_PERSON = "person";


    /**
     * 切面
     */
    @Pointcut("execution(public * net.sitir.message.controller..*.*(..))")
    public void accountInfo(){

    }

    /**
     * 通知>行为
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("accountInfo()")
    public Object accountInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        

        //获取方法的参数类型
        Object[] args = proceedingJoinPoint.getArgs();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();

        // 获取token
        String auth = request.getHeader("Authorization");
        if (StringUtils.isBlank(auth)){
            log.info("token缺失");
            throw new APIException("token缺失!!!");
            // return proceedingJoinPoint.proceed(args);
        }
        TokenTools tokenTools = new TokenTools(request);
        String token = tokenTools.getToken();

        if (StringUtils.isBlank(token)) {
            log.info("token缺失");
            throw new APIException("token缺失!!!");
            // return proceedingJoinPoint.proceed(args);
        }else {
            AccountInfo accountInfo = tokenTools.getAccountInfo(token);

            String person = request.getHeader(HEADER_ACCOUNT_PERSON);
            if(StringUtils.isNotBlank(person)){
                person = URLDecoder.decode(person, StandardCharsets.UTF_8);
                PersonnelInfoDTO dto = JSONObject.parseObject(person, PersonnelInfoDTO.class);
                accountInfo.setPersonId(dto.getId());
                accountInfo.setPerName(dto.getPerName());
                accountInfo.setPerCode(dto.getPerCode());
            }

            int index = 0;
            for (Parameter parameter : parameters) {
                if (parameter.getType().equals(AccountInfo.class)) {
                    args[index] = accountInfo;
                }
                index++;
            }

            return proceedingJoinPoint.proceed(args);
        }
    }

}
