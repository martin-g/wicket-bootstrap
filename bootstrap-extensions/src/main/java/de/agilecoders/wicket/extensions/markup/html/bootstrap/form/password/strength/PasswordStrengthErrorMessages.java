package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.password.strength;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.Key;

/**
 * A configuration for pwstrength's UI error messages
 */
public class PasswordStrengthErrorMessages extends AbstractConfig {
    private static final long serialVersionUID = 1L;
    private static final IKey<String> wordNotEmail = new Key<>("wordNotEmail", "Do not use your email as your password");
    private static final IKey<String> wordLength = new Key<>("wordLength", "Your password is too short");
    private static final IKey<String> wordSimilarToUsername = new Key<>("wordSimilarToUsername", "Your password cannot contain your username");
    private static final IKey<String> wordSequences = new Key<>("wordSequences", "Your password contains sequences");
    private static final IKey<String> wordTwoCharacterClasses = new Key<>("wordTwoCharacterClasses", "Use different character classes");
    private static final IKey<String> wordRepetitions = new Key<>("wordRepetitions", "Too many repetitions");
    private static final IKey<String> wordLowercase = new Key<>("wordLowercase", null);
    private static final IKey<String> wordUppercase = new Key<>("wordUppercase", null);
    private static final IKey<String> wordOneNumber = new Key<>("wordOneNumber", null);
    private static final IKey<String> wordThreeNumbers = new Key<>("wordThreeNumbers", null);
    private static final IKey<String> wordOneSpecialChar = new Key<>("wordOneSpecialChar", null);
    private static final IKey<String> wordTwoSpecialChar = new Key<>("wordTwoSpecialChar", null);
    private static final IKey<String> wordUpperLowerCombo = new Key<>("wordUpperLowerCombo", null);
    private static final IKey<String> wordLetterNumberCombo = new Key<>("wordLetterNumberCombo", null);
    private static final IKey<String> wordLetterNumberCharCombo = new Key<>("wordLetterNumberCharCombo", null);

    public PasswordStrengthErrorMessages wordNotEmailMessage(String message) {
        put(wordNotEmail, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordLengthMessage(String message) {
        put(wordLength, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordSimilarToUsernameMessage(String message) {
        put(wordSimilarToUsername, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordSequences(String message) {
        put(wordSequences, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordTwoCharacterClassesMessage(String message) {
        put(wordTwoCharacterClasses, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordRepetitionsMessage(String message) {
        put(wordRepetitions, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordLowercaseMessage(String message) {
        put(wordLowercase, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordUppercaseMessage(String message) {
        put(wordUppercase, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordOneNumberMessage(String message) {
        put(wordOneNumber, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordThreeNumbersMessage(String message) {
        put(wordThreeNumbers, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordOneSpecialCharMessage(String message) {
        put(wordOneSpecialChar, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordTwoSpecialCharMessage(String message) {
        put(wordTwoSpecialChar, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordUpperLowerComboMessage(String message) {
        put(wordUpperLowerCombo, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordLetterNumberComboMessage(String message) {
        put(wordLetterNumberCombo, message);
        return this;
    }

    public PasswordStrengthErrorMessages wordLetterNumberCharComboMessage(String message) {
        put(wordLetterNumberCharCombo, message);
        return this;
    }
}
