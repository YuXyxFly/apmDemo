package cn.fly.validations.validation;

import cn.fly.validations.annotations.LengthLimitForString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * @author fly
 * @date 2023/2/27
 * @description
 */

public class AfterNowValidation implements ConstraintValidator<LengthLimitForString, Date> {
    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        return value.after(new Date());
    }
}
