package cn.ning.springboot.starter.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("")
public class ArticleTutorialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String description;

    private Boolean published;

    private String title;

}
