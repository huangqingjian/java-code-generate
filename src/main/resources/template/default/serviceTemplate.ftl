package ${servicePackage};

import ${dtoPackage}.PageQueryDTO;
import ${dtoPackage}.PageDTO;
import ${dtoPackage}.${domain}DTO;
import ${domainPackage}.${domain};
<#if table.primaryColumn?? && table.primaryColumn.columnType?? && table.primaryColumn.columnType.pkg?? && !table.primaryColumn.columnType.autoImported>
import ${table.primaryColumn.columnType.pkg};
</#if>

/**
 * ${table.comment}服务
 *
 * author: ${author}
 * date: ${date}
 */
public interface ${domain}Service {
    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    PageDTO<${domain}DTO> list(PageQueryDTO queryDTO);

    /**
     * 查找
     *
     * @param ${table.primaryColumn.propertyName}
     * @return
     */
     ${domain}DTO find(${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName});

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long save(${domain}DTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    Long update(${domain}DTO dto);

    /**
     * 删除
     *
     * @param ${table.primaryColumn.propertyName}
     */
    void delete(${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName});
}
