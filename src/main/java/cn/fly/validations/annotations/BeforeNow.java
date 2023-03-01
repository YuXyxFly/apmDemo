package cn.fly.validations.annotations;

import cn.fly.validations.validation.BeforeNowValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author fly
 * @date 2023/2/27
 * @description
 */

@Documented
@Constraint(validatedBy = {BeforeNowValidation.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface BeforeNow {

    String message() default "时间须在今天之后!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
