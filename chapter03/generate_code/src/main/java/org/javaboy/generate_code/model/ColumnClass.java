package org.javaboy.generate_code.model;

/**
 * 描述表中的每一列
 *
 * @author szh
 */
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

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }


}
