package com.huangqj.service;

import com.huangqj.dto.BannerDTO;
import com.huangqj.dto.PageDTO;
import com.huangqj.dto.PageQueryDTO;

/**
 * banner服务
 *
 * author: huangqj
 * date: 2022-10-31 13:31:33
 */
public interface BannerService {
    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    PageDTO<BannerDTO> list(PageQueryDTO queryDTO);

    /**
     * 查找
     *
     * @param id
     * @return
     */
     BannerDTO find(Long id);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long save(BannerDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    Long update(BannerDTO dto);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);
}
