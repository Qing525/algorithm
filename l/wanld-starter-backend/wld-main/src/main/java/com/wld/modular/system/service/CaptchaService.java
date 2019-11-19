package com.wld.modular.system.service;

import com.wld.modular.system.service.dto.UserLoginParam;

import javax.servlet.http.HttpServletResponse;


/**
 * @author LJQ
 * @date 2019/9/23 9:12
 **/
public interface CaptchaService {

    String initCaptcha();

    Boolean checkCaptcha(String captchaId, String code);

    void getCaptcha(String captchaId, HttpServletResponse response);

    Boolean validateCaptcha(UserLoginParam loginParam);
}
