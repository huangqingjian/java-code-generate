package com.huangqj.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huangqj.common.dto.*;
import com.huangqj.common.bean.PageQueryBean;
import com.huangqj.service.BannerService;
import com.huangqj.utils.BeanUtils;
import com.huangqj.dto.BannerDTO;
import com.huangqj.bean.BannerBean;
import com.huangqj.domain.Banner;
import com.huangqj.mapper.BannerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* banner服务
*
* author: huangqj
* date: 2022-10-31 13:31:33
*/
@Service
public class BannerServiceImpl implements BannerService {
    private static final Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageDTO<BannerDTO> list(PageQueryDTO queryDTO) {
        log.info("the {}.list parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(queryDTO));
        PageQueryBean query = BeanUtils.map(queryDTO, PageQueryBean.class);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<Banner> domains = bannerMapper.selectByQuery(query);
        PageInfo<BannerDTO> pageInfo = new PageInfo(domains);
        // 转换为PageDTO
        PageDTO<BannerDTO> page = BeanUtils.map(pageInfo, PageDTO.class);
        page.setList(BeanUtils.mapList(pageInfo.getList(), BannerDTO.class));
        return page;
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public BannerDTO find(Long id) {
        log.info("the {}.find parameter: [{}]", this.getClass().getSimpleName(), id);
        Banner domain = bannerMapper.selectByPrimaryKey(id);
        if(domain != null) {
            BannerDTO dto = BeanUtils.map(domain, BannerDTO.class);
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
    public Long save(BannerDTO dto) {
        log.info("the {}.save parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        Banner domain = BeanUtils.map(dto, Banner.class);
        bannerMapper.insertBanner(domain);
        return domain.getId();
    }

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Long update(BannerDTO dto) {
        log.info("the {}.update parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        Banner domain = bannerMapper.selectByPrimaryKey(dto.getId());
        if(domain != null) {
            Banner banner = BeanUtils.map(dto, Banner.class);
            bannerMapper.updateBanner(banner);
        }
        return dto.getId();
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.info("the {}.delete parameter: [{}]", this.getClass().getSimpleName(), id);
        bannerMapper.deleteByPrimaryKey(id);
    }

}
