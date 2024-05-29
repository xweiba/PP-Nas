package pp.weiba.framework.core;
import lombok.extern.log4j.Log4j2;

/**
* 标识当前实现的类型
*
* @author weiba
* @date 2024/5/28 13:05
*/
public interface IBusinessType {
    
    String getBusinessType();

    default boolean BusinessTypeEquals(String businessType) {
        return getBusinessType().equals(businessType);
    }
    
}
