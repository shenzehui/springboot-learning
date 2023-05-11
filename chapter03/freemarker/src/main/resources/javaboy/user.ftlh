<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>$Title$</title>
</head>
<body>

<#--宏-->
<#--引入宏-->
<#import "./mymacro.ftlh" as com>

<@com.book bs=["三国演义","水浒传"]>
    <h1>hello javaboy</h1>
</@com.book>
<hr>

<#--switch-case-->
<div>
    <#assign age=99>
    <#switch age>
        <#case 23>23<#break>
        <#case 24>24<#break>
        <#default>9999
    </#switch>
</div>
<hr>
<#--if else if -->
<div>
    <#assign age=23>
    <#if (age>60)>老年人
    <#elseif (age>40)>中年人
    <#elseif (age>20)>青年人
    <#else> 少年人
    </#if>
</div>
<hr>

<div>
    <#--首字母大写-->
    <div>${"hello"?cap_first}</div>
    <#--转为小写-->
    <div>${"HELLO"?lower_case}</div>
    <#--转为大写-->
    <div>${"hello"?upper_case}</div>
    <#--去空格-->
    <div>${"hello "?trim}</div>
    <#--取集合大小-->
    <div>${users?size}</div>
    <#--取小数部分-->
    <div>${3.14?int}</div>
    <#--日期格式化-->
    <div>${birthday?string("yyyy-MM-dd")}</div>
</div>
<hr>
<#--空值处理-->
<div>${aaa!"aaa"}</div>
<div>
    <#if aaa??>
        aaa
    </#if>
</div>
<hr>
<#--比较运算符-->
<div>
    <#assign age=99>
    <#if age=99>age=99</#if>
    <#if age gt 99>age gt 99</#if>
    <#if age gte 99>age gte 99</#if>
    <#if age lt 99>age lt 99</#if>
    <#if age lte 99>age lte 99</#if>
    <#if age!=99>age!=99</#if>
    <#if age==99>age==99</#if>
</div>
<hr>
<#--数组的相加-->
<#list [1,2,3]+[4,5,6] as x>
    <div>${x}</div>
</#list>
<hr>
<#--Map的相加 添加了一个key和value-->
<#list (info+{"address":"www.org.javaboy"})?keys as key>
    <div>${key}</div>
</#list>
<hr>
<#--String的截取与拼接-->
<div>${"hello ${name}"}</div>
<div>${"hello"+name}</div>
<div>${name[0]}${name[1]}</div>
<div>${name[1..3]}</div>
<hr>
<#--Map集合-->
<div>${info.name}</div>
<div>${info['age']}</div>
<hr>
<#list info?keys as key>
    <div>${key}</div>
    <div>${info[key]}</div>
</#list>
<hr>
<#list info?values as value>
    <div>${value}</div>
</#list>
<hr>
<#--基础变量-->
<div>${name}</div>
<#--对象集合-->
<table border="1">
    <#list users as u>
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.address}</td>
            <td>${u.gender}</td>
        </tr>
    </#list>
</table>
<hr>
<table border="1">
    <#list users[3..5] as u>
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.address}</td>
            <td>${u.gender}</td>
            <#--正在遍历的集合下标-->
            <td>${u_index}</td>
            <#--有没有下一个元素-->
            <td>${u_has_next?string('yes','no')}</td>
        </tr>
    </#list>
</table>
<#--自定义Map-->
<#assign userinfo={"name":"javaboy","address":"www.org.javaboy"}>

<#--遍历Map-->
<#list userinfo?keys as key>
    <div>${key}-${userinfo[key]}</div>
</#list>
<hr>
<#list userinfo?values as value>
    <div>${value}</div>
</#list>
<hr>
<div>${userinfo.name}</div>
<div>${userinfo['address']}</div>
<hr>

<#--noparse只显示文本-->
<#noparse>
    <#include './header.ftl'>
</#noparse>

<#--引入其他页面-->
<#include 'header.ftlh'>

<#--定义集合-->
<#list 1..5 as x>
    <div>${x}</div>
</#list>
<hr>
<#list 5..1 as x>
    <div>${x}</div>
</#list>
<hr>

<#list [2+2,"javaboy","javairl"] as x>
    <div>${x}</div>
</#list>

<#--定义变量-->
<#assign flage=true>
<div>${flage?string("yes","no")}</div>

<#assign price=99>
<div>${price?string.currency}</div>
<div>${price?string.percent}</div>

<#--原本输出-->
${"hello"}
${"world"}
<div>${r"C:\"}</div>

<#--老版本用法-->
<#--禁止渲染标签-->
<#--${hello?html}-->
<#--${world?html}-->
<#--在后面加?html-->
<#--<#escape x as x?html>
    ${hello}
    ${world}
</#escape>-->

  <table border="1">
    <tr>
      <td>编号</td>
      <td>用户名</td>
      <td>用户地址</td>
      <td>性别</td>
    </tr>
    <#list users as u>
        <#--
        <#if u.id == 4>
            <#break >
        </#if>
        -->
        <tr>
          <td>${u.id}</td>
          <td>${u.username}</td>
          <td>${u.address}</td>
          <td>
            <#--  <#if u.gender==0>
                  男
              <#elseif u.gender==1>
                  女
              <#else>
                  未知
              </#if>-->
              <#switch u.gender>
                  <#case 0>男<#break >
                  <#case 1>女<#break >
                  <#default >未知<#break >
              </#switch>
          </td>
        </tr>
    </#list>
  </table>
</body>
</html>