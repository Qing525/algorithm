package com.wld.email.core.model;



/**
 * 发送邮件的请求参数
 *
 * @author zhangcf
 */

public class SendMailParam {

    /**
     * 发送给某人的邮箱
     */
    private String to;

    /**
     * 邮件标题
     */
    private String title;

    /**
     * 邮件内容（经过base64编码后的）
     */
    private String content;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
