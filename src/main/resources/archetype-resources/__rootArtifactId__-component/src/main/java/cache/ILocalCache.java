#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cache;

/**
 * Guava实现类必须实现的接口
 */
public interface ILocalCache<K, V> {

    V get(K key);
}
