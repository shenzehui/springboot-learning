<#macro book bs>
    <table>
        <#list bs as b>
            <tr>
                <td>${b}</td>
            </tr>
        </#list>
    </table>
<#--占位符 内容是booke标签中的内容-->
    <#nested>
</#macro>