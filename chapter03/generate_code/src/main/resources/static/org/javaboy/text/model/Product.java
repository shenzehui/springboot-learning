package org.javaboy.text.model;

import java.util.Date;
import java.math.BigDecimal;

public class Product{

                /**
                * 
                */
                private Integer productid;
                /**
                * 
                */
                private String productname;
                /**
                * 
                */
                private String productimage;
                /**
                * 
                */
                private Double productprice;
                /**
                * 
                */
                private Integer productcount;

                public Integer getProductid(){
                    return productid;
                }
                public void setProductid(Integer productid){
                    this.productid = productid;
                }
                public String getProductname(){
                    return productname;
                }
                public void setProductname(String productname){
                   this.productname = productname;
                }
                public String getProductimage(){
                    return productimage;
                }
                public void setProductimage(String productimage){
                   this.productimage = productimage;
                }
                public Double getProductprice(){
                    return productprice;
                }
                public void setProductprice(Double productprice){
                    this.productprice = productprice;
                }
                public Integer getProductcount(){
                    return productcount;
                }
                public void setProductcount(Integer productcount){
                    this.productcount = productcount;
                }

}