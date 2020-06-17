package cn.pomit.springwork.netty.Ditu;

import lombok.Data;

/*
地图
 */
@Data
public class Ditu {
    private Integer mid;
    //地图名称m
    private String mname;
    //地图描述
    private String mdesc;
    //临近的地图
    private String neighbor;
}
