package org.javaboy.text.model;

import java.util.Date;
import java.math.BigDecimal;

public class OrderItem{

                /**
                * 订单项ID
                */
                private String itemId;
                /**
                * 订单ID
                */
                private String orderId;
                /**
                * 商品ID
                */
                private String productId;
                /**
                * 商品名称
                */
                private String productName;
                /**
                * 商品图片
                */
                private String productImg;
                /**
                * skuID
                */
                private String skuId;
                /**
                * sku名称
                */
                private String skuName;
                /**
                * 商品价格
                */
                private BigDecimal productPrice;
                /**
                * 购买数量
                */
                private Integer buyCounts;
                /**
                * 商品总金额
                */
                private BigDecimal totalAmount;
                /**
                * 加入购物车时间
                */
                private Date basketDate;
                /**
                * 购买时间
                */
                private Date buyTime;
                /**
                * 评论状态： 0 未评价  1 已评价
                */
                private Integer isComment;

                public String getItemId(){
                    return itemId;
                }
                public void setItemId(String itemId){
                   this.itemId = itemId;
                }
                public String getOrderId(){
                    return orderId;
                }
                public void setOrderId(String orderId){
                   this.orderId = orderId;
                }
                public String getProductId(){
                    return productId;
                }
                public void setProductId(String productId){
                   this.productId = productId;
                }
                public String getProductName(){
                    return productName;
                }
                public void setProductName(String productName){
                   this.productName = productName;
                }
                public String getProductImg(){
                    return productImg;
                }
                public void setProductImg(String productImg){
                   this.productImg = productImg;
                }
                public String getSkuId(){
                    return skuId;
                }
                public void setSkuId(String skuId){
                   this.skuId = skuId;
                }
                public String getSkuName(){
                    return skuName;
                }
                public void setSkuName(String skuName){
                   this.skuName = skuName;
                }
                public BigDecimal getProductPrice(){
                    return productPrice;
                }
                public void setProductPrice(BigDecimal productPrice){
                this.productPrice = productPrice;
                }
                public Integer getBuyCounts(){
                    return buyCounts;
                }
                public void setBuyCounts(Integer buyCounts){
                    this.buyCounts = buyCounts;
                }
                public BigDecimal getTotalAmount(){
                    return totalAmount;
                }
                public void setTotalAmount(BigDecimal totalAmount){
                this.totalAmount = totalAmount;
                }
                public Date getBasketDate(){
                    return basketDate;
                }
                public void setBasketDate(Date basketDate){
                    this.basketDate = basketDate;
                }
                public Date getBuyTime(){
                    return buyTime;
                }
                public void setBuyTime(Date buyTime){
                    this.buyTime = buyTime;
                }
                public Integer getIsComment(){
                    return isComment;
                }
                public void setIsComment(Integer isComment){
                    this.isComment = isComment;
                }

}