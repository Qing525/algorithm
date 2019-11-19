package com.wld.modular.system.service.dto;

import com.wld.modular.domain.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wangzg
 * @date 2019/08/08
 */
public class PermissionNode extends Permission {
    @Getter
    @Setter
    private List<PermissionNode> children;
}
