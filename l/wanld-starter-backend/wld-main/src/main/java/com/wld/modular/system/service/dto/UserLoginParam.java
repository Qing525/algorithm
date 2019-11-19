package com.wld.modular.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录参数
 *
 * @author wangzg
 */
@Data
public class UserLoginParam {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "唯一标识码", required = true)
    private String captchaId;

    @ApiModelProperty(value = "验证码", required = true)
    @NotEmpty(message = "验证码不能为空")
    private String code;

    private boolean rememberMe = false;
}
