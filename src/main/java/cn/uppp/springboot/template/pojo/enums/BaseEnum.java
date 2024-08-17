package cn.uppp.springboot.template.pojo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 结果码定义
 *
 * @author liudongdong
 * @date 2024/8/15
 */
public interface BaseEnum extends IEnum<Integer> {
    /**
     * 描述
     *
     * @return
     */
    String getDescription();
}
