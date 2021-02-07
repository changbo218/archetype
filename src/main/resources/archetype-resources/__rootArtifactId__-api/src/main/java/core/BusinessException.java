#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */
public class BusinessException extends RuntimeException {

    @Setter
    @Getter
    private BusinessErrorInterface businessErrorInterface;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(BusinessErrorInterface businessErrorInterface) {
        super(businessErrorInterface.getMessage());
        this.businessErrorInterface = businessErrorInterface;
    }

    public BusinessException(String message, BusinessErrorInterface businessErrorInterface) {
        super(message);
        this.businessErrorInterface = businessErrorInterface;
    }

}
