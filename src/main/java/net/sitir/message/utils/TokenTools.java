package net.sitir.message.utils;

import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import net.sitir.message.component.aop.bo.AccountInfo;
import net.sitir.message.component.exception.APIException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author linweikang
 * @since 2023/8/3
 */
public class TokenTools {

    public static final String TOKEN_PREFIX_BEARER = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String PAYLOAD_EXP = "exp";
    public static final String PAYLOAD_CLIENT_ID = "client_id";
    public static final String PAYLOAD_SCOPE = "scope";
    public static final String PAYLOAD_AUTHORITIES = "authorities";
    public static final String PAYLOAD_JTI = "jti";
    private HttpServletRequest request;
    private String token;

    public TokenTools(HttpServletRequest request) throws ServletException {
        if (request == null) {
            throw new ServletException("请求不能为空");
        }
        this.request = request;
    }

    public String getToken() throws APIException {
        String authorization = request.getHeader(HEADER_AUTHORIZATION);
        if (StringUtils.isBlank(authorization)) {
            throw new APIException("token不存在");
        }
        this.token = authorization.replaceAll(TOKEN_PREFIX_BEARER, "");
        return token;
    }

    public JWSObject getJwt(String token) throws ParseException {
        return JWSObject.parse(token);
    }

    public JSONObject getPayloadObject(String token) throws ParseException {
        String payload = JWSObject.parse(token).getPayload().toString();
        return JSONObject.parseObject(payload);
    }

    public Long getExp(String token) throws ParseException {
        return getPayloadObject(token).getLong(PAYLOAD_EXP);
    }

    public Object getAdditionalInfo(String token, String key) throws ParseException {
        return JWSObject.parse(token).getPayload().toJSONObject().get(key);
    }

    public AccountInfo getAccountInfo(String token) throws ParseException {
        JSONObject jsonObject = getPayloadObject(token);
        AccountInfo accountEntity = new AccountInfo();
        // 将keycloak中的attribut属性中的LDAP_ID设置为账户id
        if(jsonObject != null) {
            accountEntity.setAccountName(jsonObject.getString("preferred_username"));
            if(jsonObject.get("attributes") != null){
                JSONObject attributes = JSONObject.parseObject(jsonObject.getString("attributes"));
                if (attributes != null) {
                    String ldapId = attributes.getString("LDAP_ID");
                    if (ldapId != null) {
                        accountEntity.setId(ldapId);
                    }
                }
            }
        }
        accountEntity.setPersonId(0L);
        
        return accountEntity;
    }

}
