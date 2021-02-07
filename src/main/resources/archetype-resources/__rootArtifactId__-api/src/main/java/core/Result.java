#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;

import lombok.Data;

/**
 * 统一返回结果
 */
@Data
public class Result<T> {

    private int result;
    private String message;
    private T data;

    public Result(int result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return new Result<>(ResultCode.SUCCESS.getResult(),
                ResultCode.SUCCESS.getMessage().toUpperCase(), null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(ResultCode.SUCCESS.getResult(),
                ResultCode.SUCCESS.getMessage().toUpperCase(), data);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultCode.FAIL.getResult(), message, null);
    }

    public static <T> Result<T> fail(int result, String message) {
        return new Result<>(result, message, null);
    }

    public static <T> Result<T> fail(BusinessErrorInterface e) {
        return new Result<>(e.getResult(), e.getMessage(), null);
    }
}