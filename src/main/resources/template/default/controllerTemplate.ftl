package ${controllerPackage};

import ${dtoPackage}.${domain}DTO;
<#if table.primaryColumn?? && table.primaryColumn.columnType?? && table.primaryColumn.columnType.pkg?? && !table.primaryColumn.columnType.autoImported>
import ${table.primaryColumn.columnType.pkg};
</#if>
import ${servicePackage}.${domain}Service;
import com.huangqj.common.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * ${table.comment}管理器
 *
 * author: ${author}
 * date: ${date}
 */
@Api(tags = "${table.comment}管理器", value = "${domain}.Manager")
@Controller
@RequestMapping(value="/${domain?uncap_first}")
public class ${domain}Controller {
    private static final Logger log = LoggerFactory.getLogger(${domain}Controller.class);

    private static final String LIST = "/${domain?uncap_first}/list";
    private static final String ADD = "/${domain?uncap_first}/add";
    private static final String EDIT = "/${domain?uncap_first}/edit";
    private static final String DETAIL = "/${domain?uncap_first}/detail";

    @Autowired
    private ${domain}Service ${domain?uncap_first}Service;

    /**
     * ${table.comment}列表页
     *
     * @return
     */
    @ApiOperation(value = "${table.comment}列表页")
    @RequestMapping(value = "list.html")
    public String list() {
        return LIST;
    }

    /**
     * ${table.comment}新增页
     *
     * @return
     */
    @ApiOperation(value = "${table.comment}新增页")
    @RequestMapping(value = "add.html")
    public String add() {
        return ADD;
    }

    /**
     * ${table.comment}编辑页
     *
     * @param ${table.primaryColumn.propertyName}
     * @param model
     * @return
     */
    @ApiOperation(value = "${table.comment}编辑页")
    @RequestMapping(value = "/{${table.primaryColumn.propertyName}}.html")
    public String edit(@PathVariable("${table.primaryColumn.propertyName}") ${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName}, Model model) {
        model.addAttribute("${table.primaryColumn.propertyName}", ${table.primaryColumn.propertyName});
        return EDIT;
    }

    /**
     * ${table.comment}详情页
     *
     * @param ${table.primaryColumn.propertyName}
     * @param model
     * @return
     */
    @ApiOperation(value = "${table.comment}详情页")
    @RequestMapping(value = "detail/{${table.primaryColumn.propertyName}}.html")
    public String detail(@PathVariable("${table.primaryColumn.propertyName}") ${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName}, Model model) {
        model.addAttribute("${table.primaryColumn.propertyName}", ${table.primaryColumn.propertyName});
        return DETAIL;
    }

    /**
     * ${table.comment}列表
     *
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "${table.comment}列表")
    @GetMapping(value = "list")
    @ResponseBody
    public ResponseDTO<PageDTO<${domain}DTO>> list(PageQueryDTO queryDTO) {
        PageDTO<${domain}DTO> pageDTO = ${domain?uncap_first}Service.list(queryDTO);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * ${table.comment}查找
     *
     * @param ${table.primaryColumn.propertyName}
     * @return
     */
    @ApiOperation(value = "${table.comment}查找")
    @GetMapping(value = "get/{${table.primaryColumn.propertyName}}")
    @ResponseBody
    public ResponseDTO<${domain}DTO> get(@PathVariable("${table.primaryColumn.propertyName}") ${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName}) {
        ${domain}DTO dto = ${domain?uncap_first}Service.find(${table.primaryColumn.propertyName});
        return ResponseDTO.success(dto);
    }

    /**
     * ${table.comment}新增
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "${table.comment}新增")
    @PostMapping(value = "save")
    @ResponseBody
    public ResponseDTO<${table.primaryColumn.columnType.type}> save(@RequestBody ${domain}DTO dto) {
        ${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName} = ${domain?uncap_first}Service.save(dto);
        return ResponseDTO.success(${table.primaryColumn.propertyName});
    }

    /**
     * ${table.comment}更新
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "${table.comment}更新")
    @PostMapping(value = "update")
    @ResponseBody
    public ResponseDTO<Long> update(@RequestBody ${domain}DTO dto) {
        ${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName} = ${domain?uncap_first}Service.update(dto);
        return ResponseDTO.success(${table.primaryColumn.propertyName});
    }

    /**
     * ${table.comment}删除
     *
     * @param ${table.primaryColumn.propertyName}
     * @return
     */
    @ApiOperation(value = "${table.comment}删除")
    @PostMapping(value = "delete/{${table.primaryColumn.propertyName}}")
    @ResponseBody
    public ResponseDTO<Boolean> delete(@PathVariable("${table.primaryColumn.propertyName}") ${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName}) {
        ${domain?uncap_first}Service.delete(${table.primaryColumn.propertyName});
        return ResponseDTO.success(Boolean.TRUE);
    }

}
