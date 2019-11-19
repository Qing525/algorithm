package com.wld.email.provider;


import com.wld.email.core.mail.MailManager;
import com.wld.email.core.model.SendMailParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 短信通知接口
 *
 * @author zhangcf
 */
@Component
public class MailServiceProvider implements MailServiceApi {

    private static Logger logger = LoggerFactory.getLogger(MailServiceProvider.class);

    @Autowired
    private MailManager mailManager;

    @Override
    public void sendMail(SendMailParam sendMailParam) {
        logger.info("email调用入参：" + com.alibaba.fastjson.JSONObject.toJSON(sendMailParam).toString());
        mailManager.sendMail(sendMailParam);
    }
}
