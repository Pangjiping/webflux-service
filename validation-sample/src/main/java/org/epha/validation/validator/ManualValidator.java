package org.epha.validation.validator;

import java.util.List;

/**
 * @author pangjiping
 */
public interface ManualValidator {

    void validate(Object object);

    void validate(Object object, List<Class<?>> groupClasses);

}
