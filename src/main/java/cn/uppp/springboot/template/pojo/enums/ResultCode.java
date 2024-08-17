package cn.uppp.springboot.template.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 结果码枚举
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 系统错误码(1000~9999)
     */
    SYSTEM_ERROR(1000, "系统异常"),
    PARAM_ERROR(1001, "参数错误"),
    ILLEGAL_REQUEST(1002, "非法请求"),
    UPLOAD_FAILED(1003, "上传失败"),
    GET_STS_FAILED(1004, "获取阿里云STS失败"),
    GET_VIDEO_UPLOAD_FAIL(1005, "获取视频上传凭证失败: %s"),
    UNSUPPORTED_ENUM(1006, "未支持的枚举类型"),
    ILLEGAL_CLIENT(1007, "非法客户端"),
    FILE_NOT_EXISTS(1008, "文件不存在"),
    /**
     * 业务错误码(10000以上)
     */
    ;

    private final Integer code;
    private final String message;
}
