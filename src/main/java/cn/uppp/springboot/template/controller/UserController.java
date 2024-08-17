package cn.uppp.springboot.template.controller;

import cn.uppp.springboot.template.pojo.common.Result;
import cn.uppp.springboot.template.utils.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author liudongdong
 * @date 2024/8/14
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Operation(summary = "查询用户列表")
    @GetMapping("/list")
    public Result listUser() {
        return ResultUtil.ok();
    }

    @Operation(summary = "添加用户")
    @PostMapping("/add")
    public Result addUser() {
        return ResultUtil.ok();
    }
}
