package cn.uppp.springboot.template.spring.handler;

import cn.uppp.springboot.template.pojo.common.BizException;
import cn.uppp.springboot.template.pojo.common.Result;
import cn.uppp.springboot.template.pojo.enums.ResultCode;
import cn.uppp.springboot.template.utils.ResultUtil;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常处理器
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return ResponseEntity.ok(ResultUtil.build(ResultCode.SYSTEM_ERROR.getCode(), "请求方式错误"));
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return ResponseEntity.ok(ResultUtil.build(ResultCode.SYSTEM_ERROR.getCode(), "请求路径错误"));
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        BindingResult bindingResult = ex.getBindingResult();
//        // 默认参数错误提示
//        String message = ResultCode.PARAM_ERROR.getMessage();
//        if (bindingResult.hasErrors()) {
//            message = bindingResult.getAllErrors().get(0).getDefaultMessage();
//        }
//        return ResponseEntity.ok(ResultUtil.build(ResultCode.PARAM_ERROR.getCode(), message));
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        BindingResult bindingResult = ex.getBindingResult();
//        // 默认参数错误提示
//        String message = ResultCode.PARAM_ERROR.getMessage();
//        if (bindingResult.hasErrors()) {
//            message = bindingResult.getAllErrors().get(0).getDefaultMessage();
//        }
//        return ResponseEntity.ok(ResultUtil.build(ResultCode.PARAM_ERROR.getCode(), message, null));
//    }






    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationException(ConstraintViolationException e) {
        return ResultUtil.build(ResultCode.PARAM_ERROR.getCode(), e.getConstraintViolations().iterator().next().getMessage(), null);
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Result bizException(BizException e) {
        return e.toResult();
    }

    /**
     * 兜底异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        return ResultUtil.build(ResultCode.SYSTEM_ERROR);
    }
}
