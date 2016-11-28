package bg.tilchev.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UsernameValidator implements ConstraintValidator<Username, String> {

    private int minLength;

    private int maxLength;

    private boolean canStartWithNumber;

    private boolean containsOnlyLettersAndNumbers;

    @Override
    public void initialize(Username username) {
        this.minLength = username.minLength();
        this.maxLength = username.maxLength();
        this.canStartWithNumber = username.canStartWithNumber();
        this.containsOnlyLettersAndNumbers = username.containsOnlyLettersAndNumbers();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        int len = password.length();

        boolean isOnlyLettersAndNumbers = true;
        boolean doesntStartWithNumber = !Character.isDigit(password.charAt(0));
        for (int i = 1; i < len; i++) {
            char c = password.charAt(i);
            if(!Character.isLetter(c)) {
                if(!Character.isDigit(c)) {
                    isOnlyLettersAndNumbers = false;
                    break;
                }
            }
        }

        boolean result = len <= this.maxLength && len >= this.minLength;

        if(!this.canStartWithNumber) {
            result = result && doesntStartWithNumber;
        }
        if(this.containsOnlyLettersAndNumbers) {
            result = result && isOnlyLettersAndNumbers;
        }

        return result;
    }
}
