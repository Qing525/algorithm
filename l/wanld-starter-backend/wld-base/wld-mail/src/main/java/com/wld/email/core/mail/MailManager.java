package com.wld.email.core.mail;

import com.wld.email.core.model.SendMailParam;

/**
 * 邮件收发统一接口
 *
 * @author zhangcf
 */
public interface MailManager {

    /**
     * 发送普通邮件
     *
     * @author zhangcf
     */
    void sendMail(SendMailParam sendMailParam);
}
