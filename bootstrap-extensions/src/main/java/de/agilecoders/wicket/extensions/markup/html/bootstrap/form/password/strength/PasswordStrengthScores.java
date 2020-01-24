package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.password.strength;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.Key;

/**
 * A configuration for pwstrength's rules' scores
 */
public class PasswordStrengthScores extends AbstractConfig {
    private static final long serialVersionUID = 1L;
    private static final IKey<Integer> wordNotEmail = new Key<>("wordNotEmail", -100);
    private static final IKey<Integer> wordLength = new Key<>("wordLength", -50);
    private static final IKey<Integer> wordSimilarToUsername = new Key<>("wordSimilarToUsername", -50);
    private static final IKey<Integer> wordSequences = new Key<>("wordSequences", -50);
    private static final IKey<Integer> wordTwoCharacterClasses = new Key<>("wordTwoCharacterClasses", 2);
    private static final IKey<Integer> wordRepetitions = new Key<>("wordRepetitions", -25);
    private static final IKey<Integer> wordLowercase = new Key<>("wordLowercase", 1);
    private static final IKey<Integer> wordUppercase = new Key<>("wordUppercase", 3);
    private static final IKey<Integer> wordOneNumber = new Key<>("wordOneNumber", 3);
    private static final IKey<Integer> wordThreeNumbers = new Key<>("wordThreeNumbers", 5);
    private static final IKey<Integer> wordOneSpecialChar = new Key<>("wordOneSpecialChar", 3);
    private static final IKey<Integer> wordTwoSpecialChar = new Key<>("wordTwoSpecialChar", 5);
    private static final IKey<Integer> wordUpperLowerCombo = new Key<>("wordUpperLowerCombo", 2);
    private static final IKey<Integer> wordLetterNumberCombo = new Key<>("wordLetterNumberCombo", 2);
    private static final IKey<Integer> wordLetterNumberCharCombo = new Key<>("wordLetterNumberCharCombo", 2);

    public PasswordStrengthScores wordNotEmailScore(int score) {
        put(wordNotEmail, score);
        return this;
    }

    public PasswordStrengthScores wordLengthScore(int score) {
        put(wordLength, score);
        return this;
    }

    public PasswordStrengthScores wordSimilarToUsernameScore(int score) {
        put(wordSimilarToUsername, score);
        return this;
    }

    public PasswordStrengthScores wordSequences(int score) {
        put(wordSequences, score);
        return this;
    }

    public PasswordStrengthScores wordTwoCharacterClassesScore(int score) {
        put(wordTwoCharacterClasses, score);
        return this;
    }

    public PasswordStrengthScores wordRepetitionsScore(int score) {
        put(wordRepetitions, score);
        return this;
    }

    public PasswordStrengthScores wordLowercaseScore(int score) {
        put(wordLowercase, score);
        return this;
    }

    public PasswordStrengthScores wordUppercaseScore(int score) {
        put(wordUppercase, score);
        return this;
    }

    public PasswordStrengthScores wordOneNumberScore(int score) {
        put(wordOneNumber, score);
        return this;
    }

    public PasswordStrengthScores wordThreeNumbersScore(int score) {
        put(wordThreeNumbers, score);
        return this;
    }

    public PasswordStrengthScores wordOneSpecialCharScore(int score) {
        put(wordOneSpecialChar, score);
        return this;
    }

    public PasswordStrengthScores wordTwoSpecialCharScore(int score) {
        put(wordTwoSpecialChar, score);
        return this;
    }

    public PasswordStrengthScores wordUpperLowerComboScore(int score) {
        put(wordUpperLowerCombo, score);
        return this;
    }

    public PasswordStrengthScores wordLetterNumberComboScore(int score) {
        put(wordLetterNumberCombo, score);
        return this;
    }

    public PasswordStrengthScores wordLetterNumberCharComboScore(int score) {
        put(wordLetterNumberCharCombo, score);
        return this;
    }
}
