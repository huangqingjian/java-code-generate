package com.huangqj.mapper;

import com.huangqj.domain.Banner;
import java.util.List;
import com.huangqj.bean.PageQueryBean;
import org.apache.ibatis.annotations.Param;

/**
 * banner mapper
 *
 * author: huangqj
 * date: 2022-10-31 13:31:33
 */
public interface BannerMapper {
    /**
     * 查询banner列表
     *
     * @param queryBean
     * @return
     */
    List<Banner> selectByQuery(PageQueryBean queryBean);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    Banner selectByPrimaryKey(@Param("id") Long id);

    /**
     * 新增
     *
     * @param domain
     */
    void insertBanner(Banner domain);

    /**
     * 更新
     *
     * @param domain
     */
    void updateBanner(Banner domain);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(@Param("id") Long id);
}