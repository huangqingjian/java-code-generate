package com.huangqj.generate;

import com.huangqj.utils.CommonUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 数据库配置
 */
public class DataSourceConfig {
    /**
     * 数据库连接url
     */
    private String url;
    /**
     * 数据库驱动
     */
    private String driverName;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 数据库连接
     */
    private Connection connection;
    /**
     * 数据库类型转换器
     */
    private TypeConvert typeConvert = new TypeConvert();

    /**
     * 初始化数据库连接
     *
     * @return
     */
    public Connection initConnection() {
        try {
            if (connection != null) {
                return connection;
            }
            Class.forName(this.driverName);
            Properties props = new Properties();
            props.setProperty("user", this.username);
            props.setProperty("password", this.password);
            props.setProperty("remarks", "true");
            props.setProperty("useInformationSchema", "true");
            return connection = DriverManager.getConnection(this.url, props);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("get db connection failure");
        }
    }

    /**
     * 关闭数据库连接
     *
     * @param
     */
    public void closeConnection(){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("close db connection failure");
        }
    }

    /**
     * 获取生成的表信息
     *
     * @param tables
     * @return
     */
    public List<Table> getTableList(String[] tables) {
        initConnection();
        List<Table> tableList = new ArrayList();
        for(int i = 0; i < tables.length; ++i){
            Table table = this.getTable(tables[i]);
            table.setName(tables[i]);
            tableList.add(table);
        }
        closeConnection();
        return tableList;
    }

    /**
     * 根据表名获取该表字段
     *
     * @param tableName
     * @return
     */
    public Table getTable(String tableName){
        Table table = new Table();
        table.setColumns(new ArrayList());
        Statement stm = null;
        ResultSet rs = null;
        try {
            DatabaseMetaData dmd = connection.getMetaData();
            // 注释
            ResultSet tableRs = dmd.getTables(null, null, tableName.toUpperCase(), null);
            String tableComment = null;
            while(tableRs.next()){
                tableComment = tableRs.getString("REMARKS");
            }
            table.setComment(tableComment);
            // 主键
            ResultSet primaryRs = dmd.getPrimaryKeys(null,null, tableName.toUpperCase());
            String primaryKey = null;
            while(primaryRs.next()){
                primaryKey = primaryRs.getString("COLUMN_NAME").toLowerCase();
            }
            rs = dmd.getColumns(   null, null, tableName.toUpperCase(), "%");
            while(rs.next()){
                TableColumn column = new TableColumn();
                String columnName = rs.getString("COLUMN_NAME").toLowerCase();
                // 表字段名
                column.setName(columnName);
                // 字段类型
                column.setType(rs.getString("TYPE_NAME"));
                // 字段名
                column.setPropertyName(CommonUtils.getNoUnderlineStr(columnName));
                // 字段注释
                column.setComment(rs.getString("REMARKS"));
                column.setColumnType(typeConvert.process(column.getType()));
                table.getColumns().add(column);
                if(columnName.equals(primaryKey)) {
                    table.setPrimaryColumn(column);
                }
            }
            if(tableRs != null){
                tableRs.close();
            }
            if(primaryRs != null){
                primaryRs.close();
            }
            if(rs != null){
                rs.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return table;
    }


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}