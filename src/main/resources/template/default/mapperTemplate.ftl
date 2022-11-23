package ${mapperPackage};

import ${domainPackage}.${domain};
import java.util.List;
<#if table.primaryColumn?? && table.primaryColumn.columnType?? && table.primaryColumn.columnType.pkg?? && !table.primaryColumn.columnType.autoImported>
    import ${table.primaryColumn.columnType.pkg};
</#if>
import com.huangqj.common.bean.PageQueryBean;
import org.apache.ibatis.annotations.Param;

/**
 * ${table.comment} mapper
 *
 * author: ${author}
 * date: ${date}
 */
public interface ${domain}Mapper {
    /**
     * 查询列表
     *
     * @param queryBean
     * @return
     */
    List<${domain}> selectByQuery(PageQueryBean queryBean);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    ${domain} selectByPrimaryKey(@Param("${table.primaryColumn.propertyName}") ${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName});

    /**
     * 新增
     *
     * @param domain
     */
    void insert${domain}(${domain} domain);

    /**
     * 更新
     *
     * @param domain
     */
    void update${domain}(${domain} domain);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(@Param("${table.primaryColumn.propertyName}") ${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName});
}