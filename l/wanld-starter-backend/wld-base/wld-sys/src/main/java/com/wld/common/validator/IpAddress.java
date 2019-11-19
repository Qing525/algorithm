package com.wld.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * @author 王增光
 * @date 2019/9/5
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IpAddressValidator.class)
@Documented
public @interface IpAddress {

    String message() default "{IpAddress.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}