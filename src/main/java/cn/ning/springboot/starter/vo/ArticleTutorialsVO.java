package cn.ning.springboot.starter.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@ApiModel("保存 ")
public class ArticleTutorialsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Long id;

    private String description;

    private Boolean published;

    private String title;

}
