#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 配置
 *
 * Write the code. Change the world.
 */
@Configuration
class SpringMvcConfig {

    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * 路径匹配配置
             */
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                configurer
                        // 是否开启后缀模式匹配，如 '/user' 是否匹配 '/user.*'，默认 true
                        .setUseSuffixPatternMatch(false)
                        // 是否开启后缀路径模式匹配，如 '/user' 是否匹配 '/user/'，默认 true
                        .setUseTrailingSlashMatch(true);
            }

            /**
             * 将对于静态资源的请求转发到 Servlet 容器的默认处理静态资源的 Servlet
             * 因为将 Spring 的拦截模式设置为 "/" 时会对静态资源进行拦截
             */
            @Override
            public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
                configurer.enable();
            }

        };
    }

}