#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;

import javax.annotation.Resource;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ${package}.i18n.MessageSources;

import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常处理 - 到达API请求后
 */
@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @Resource
    private MessageSources messageSources;

    @ExceptionHandler(Throwable.class)
    public Result<Object> errorHandlerOverJson(Exception e) {
        String message;
        if (e instanceof BusinessException) {
            BusinessErrorInterface error = ((BusinessException) e).getBusinessErrorInterface();
            if (ObjectUtils.isEmpty(error)) {
                if (StringUtils.isEmpty(e.getMessage())) {
                    message = messageSources.get(ResultCode.FAIL.getMessage());
                    return Result.fail(ResultCode.FAIL.getResult(), message);
                }
                return Result.fail(ResultCode.FAIL.getResult(), e.getMessage());
            }
            message = messageSources.get(error.getMessage());
            return Result.fail(error.getResult(), message);
        }

        log.error("system occur error: ", e);

        message = messageSources.get(ResultCode.SYSTEM_ERROR.getMessage());
        return Result.fail(ResultCode.SYSTEM_ERROR.getResult(), message);
    }

}
