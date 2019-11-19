package com.wld.modular.system.controller;

import cn.hutool.core.util.StrUtil;
import com.wld.common.api.CommonResult;
import com.wld.common.util.GenerateVerifyCode;
import com.wld.config.security.jwt.JwtTokenProvider;
import com.wld.modular.domain.Permission;
import com.wld.modular.domain.User;
import com.wld.modular.system.service.CaptchaService;
import com.wld.modular.system.service.UserService;
import com.wld.modular.system.service.dto.UserLoginParam;
import com.wld.modular.system.service.dto.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 后台用户管理
 *
 * @author 王增光
 */
@Api(tags = "sys-登录注册授权")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CaptchaService captchaService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<User> register(@RequestBody UserParam userParam, BindingResult result) {
        User wldUser = userService.register(userParam);
        if (wldUser == null) {
            CommonResult.failed();
        }
        return CommonResult.success(wldUser);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UserLoginParam userLoginParam, BindingResult result) {
        if (!captchaService.validateCaptcha(userLoginParam)) {
            return CommonResult.failed("请输入正确的验证码");
        }
        String token = userService.login(userLoginParam);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", token);
        tokenMap.put("tokenHead", jwtTokenProvider.getTokenHead());
        tokenMap.put("expireInSeconds", jwtTokenProvider.getExpireTime(userLoginParam.isRememberMe()).toString());
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed();
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    public CommonResult getLoginInfo() {

        return CommonResult.success(userService.getLoginUserInfo());
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/getPermissions", method = RequestMethod.GET)
    public CommonResult getPermissions(Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        List<Permission> permissionList = userService.getPermissionList(user.getId());
        return CommonResult.success(permissionList);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @GetMapping("/captcha/init")
    @ApiOperation(value = "初始化验证码")
    public CommonResult<String> initCaptcha() {

        return CommonResult.success(captchaService.initCaptcha());
    }

    @GetMapping(value = "/captcha/{captchaId}")
    @ApiOperation(value = "根据验证码ID获取图片")
    public void getCaptcha(@PathVariable("captchaId") String captchaId, HttpServletResponse response) {

        captchaService.getCaptcha(captchaId, response);
    }

    @PostMapping(value = "/captcha/check")
    @ApiOperation(value = "校验验证码")
    public CommonResult<Boolean> checkCaptcha(@RequestParam("captchaId") String captchaId, @RequestParam("code") String code) {

        return CommonResult.success(captchaService.checkCaptcha(captchaId, code));
    }
}
