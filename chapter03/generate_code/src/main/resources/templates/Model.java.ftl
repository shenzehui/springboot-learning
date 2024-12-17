package ${packageName}.model;

import java.util.Date;
import java.math.BigDecimal;

/**
 * <p>
 * ${tableDescription}
 * </p>
 *
 * @author 沈泽辉
 * @since ${createTime}
 */
@Getter
@Setter
@TableName("${tableName}")
@Schema(name = "${modelName}对象", description = "${tableDescription}")
public class ${modelName} {
<#if columns??>
<#list columns as column>
<#if column.type='VARCHAR'||column.type='TEXT'||column.type='CHAR'>

    @Schema(description = "${column.remark}")
    private String ${column.propertyName?uncap_first};
</#if>
<#if column.type='INT'>

    @Schema(description = "${column.remark}")
    private Integer ${column.propertyName?uncap_first};
</#if>
<#if column.type='DATETIME'>

    @Schema(description = "${column.remark}")
    private Date ${column.propertyName?uncap_first};
</#if>
<#if column.type='BIGINT'>

    @Schema(description = "${column.remark}")
    private Long ${column.propertyName?uncap_first};
</#if>
<#if column.type='DOUBLE'>

    @Schema(description = "${column.remark}")
    private Double ${column.propertyName?uncap_first};
</#if>
<#if column.type='BIT'>

    @Schema(description = "${column.remark}")
    private Boolean ${column.propertyName?uncap_first};
</#if>
<#if column.type='DECIMAL'>

    @Schema(description = "${column.remark}")
    private BigDecimal ${column.propertyName?uncap_first};
</#if>
</#list>
</#if>

}