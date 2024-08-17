package cn.uppp.springboot.template.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否删除
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum implements BaseEnum {
    NOT(0, "未删除"),
    YES(1, "已删除");

    private final Integer value;
    private final String description;


    public static DeletedEnum get(Integer value) {
        for (DeletedEnum obj : DeletedEnum.values()) {
            if (obj.value.equals(value)) {
                return obj;
            }
        }
        return null;
    }
}
