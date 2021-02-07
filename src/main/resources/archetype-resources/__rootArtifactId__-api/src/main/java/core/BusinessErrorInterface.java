#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;

/**
 * 自定义 返回结果数据结构 接口
 */
public interface BusinessErrorInterface {

    int getResult();

    String getMessage();
}
