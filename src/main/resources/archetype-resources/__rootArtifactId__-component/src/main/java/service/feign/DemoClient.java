#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Feign定义
 */
@FeignClient(name = "DEMO", url = "https://www.changbo.com")
public interface DemoClient {

    @RequestMapping("/")
    String get();
}
