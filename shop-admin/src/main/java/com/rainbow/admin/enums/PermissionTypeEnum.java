package com.rainbow.admin.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举
 *
 * @author lujinwei
 * @since 2019-06-26
 */
@Getter
@AllArgsConstructor
public enum PermissionTypeEnum implements IEnum<Integer> {

    CATALOGUE(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮-接口绑定权限");

    private Integer value;
    private String desc;
}
