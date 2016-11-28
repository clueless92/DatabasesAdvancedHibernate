package bg.tilchev.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator implements ConstraintValidator<Email, String> {

    private String regex;

    @Override
    public void initialize(Email email) {
        this.regex = email.regex();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile(this.regex);
        Matcher matcher = pattern.matcher(password);
        boolean startsCorrect = Character.isLetterOrDigit(password.charAt(0));
        boolean result = matcher.matches() && startsCorrect;
        return result;
    }
}
