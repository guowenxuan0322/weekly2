package com.zdpx.weekly.enums;

public enum SelectTypeEnum {
    CURRENTWEEK("currentWeek"),
    LASTWEEK("lastWeek"),
    ALL("all");
    private String code;

    //枚举类型的构造函数默认为private，因为枚举类型的初始化要在当前枚举类中完成。
    SelectTypeEnum (String code){
        this.code= code;
    }

    public String getCode(){
        return code;
    }
}
