package cn.ning.springboot.starter.modules.crud;

import cn.ning.springboot.starter.dto.ArticleTutorialsDTO;
import cn.ning.springboot.starter.vo.ArticleTutorialsQueryVO;
import cn.ning.springboot.starter.vo.ArticleTutorialsUpdateVO;
import cn.ning.springboot.starter.vo.ArticleTutorialsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * jpa 代码插件自动生成
 * 标准的 restful API
 */
@Api(tags = "")
@Validated
@RestController
@RequestMapping("/articleTutorials")
public class ArticleTutorialsController {

    @Autowired
    private ArticleTutorialsService articleTutorialsService;

    @PostMapping
    @ApiOperation("保存 ")
    public String save(@Valid @RequestBody ArticleTutorialsVO vO) {
        return articleTutorialsService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除 ")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        articleTutorialsService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("更新 ")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody ArticleTutorialsUpdateVO vO) {
        articleTutorialsService.update(id, vO);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID获取 ")
    public ArticleTutorialsDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return articleTutorialsService.getById(id);
    }

    @GetMapping
    @ApiOperation("自定义查询 ")
    public Page<ArticleTutorialsDTO> query(@Valid ArticleTutorialsQueryVO vO,
                                           @RequestParam(defaultValue = "0") int pageNumber,
                                           @RequestParam(defaultValue = "10") int pageSize) {
        return articleTutorialsService.query(vO, pageNumber, pageSize);
    }


}
