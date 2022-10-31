package com.huangqj.generate;

/**
 * 列类型
 */
public enum ColumnType {
    STRING("String", "java.lang.String", true),
    LONG("Long", "java.lang.Long", true),
    INTEGER("Integer", "java.lang.Integer", true),
    FLOAT("Float", "java.lang.Float", true),
    DOUBLE("Double", "java.lang.Double", true),
    BOOLEAN("Boolean", "java.lang.Boolean", true),
    BYTE_ARRAY("byte[]", null, true),
    OBJECT("Object", "java.lang.Object", true),
    DATE("Date", "java.util.Date", false),
    BLOB("Blob", "java.sql.Blob", false),
    CLOB("Clob", "java.sql.Clob", false),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal", false)
    ;

    private String type;
    private String pkg;
    private boolean autoImported;

    ColumnType(String type, String pkg, boolean autoImported) {
        this.type = type;
        this.pkg = pkg;
        this.autoImported = autoImported;
    }

    public String getType() {
        return this.type;
    }

    public String getPkg() {
        return this.pkg;
    }

    public boolean getAutoImported() {
        return this.autoImported;
    }
}

