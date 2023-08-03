package net.sitir.message.component.aop.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * token中的账户信息
 * @author linweikang
 * @date 2023/8/3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {

    /**
     * 账户id
     */
    private String id;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 绑定人员id
     */
    private Long personId;

    /**
     * 绑定人员名称
     */
    private String perName;

    /**
     * 绑定人员Code
     */
    private String perCode;

}
