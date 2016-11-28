package bg.tilchev.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;

    private int maxLength;

    private boolean containsDigit;

    private boolean containsLowercase;

    private boolean containsUppercase;

    private boolean containsSpecialSymbols;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.containsDigit = password.containsDigit();
        this.containsLowercase = password.containsLowercase();
        this.containsUppercase = password.containsUppercase(); // false
        this.containsSpecialSymbols = password.containsSpecialSymbols(); // false
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        int len = password.length();

        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialSymbol = false;
        for (int i = 0; i < len; i++) {
            char c = password.charAt(i);
            if(Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if(Character.isLowerCase(c)) {
                hasLowercase = true;
            }
            if(Character.isDigit(c)) {
                hasDigit = true;
            }
            if(c == '!' || c == '@' || c == '#' ||
                c == '$' || c == '%' || c == '^' ||
                c == '&' || c == '*' || c == '(' ||
                c == ')' || c == '_' || c == '+' ||
                c == '<' || c == '>' || c == '?') {
                hasSpecialSymbol = true;
            }
        }

        boolean result = len <= this.maxLength && len >= this.minLength;

        if(this.containsDigit) {
            result = result && hasDigit;
        }
        if(this.containsLowercase) {
            result = result && hasLowercase;
        }
        if(this.containsUppercase) {
            result = result && hasUppercase;
        }
        if(this.containsSpecialSymbols) {
            result = result && hasSpecialSymbol;
        }

        return result;
    }
}
