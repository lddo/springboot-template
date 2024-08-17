package cn.uppp.springboot.template.utils;

import cn.uppp.springboot.template.pojo.common.PageParam;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页工具
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageUtil {

    public static <T> List<T> page(Collection<T> elements, PageParam param) {
        return page(elements, param.getPageNum(), param.getPageSize());
    }

    public static <T> List<T> page(Collection<T> elements, int pageNum, int pageSize) {
        int startElem = (pageNum - 1) * pageSize;
        return elements.stream().skip(startElem).limit(pageSize).collect(Collectors.toList());
    }
}
