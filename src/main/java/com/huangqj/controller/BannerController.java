package com.huangqj.controller;

import com.huangqj.dto.BannerDTO;
import com.huangqj.dto.PageDTO;
import com.huangqj.dto.PageQueryDTO;
import com.huangqj.dto.ResponseDTO;
import com.huangqj.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * banner管理器
 *
 * author: huangqj
 * date: 2022-10-31 13:31:33
 */
@Api(tags = "banner管理器", value = "Banner.Manager")
@Controller
@RequestMapping(value="/banner")
public class BannerController {
    private static final Logger log = LoggerFactory.getLogger(BannerController.class);

    private static final String LIST = "/banner/list";
    private static final String ADD = "/banner/add";
    private static final String EDIT = "/banner/edit";
    private static final String DETAIL = "/banner/detail";

    @Autowired
    private BannerService bannerService;

    /**
     * Banner列表页
     *
     * @return
     */
    @ApiOperation(value = "Banner列表页")
    @RequestMapping(value = "list.html")
    public String list() {
        return LIST;
    }

    /**
     * Banner新增页
     *
     * @return
     */
    @ApiOperation(value = "Banner新增页")
    @RequestMapping(value = "add.html")
    public String add() {
        return ADD;
    }

    /**
     * Banner编辑页
     *
     * @param id
     * @param model
     * @return
     */
    @ApiOperation(value = "Banner编辑页")
    @RequestMapping(value = "/{id}.html")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return EDIT;
    }

    /**
     * Banner详情页
     *
     * @param id
     * @param model
     * @return
     */
    @ApiOperation(value = "Banner详情页")
    @RequestMapping(value = "detail/{id}.html")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return DETAIL;
    }

    /**
     * Banner列表
     *
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "Banner列表")
    @GetMapping(value = "list")
    @ResponseBody
    public ResponseDTO<PageDTO<BannerDTO>> list(PageQueryDTO queryDTO) {
        PageDTO<BannerDTO> pageDTO = bannerService.list(queryDTO);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * Banner查找
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Banner查找")
    @GetMapping(value = "get/{id}")
    @ResponseBody
    public ResponseDTO<BannerDTO> get(@PathVariable("id") Long id) {
        BannerDTO dto = bannerService.find(id);
        return ResponseDTO.success(dto);
    }

    /**
     * Banner新增
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "Banner新增")
    @PostMapping(value = "save")
    @ResponseBody
    public ResponseDTO<Long> save(@RequestBody BannerDTO dto) {
        Long id = bannerService.save(dto);
        return ResponseDTO.success(id);
    }

    /**
     * Banner更新
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "Banner更新")
    @PostMapping(value = "update")
    @ResponseBody
    public ResponseDTO<Long> update(@RequestBody BannerDTO dto) {
        Long id = bannerService.update(dto);
        return ResponseDTO.success(id);
    }

    /**
     * Banner删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Banner删除")
    @PostMapping(value = "delete/{id}")
    @ResponseBody
    public ResponseDTO<Boolean> delete(@PathVariable("id") Long id) {
        bannerService.delete(id);
        return ResponseDTO.success(Boolean.TRUE);
    }

}
