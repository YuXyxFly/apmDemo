package cn.fly.validations.validation;

import cn.fly.validations.annotations.LengthLimitForLong;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author fly
 * @date 2023/2/27
 * @description
 */

public class LengthValidationForLong implements ConstraintValidator<LengthLimitForLong, Long> {

    private int[] limit;

    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(LengthLimitForLong constraintAnnotation) {
        limit = constraintAnnotation.limit();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

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
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return limit[0] <= String.valueOf(value).length() && String.valueOf(value).length() <= limit[1];
    }
}
