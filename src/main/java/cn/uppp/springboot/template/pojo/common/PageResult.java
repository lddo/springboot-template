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
public class PageResult<T> extends Result<T> implements Serializable {
    private static final long serialVersionUID = -1447283981433117032L;

    private long total;
}
