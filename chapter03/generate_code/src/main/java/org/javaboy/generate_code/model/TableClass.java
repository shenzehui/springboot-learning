package org.javaboy.generate_code.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 数据表
 *
 * @author szh
 */
@Getter
@Setter
public class TableClass {

    private String tableName;

    private String modelName;

    private String serviceName;

    private String controllerName;

    private String mapperName;

    private String packageName;

    /**
     * 创建日期
     */
    private String createTime;

    /**
     * 表描述
     */
    private String tableDescription;

    private List<ColumnClass> columns;

    @Override
    public String toString() {
        return "TableClass{" + "tableName='" + tableName + '\'' + ", modelName='" + modelName + '\'' + ", serviceName='" + serviceName + '\'' + ", controllerName='" + controllerName + '\'' + ", mapperName='" + mapperName + '\'' + ", packageName='" + packageName + '\'' + ", columns=" + columns + '}';
    }
}
