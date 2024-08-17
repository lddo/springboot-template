package cn.uppp.springboot.template.utils;

import cn.uppp.springboot.template.pojo.common.PageResult;
import cn.uppp.springboot.template.pojo.common.Result;
import cn.uppp.springboot.template.pojo.enums.ResultCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * 结果工具类
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultUtil {
    /* 预期结果 */
    private static final int EXPECT_RESULT = 1;

    public static Result<Void> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return build(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> ok(T data, String message) {
        return build(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> build(ResultCode resultCode) {
        return build(resultCode, null);
    }

    public static <T> Result<T> build(ResultCode resultCode, T data) {
        return build(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static <T> Result<T> build(int code, String message) {
        return build(code, message, null);
    }

    public static <T> Result<T> build(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> PageResult<T> emptyData() {
        return new PageResult<>();
    }

    public static <T extends Collection<?>> PageResult<T> empty(long total) {
        return page(null, total);
    }

    public static <T extends Collection<?>> PageResult<T> page(T data, long total) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(ResultCode.SUCCESS.getCode());
        pageResult.setMessage(ResultCode.SUCCESS.getMessage());
        pageResult.setData(data);
        pageResult.setTotal(total);
        return pageResult;
    }

    public static <T> PageResult<T> noPage(T data, long total) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(ResultCode.SUCCESS.getCode());
        pageResult.setMessage(ResultCode.SUCCESS.getMessage());
        pageResult.setData(data);
        pageResult.setTotal((int) total);
        return pageResult;
    }

    public static <T> boolean isSuccess(Result<T> result) {
        return result != null && result.getCode() == ResultCode.SUCCESS.getCode();
    }

    public static <T> boolean isFailure(Result<T> result) {
        return !isSuccess(result);
    }
}
