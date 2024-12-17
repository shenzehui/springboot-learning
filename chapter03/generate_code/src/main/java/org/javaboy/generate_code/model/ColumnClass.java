package org.javaboy.generate_code.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述表中的每一列
 *
 * @author szh
 */
@Getter
@Setter
public class ColumnClass {
    /**
     * 数据库属性对应Java属性的名字
     */
    private String propertyName;
    /**
     * 数据库中的名字
     */
    private String columnName;
    /**
     * 类型
     */
    private String type;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否为主键
     */
    private Boolean isPrimary;

    @Override
    public String toString() {
        return "ColumnClass{" + "propertyName='" + propertyName + '\'' + ", columnName='" + columnName + '\'' + ", type='" + type + '\'' + ", remark='" + remark + '\'' + ", isPrimary=" + isPrimary + '}';
    }

}
