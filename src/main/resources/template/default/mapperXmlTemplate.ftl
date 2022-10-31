<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperPackage}.${domain}Mapper" >
    <resultMap id="BaseResultMap" type="${domainPackage}.${domain}" >
        <#list table.columns as column>
            <#if column.name == table.primaryColumn.name>
            <id column="${column.name}" property="${column.propertyName}" jdbcType="${column.type?upper_case}" />
            <#else>
            <result column="${column.name}" property="${column.propertyName}" jdbcType="${column.type?upper_case}" />
            </#if>
        </#list>
    </resultMap>
    <sql id="Base_Column_List" >
        <#list table.columns as column>
            `${column.name}`<#if column_has_next>,</#if>
        </#list>
    </sql>
    <select id="selectByQuery" resultMap="BaseResultMap" parameterType="com.huangqj.common.bean.PageQueryBean" >
        select
        <include refid="Base_Column_List" />
        from ${table.name}
    </select>
    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="${table.primaryColumn.columnType.pkg}" >
        select
        <include refid="Base_Column_List" />
        from ${table.name}
        where ${table.primaryColumn.name} = <#noparse>#{</#noparse>${table.primaryColumn.propertyName},jdbcType=${table.primaryColumn.type?upper_case}<#noparse>}</#noparse>
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Long" >
        delete from ${table.name}
        where ${table.primaryColumn.name} = <#noparse>#{</#noparse>${table.primaryColumn.propertyName},jdbcType=${table.primaryColumn.type?upper_case}<#noparse>}</#noparse>
    </delete>
    <insert id="insert${domain}" parameterType="${domainPackage}.${domain}" useGeneratedKeys="true" keyProperty="${table.primaryColumn.name}">
        insert into ${table.name}
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <#list table.columns as column>
            <if test="${column.propertyName} != null" >
                `${column.name}`,
            </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
            <#list table.columns as column>
            <#if column.name != table.primaryColumn.name>
            <if test="${column.propertyName} != null" >
                <#noparse>#{</#noparse>${column.propertyName},jdbcType=${column.type?upper_case}<#noparse>}</#noparse>,
            </if>
            </#if>
            </#list>
        </trim>
    </insert>
    <update id="update${domain}" parameterType="${domainPackage}.${domain}" >
        update ${table.name}
        <set>
            <#list table.columns as column>
            <#if column.name != table.primaryColumn.name>
            <if test="${column.propertyName} != null" >
                `${column.name}` = <#noparse>#{</#noparse>${column.propertyName},jdbcType=${column.type?upper_case}<#noparse>}</#noparse>,
            </if>
            </#if>
            </#list>
        </set>
        where ${table.primaryColumn.name} = <#noparse>#{</#noparse>${table.primaryColumn.propertyName},jdbcType=${table.primaryColumn.type?upper_case}<#noparse>}</#noparse>
    </update>

</mapper>
