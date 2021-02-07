#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cache;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Guava实现类 示例
 */
@Slf4j
@Component
public class DemoLoadingCache extends GuavaAbstractLoadingCache<String, String> implements
                              ILocalCache<String, String> {

    @Override
    public String get(String key) {
        try {
            return getValue(key);
        } catch (Exception e) {
            log.error("根据 {} 在本地缓存 {} 中取值失败", key, this.getClass().getSimpleName(), e);
            return null;
        }
    }

    @Override
    protected String fetchData(String key) {
        return "本地缓存 key: " + key;
    }
}
