package bg.tilchev.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minLength() default 3;

    int maxLength() default Integer.MAX_VALUE;

    boolean containsDigit() default false;

    boolean containsLowercase() default false;

    boolean containsUppercase() default false;

    boolean containsSpecialSymbols() default false;
}
