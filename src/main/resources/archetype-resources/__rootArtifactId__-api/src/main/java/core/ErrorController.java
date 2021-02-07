#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${package}.i18n.MessageSources;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 统一异常处理 - 到达API请求前
 */
@ApiIgnore
@Slf4j
@RestController
public class ErrorController extends AbstractErrorController {

    private static final String PATH = "/error";
    @Resource
    private MessageSources messageSources;

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(value = PATH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> handleError(HttpServletRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request, false);
        log.error("system occur error: {}", errorAttributes);
        String message = messageSources.get(ResultCode.SYSTEM_ERROR.getMessage());
        return Result.fail(ResultCode.SYSTEM_ERROR.getResult(), message);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
