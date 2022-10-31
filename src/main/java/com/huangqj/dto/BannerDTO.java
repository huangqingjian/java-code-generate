package com.huangqj.dto;

import java.util.Date;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* banner DTO
*
* author: huangqj
* date: 2022-10-31 13:31:33
*/
@ApiModel("bannerDTO")
public class BannerDTO {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 链接
     */
    @ApiModelProperty("链接")
    private String url;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private String pic;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String desc;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    private Integer order;

    /**
     * 0-正常，1-删除
     */
    @ApiModelProperty("0-正常，1-删除")
    private Integer deleted;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private Long updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}