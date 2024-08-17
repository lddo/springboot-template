package cn.uppp.springboot.template.pojo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam implements Serializable {
    private static final long serialVersionUID = -4245102116713549448L;

    @Schema(description = "页码")
    private Integer pageNum = 1;
    @Schema(description = "每页数量")
    private Integer pageSize = 10;

}
