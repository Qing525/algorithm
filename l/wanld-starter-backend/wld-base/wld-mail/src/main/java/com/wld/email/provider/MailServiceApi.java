package com.wld.email.provider;

import com.wld.email.core.model.SendMailParam;

/**
 * 邮件发送的api
 *
 * @author zhangcf
 */
public interface MailServiceApi {

    /**
     * 发送邮件
     *
     * @author zhangcf
     */
    void sendMail(SendMailParam sendMailParam);
}
