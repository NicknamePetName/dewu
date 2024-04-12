package com.youkeda.dewu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品参考价格
     */
    private Double price;
    /**
     * 商品销量
     */
    private Integer purchaseNum;
    /**
     * 商品介绍
     */
    private String productIntro;
    /**
     * 商品轮播图片,这里会存储多个 URL,用空格隔开,第一张图就是缩略图
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getProductIntro() {
        return productIntro;
    }

    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
