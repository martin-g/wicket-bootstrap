package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.password.strength;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.Key;

/**
 * A configuration for the activated password strength check rules
 */
public class PasswordStrengthActivated extends AbstractConfig {

    private static final IKey<Boolean> wordNotEmail = new Key<Boolean>("wordNotEmail", true);
    private static final IKey<Boolean> wordLength = new Key<Boolean>("wordLength", true);
    private static final IKey<Boolean> wordSimilarToUsername = new Key<Boolean>("wordSimilarToUsername", true);
    private static final IKey<Boolean> wordSequences = new Key<Boolean>("wordSequences", true);
    private static final IKey<Boolean> wordTwoCharacterClasses = new Key<Boolean>("wordTwoCharacterClasses", false);
    private static final IKey<Boolean> wordRepetitions = new Key<Boolean>("wordRepetitions", false);
    private static final IKey<Boolean> wordLowercase = new Key<Boolean>("wordLowercase", true);
    private static final IKey<Boolean> wordUppercase = new Key<Boolean>("wordUppercase", true);
    private static final IKey<Boolean> wordOneNumber = new Key<Boolean>("wordOneNumber", true);
    private static final IKey<Boolean> wordThreeNumbers = new Key<Boolean>("wordThreeNumbers", true);
    private static final IKey<Boolean> wordOneSpecialChar = new Key<Boolean>("wordOneSpecialChar", true);
    private static final IKey<Boolean> wordTwoSpecialChar = new Key<Boolean>("wordTwoSpecialChar", true);
    private static final IKey<Boolean> wordUpperLowerCombo = new Key<Boolean>("wordUpperLowerCombo", true);
    private static final IKey<Boolean> wordLetterNumberCombo = new Key<Boolean>("wordLetterNumberCombo", true);
    private static final IKey<Boolean> wordLetterNumberCharCombo = new Key<Boolean>("wordLetterNumberCharCombo", true);

    public PasswordStrengthActivated wordNotEmailScore(boolean activated) {
        put(wordNotEmail, activated);
        return this;
    }

    public PasswordStrengthActivated wordLengthScore(boolean activated) {
        put(wordLength, activated);
        return this;
    }

    public PasswordStrengthActivated wordSimilarToUsernameScore(boolean activated) {
        put(wordSimilarToUsername, activated);
        return this;
    }

    public PasswordStrengthActivated wordSequences(boolean activated) {
        put(wordSequences, activated);
        return this;
    }

    public PasswordStrengthActivated wordTwoCharacterClassesScore(boolean activated) {
        put(wordTwoCharacterClasses, activated);
        return this;
    }

    public PasswordStrengthActivated wordRepetitionsScore(boolean activated) {
        put(wordRepetitions, activated);
        return this;
    }

    public PasswordStrengthActivated wordLowercaseScore(boolean activated) {
        put(wordLowercase, activated);
        return this;
    }

    public PasswordStrengthActivated wordUppercaseScore(boolean activated) {
        put(wordUppercase, activated);
        return this;
    }

    public PasswordStrengthActivated wordOneNumberScore(boolean activated) {
        put(wordOneNumber, activated);
        return this;
    }

    public PasswordStrengthActivated wordThreeNumbersScore(boolean activated) {
        put(wordThreeNumbers, activated);
        return this;
    }

    public PasswordStrengthActivated wordOneSpecialCharScore(boolean activated) {
        put(wordOneSpecialChar, activated);
        return this;
    }

    public PasswordStrengthActivated wordTwoSpecialCharScore(boolean activated) {
        put(wordTwoSpecialChar, activated);
        return this;
    }

    public PasswordStrengthActivated wordUpperLowerComboScore(boolean activated) {
        put(wordUpperLowerCombo, activated);
        return this;
    }

    public PasswordStrengthActivated wordLetterNumberComboScore(boolean activated) {
        put(wordLetterNumberCombo, activated);
        return this;
    }

    public PasswordStrengthActivated wordLetterNumberCharComboScore(boolean activated) {
        put(wordLetterNumberCharCombo, activated);
        return this;
    }
}
