package net.sitir.message.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import net.sitir.message.component.common.SmsConstant;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

/**
 * 发送手机验证码工具类.
 */
@Component
public class SendSmsUtil {

    public String sendSms(String phone){
        // 生成随机数
        String code=createRandom();
        SmsSingleSenderResult result = null;
        try {
            // 模板需要的参数
            String[] params = {code,"3"};
            SmsSingleSender ssender = new SmsSingleSender(SmsConstant.APP_ID, SmsConstant.APP_KEY);
            // 单发短信
            result = ssender.sendWithParam("86", phone, SmsConstant.TEMPLATE_ID, params, SmsConstant.SIGN, "", "");
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return "验证码发送失败";
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return "验证码发送失败";
        } catch (HTTPException e) {
            e.printStackTrace();
            return "验证码发送失败";
        }

            if(!"OK".equals(result.errMsg)){
            return "验证码发送失败";
        }

            System.out.println(result.errMsg);
            return "success";
    }

    /**
     * 验证码长度（通过更改i的最大值）
     * 获取6位随机数
     * @return
     */
    public static String createRandom(){
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        return result;
    }


}
