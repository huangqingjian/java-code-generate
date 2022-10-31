package com.huangqj.generate;

/**
 * 类型转换器（目前只支持mysql，其它数据库自行实现）
 */
public class TypeConvert {
    /**
     * 类型转换
     *
     * @param fieldType
     * @return
     */
    public ColumnType process(String fieldType) {
        String t = fieldType.toLowerCase();
        if (!t.contains("char") && !t.contains("text")) {
            if (t.contains("bigint")) {
                return ColumnType.LONG;
            } else if (t.contains("int")) {
                return ColumnType.INTEGER;
            } else if (!t.contains("date") && !t.contains("time") && !t.contains("year")) {
                if (t.contains("text")) {
                    return ColumnType.STRING;
                } else if (t.contains("bit")) {
                    return ColumnType.BOOLEAN;
                } else if (t.contains("decimal")) {
                    return ColumnType.BIG_DECIMAL;
                } else if (t.contains("clob")) {
                    return ColumnType.CLOB;
                } else if (t.contains("blob")) {
                    return ColumnType.BLOB;
                } else if (t.contains("binary")) {
                    return ColumnType.BYTE_ARRAY;
                } else if (t.contains("float")) {
                    return ColumnType.FLOAT;
                } else if (t.contains("double")) {
                    return ColumnType.DOUBLE;
                } else {
                    return !t.contains("json") && !t.contains("enum") ? ColumnType.STRING : ColumnType.STRING;
                }
            } else {
                return ColumnType.DATE;
            }
        } else {
            return ColumnType.STRING;
        }
    }
}

