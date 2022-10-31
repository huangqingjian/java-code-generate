package com.huangqj.generate;

import java.util.*;

/**
 * 表信息
 */
public class Table {
    /**
     * 表名
     */
    private String name;
    /**
     * 主键列
     */
    private TableColumn primaryColumn;
    /**
     * 表列
     */
    private List<TableColumn> columns;
    /**
     * 表comment
     */
    private String comment = "";

    public Table() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TableColumn getPrimaryColumn() {
        return primaryColumn;
    }

    public void setPrimaryColumn(TableColumn primaryColumn) {
        this.primaryColumn = primaryColumn;
    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
