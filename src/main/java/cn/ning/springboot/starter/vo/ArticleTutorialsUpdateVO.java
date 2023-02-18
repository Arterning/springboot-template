package cn.ning.springboot.starter.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@ApiModel("更新 ")
@EqualsAndHashCode(callSuper = false)
public class ArticleTutorialsUpdateVO extends ArticleTutorialsVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
