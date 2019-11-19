package com.wld.email.core.mail.service;


import com.wld.email.core.mail.MailManager;
import com.wld.email.core.model.SendMailParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * java默认的邮件发送方式实现
 *
 * @author zhangcf
 */
@Service

public class JavaMailManager implements MailManager {
    private final Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.from}")
    private String from;

    @Override
    public void sendMail(SendMailParam sendMailParam) {

        //校验发送邮件的参数
        assertSendMailParams(sendMailParam);
        log.info("参数校验成功。。。。");
        //spring发送邮件
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(sendMailParam.getTo());
            mimeMessageHelper.setSubject(sendMailParam.getTitle());
            mimeMessageHelper.setText(sendMailParam.getContent(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("发送邮件异常", e);
        }
    }

    /**
     * 校验发送邮件的请求参数
     *
     * @author zhangcf
     */
    private void assertSendMailParams(SendMailParam sendMailParam) {
        if (sendMailParam == null) {
            log.error("请求参数为空");
        }
    }
}
