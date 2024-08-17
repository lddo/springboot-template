package cn.uppp.springboot.template.pojo.common;

import cn.uppp.springboot.template.pojo.enums.ResultCode;
import cn.uppp.springboot.template.utils.ResultUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    private int code;
    private String message;

    private BizException() {
    }

    public BizException(String message) {
        this.code = ResultCode.SYSTEM_ERROR.getCode();
        this.message = message;
    }

    public BizException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BizException(ResultCode resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
    }

    public Result toResult() {
        return ResultUtil.build(code, message, null);
    }
}
