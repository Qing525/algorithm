package com.wld.modular.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wld.common.util.GenerateVerifyCode;
import com.wld.modular.system.service.CaptchaService;
import com.wld.modular.system.service.dto.UserLoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author LJQ
 * @date 2019/9/23 9:14
 **/
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String initCaptcha() {
        String captchaId = UUID.randomUUID().toString().replace("-", "");

        String code = new GenerateVerifyCode().randomStr(4);

        redisTemplate.opsForValue().set(captchaId, code, 10L, TimeUnit.MINUTES);

        return captchaId;
    }

    @Override
    public Boolean checkCaptcha(String captchaId, String code) {
        String redisCode = redisTemplate.opsForValue().get(captchaId);
        if (StrUtil.isBlank(redisCode) || StrUtil.isBlank(captchaId) || StrUtil.isBlank(code)) {
            return false;
        }
        return redisCode.toLowerCase().equals(code.toLowerCase());
    }

    @Override
    public void getCaptcha(String captchaId, HttpServletResponse response) {
        String code = redisTemplate.opsForValue().get(captchaId);
        GenerateVerifyCode vCode = new GenerateVerifyCode(116, 36, 4, 10, code);
        response.setContentType("image/png");
        try {
            vCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean validateCaptcha(UserLoginParam userLoginParam) {
        String captchaId = userLoginParam.getCaptchaId();
        String code = userLoginParam.getCode();
        String redisCode = redisTemplate.opsForValue().get(captchaId);
        if (StrUtil.isBlank(captchaId) || StrUtil.isBlank(code)) {
            return false;
        }

        if (StrUtil.isBlank(redisCode)) {
            return false;
        }
        if (!redisCode.toLowerCase().equals(code.toLowerCase())) {
            redisTemplate.delete(captchaId);
            return false;
        }
        redisTemplate.delete(captchaId);
        return true;
    }
}
