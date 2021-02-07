#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ${package}.service.feign.DemoClient;

import io.swagger.annotations.Api;

/**
 * Feign测试示例
 */
@Api(tags = "测试", description = "information about test")
@RestController
public class HomeController {

    @Resource
    private DemoClient demoClient;

    @GetMapping("/")
    public String index() {
        return "Hello";
    }

    @GetMapping("/demo")
    public String demo() {
        return demoClient.get();
    }
}
