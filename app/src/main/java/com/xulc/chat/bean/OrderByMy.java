package com.xulc.chat.bean;

/**
 * Created by 徐椋春 on 2016/8/28.
 */
public class OrderByMy {
    private String columnName;
    private boolean desc;

    public OrderByMy(String columnName, boolean desc) {
        this.columnName = columnName;
        this.desc = desc;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }
}
