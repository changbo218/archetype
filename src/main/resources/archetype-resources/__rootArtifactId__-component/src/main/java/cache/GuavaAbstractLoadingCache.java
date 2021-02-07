#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import ${package}.util.ThreadTaskUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Guava抽象封装类
 */
@Slf4j
@Data
public abstract class GuavaAbstractLoadingCache<K, V> {

    private int refreshAfterWriteDuration = 10;
    private TimeUnit timeUnit = TimeUnit.MINUTES;

    private LoadingCache<K, V> cache;

    private LoadingCache<K, V> getCache() {
        if (cache == null) {
            synchronized (this) {
                if (cache == null) {
                    cache = CacheBuilder.newBuilder()
                            .refreshAfterWrite(refreshAfterWriteDuration, timeUnit)
                            .build(new CacheLoader<K, V>() {

                                @Override
                                public V load(@Nonnull K key) {
                                    return fetchData(key);
                                }

                                @Override
                                public ListenableFuture<V> reload(final K key, V value) {
                                    // 异步刷新
                                    ListenableFutureTask<V> task = ListenableFutureTask
                                            .create(() -> fetchData(key));
                                    ThreadTaskUtils.run(task);
                                    return task;
                                }
                            });
                    log.info("本地缓存 {} 初始化成功", this.getClass().getSimpleName());
                }
            }
        }

        return cache;
    }

    protected abstract V fetchData(K key);

    protected V getValue(K key) throws ExecutionException {
        return getCache().get(key);
    }
}
