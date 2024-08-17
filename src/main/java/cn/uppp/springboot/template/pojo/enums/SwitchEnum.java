package cn.uppp.springboot.template.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 开关
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@Getter
@AllArgsConstructor
public enum SwitchEnum implements BaseEnum {
    OFF(0, "关"),
    ON(1, "开");

    private final Integer value;
    private final String description;
}
