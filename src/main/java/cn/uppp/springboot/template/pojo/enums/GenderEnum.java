package cn.uppp.springboot.template.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements BaseEnum {
    FEMALE(0, "女"),
    MALE(1, "男");

    private final Integer value;
    private final String description;
}
