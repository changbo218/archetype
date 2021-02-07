#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cat;

import javax.annotation.Resource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SqlSessionPostProcessor implements BeanPostProcessor {

    @Resource
    private CatMybatisInterceptor mybatisInterceptor;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof SqlSessionFactoryBean) {
            //添加cat 拦截器监控
            ((SqlSessionFactoryBean) bean).setPlugins(new Interceptor[] { mybatisInterceptor });
        }
        return bean;
    }
}