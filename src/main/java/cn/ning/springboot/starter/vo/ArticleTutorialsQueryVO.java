package cn.ning.springboot.starter.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("自定义查询 ")
public class ArticleTutorialsQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String description;

    private Boolean published;

    private String title;

}
