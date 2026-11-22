package com.example.venue.common;
import lombok.Data;

@Data
public class Result<T>{
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data){  //有参 "T data"：入参，要返回的业务数据。
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }
    public static <T> Result<T> success(){  //无参 有些接口只表示"操作成功"，不需要返回数据（比如取消预约、删除）
        return success(null);  //直接调用上面刚写的success(T data)方法,data传入的是null,不用再写一遍setCode、setMsg,节省重复步骤
    }
    public static <T> Result<T> fail(Integer code, String msg){
        Result<T> result = new Result<>();
        result.setCode(code);  //入参 code：失败状态码（如 400参数错误、401未登录、500服务器错误）
        result.setMsg(msg);  //入参 msg：失败原因提示
        return result;  //没有 setData：失败时一般不返回业务数据
    }
}

