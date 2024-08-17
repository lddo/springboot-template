package cn.uppp.springboot.template.pojo.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 结果
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@Getter
@Setter
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -8066610622719934274L;

    private int code;
    private String message;
    private T data;
}
