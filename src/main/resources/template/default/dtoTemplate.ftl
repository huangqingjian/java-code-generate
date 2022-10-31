package ${dtoPackage};

<#if table.columns?? && table.columns?size gt 0>
<#list table.columns as column>
<#if column.columnType?? && column.columnType.pkg?? && !column.columnType.autoImported>
import ${column.columnType.pkg};
</#if>
</#list>
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* ${table.comment} DTO
*
* author: ${author}
* date: ${date}
*/
@ApiModel("${table.comment}DTO")
public class ${domain}DTO {

<#-- 循环属性名称 -->
<#list table.columns as column>
    <#if column.comment??>
    /**
     * ${column.comment}
     */
    @ApiModelProperty("${column.comment}")
    </#if>
    private ${column.propertyType} ${column.propertyName};

</#list>

<#-- 循环set/get方法 -->
<#list table.columns as column>
    public ${column.propertyType} get${column.propertyName?cap_first}() {
        return ${column.propertyName};
    }

    public void set${column.propertyName?cap_first}(${column.propertyType} ${column.propertyName}) {
        this.${column.propertyName} = ${column.propertyName};
    }

</#list>
}