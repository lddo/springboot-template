package cn.uppp.springboot.template.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会员套餐
 *
 * @author liudongdong
 * @date 2021/3/18
 */
@Getter
@AllArgsConstructor
public enum StatusEnum implements BaseEnum {
    DISABLED(0, "已禁用"),
    ENABLED(1, "已启用");

    private final Integer value;
    private final String description;
}
