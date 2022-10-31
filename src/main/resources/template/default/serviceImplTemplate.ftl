package ${servicePackage}.impl;

<#if table.primaryColumn?? && table.primaryColumn.columnType?? && table.primaryColumn.columnType.pkg?? && !table.primaryColumn.columnType.autoImported>
import ${table.primaryColumn.columnType.pkg};
</#if>
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huangqj.common.dto.*;
import com.huangqj.common.bean.PageQueryBean;
import ${servicePackage}.${domain}Service;
import com.huangqj.utils.BeanUtils;
import ${dtoPackage}.${domain}DTO;
import ${beanPackage}.${domain}Bean;
import ${domainPackage}.${domain};
import ${mapperPackage}.${domain}Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* ${table.comment}服务
*
* author: ${author}
* date: ${date}
*/
@Service
public class ${domain}ServiceImpl implements ${domain}Service {
    private static final Logger log = LoggerFactory.getLogger(${domain}ServiceImpl.class);

    @Autowired
    private ${domain}Mapper ${domain?uncap_first}Mapper;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageDTO<${domain}DTO> list(PageQueryDTO queryDTO) {
        log.info("the {}.list parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(queryDTO));
        PageQueryBean query = BeanUtils.map(queryDTO, PageQueryBean.class);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<${domain}> domains = ${domain?uncap_first}Mapper.selectByQuery(query);
        PageInfo<${domain}DTO> pageInfo = new PageInfo(domains);
        // 转换为PageDTO
        PageDTO<${domain}DTO> page = BeanUtils.map(pageInfo, PageDTO.class);
        page.setList(BeanUtils.mapList(pageInfo.getList(), ${domain}DTO.class));
        return page;
    }

    /**
     * 查找
     *
     * @param ${table.primaryColumn.propertyName}
     * @return
     */
    @Override
    public ${domain}DTO find(${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName}) {
        log.info("the {}.find parameter: [{}]", this.getClass().getSimpleName(), ${table.primaryColumn.propertyName});
        ${domain} domain = ${domain?uncap_first}Mapper.selectByPrimaryKey(${table.primaryColumn.propertyName});
        if(domain != null) {
            ${domain}DTO dto = BeanUtils.map(domain, ${domain}DTO.class);
            return dto;
        }
        return null;
    }

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Long save(${domain}DTO dto) {
        log.info("the {}.save parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        ${domain} domain = BeanUtils.map(dto, ${domain}.class);
        ${domain?uncap_first}Mapper.insert${domain}(domain);
        return domain.get${table.primaryColumn.propertyName?cap_first}();
    }

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Long update(${domain}DTO dto) {
        log.info("the {}.update parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        ${domain} domain = ${domain?uncap_first}Mapper.selectByPrimaryKey(dto.get${table.primaryColumn.propertyName?cap_first}());
        if(domain != null) {
            ${domain} ${domain?uncap_first} = BeanUtils.map(dto, ${domain}.class);
            ${domain?uncap_first}Mapper.update${domain}(${domain?uncap_first});
        }
        return dto.get${table.primaryColumn.propertyName?cap_first}();
    }

    /**
     * 删除
     *
     * @param ${table.primaryColumn.propertyName}
     */
    @Override
    @Transactional
    public void delete(${table.primaryColumn.columnType.type} ${table.primaryColumn.propertyName}) {
        log.info("the {}.delete parameter: [{}]", this.getClass().getSimpleName(), ${table.primaryColumn.propertyName});
        ${domain?uncap_first}Mapper.deleteByPrimaryKey(${table.primaryColumn.propertyName});
    }

}
