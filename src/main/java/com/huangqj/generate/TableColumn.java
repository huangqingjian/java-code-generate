package com.huangqj.generate;

/**
 * 表列
 */
public class TableColumn {
    /**
     * 列名
     */
    private String name;
    /**
     * 列类型
     */
    private String type;
    /**
     * 属性名
     */
    private String propertyName;
    /**
     * 属性类型
     */
    private ColumnType columnType;
    /**
     * comment
     */
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        if("int".equalsIgnoreCase(type)) {
            return "integer";
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPropertyType() {
        return null != this.columnType ? this.columnType.getType() : null;
    }

    public String getCapitalName() {
        return this.propertyName.substring(0, 1).toUpperCase() + this.propertyName.substring(1);
    }
}