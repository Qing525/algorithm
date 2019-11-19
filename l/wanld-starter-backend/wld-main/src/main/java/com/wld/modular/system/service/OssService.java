package com.wld.modular.system.service;

import com.wld.modular.system.service.dto.OssCallbackResult;
import com.wld.modular.system.service.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 *
 * @author wangzg
 * @date 2019/08/08
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
