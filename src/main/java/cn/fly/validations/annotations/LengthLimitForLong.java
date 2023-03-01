package cn.fly.validations.annotations;

import cn.fly.validations.validation.LengthValidationForLong;

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
 * @description 数字校验类 @valid 下
 */

@Documented
@Constraint(validatedBy = {LengthValidationForLong.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface LengthLimitForLong {

    // 长度限制
    int[] limit();

    String message() default "当前字段超长!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
