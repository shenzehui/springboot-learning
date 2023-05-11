package org.javaboy.text.model;

import java.util.Date;
import java.math.BigDecimal;

public class User{

                /**
                * 标识
                */
                private Integer uId;
                /**
                * 姓名
                */
                private String uName;
                /**
                * 密码
                */
                private String uPassword;
                /**
                * 性别
                */
                private String uSex;
                /**
                * 年龄
                */
                private Integer uAge;
                /**
                * 地址
                */
                private String uAddress;
                /**
                * 移动电话号码
                */
                private String uTelephone;
                /**
                * 
                */
                private String uEmail;
                /**
                * 注册时间
                */
                private Date rTime;
                /**
                * 最后一次登录时间
                */
                private Date lTime;

                public Integer getUId(){
                    return uId;
                }
                public void setUId(Integer uId){
                    this.uId = uId;
                }
                public String getUName(){
                    return uName;
                }
                public void setUName(String uName){
                   this.uName = uName;
                }
                public String getUPassword(){
                    return uPassword;
                }
                public void setUPassword(String uPassword){
                   this.uPassword = uPassword;
                }
                public String getUSex(){
                    return uSex;
                }
                public void setUSex(String uSex){
                   this.uSex = uSex;
                }
                public Integer getUAge(){
                    return uAge;
                }
                public void setUAge(Integer uAge){
                    this.uAge = uAge;
                }
                public String getUAddress(){
                    return uAddress;
                }
                public void setUAddress(String uAddress){
                   this.uAddress = uAddress;
                }
                public String getUTelephone(){
                    return uTelephone;
                }
                public void setUTelephone(String uTelephone){
                   this.uTelephone = uTelephone;
                }
                public String getUEmail(){
                    return uEmail;
                }
                public void setUEmail(String uEmail){
                   this.uEmail = uEmail;
                }
                public Date getRTime(){
                    return rTime;
                }
                public void setRTime(Date rTime){
                    this.rTime = rTime;
                }
                public Date getLTime(){
                    return lTime;
                }
                public void setLTime(Date lTime){
                    this.lTime = lTime;
                }

}