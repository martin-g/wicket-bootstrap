package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.password.strength;

import org.apache.wicket.Component;
import org.apache.wicket.util.lang.Args;



import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.Key;

/**
 * The configuration for <a href="https://github.com/ablanco/jquery.pwstrength.bootstrap/blob/master/OPTIONS.md">pwstrength</em>.
 */
public class PasswordStrengthConfig extends AbstractConfig {

    private static final IKey<CommonConfig> COMMON_CONFIG = new Key<PasswordStrengthConfig.CommonConfig>("common", new CommonConfig());
    private static final IKey<UiConfig> UI_CONFIG = new Key<PasswordStrengthConfig.UiConfig>("ui", new UiConfig());
    private static final IKey<RulesConfig> RULES_CONFIG = new Key<PasswordStrengthConfig.RulesConfig>("rules", new RulesConfig());

    private final CommonConfig commonConfig;
    private final RulesConfig rulesConfig;
    private final UiConfig uiConfig;

    public PasswordStrengthConfig() {
        this.commonConfig = new CommonConfig();
        put(COMMON_CONFIG, commonConfig);

        this.rulesConfig = new RulesConfig();
        put(RULES_CONFIG, rulesConfig);

        this.uiConfig = new UiConfig();
        put(UI_CONFIG, uiConfig);
        uiConfig.put(UiConfig.UseBootstrap4, true);
    }

    public PasswordStrengthConfig withMinChar(int minChar) {
        commonConfig.put(CommonConfig.MinChar, minChar);
        return this;
    }

    public PasswordStrengthConfig withUsernameField(Component usernameField) {
        commonConfig.put(CommonConfig.UsernameField, "#" + usernameField.getMarkupId());
        return this;
    }

    public PasswordStrengthConfig withZxcvbn(boolean zxcvbn) {
        commonConfig.put(CommonConfig.Zxcvbn, zxcvbn);
        return this;
    }

    public PasswordStrengthConfig withDebug(boolean debug) {
        commonConfig.put(CommonConfig.Debug, debug);
        return this;
    }

    public PasswordStrengthConfig withScores(PasswordStrengthScores scores) {
        rulesConfig.put(RulesConfig.Scores, scores);
        return this;
    }

    public PasswordStrengthConfig withActivated(PasswordStrengthActivated activated) {
        rulesConfig.put(RulesConfig.Activated, activated);
        return this;
    }

    public PasswordStrengthConfig withRaisePower(double raisePower) {
        rulesConfig.put(RulesConfig.RaisePower, raisePower);
        return this;
    }

    public PasswordStrengthConfig withShowProgressBar(boolean showProgressBar) {
        uiConfig.put(UiConfig.ShowProgressBar, showProgressBar);
        return this;
    }

    public PasswordStrengthConfig withShowPopover(boolean showPopover) {
        uiConfig.put(UiConfig.ShowPopover, showPopover);
        return this;
    }

    public PasswordStrengthConfig withShowStatus(boolean showStatus) {
        uiConfig.put(UiConfig.ShowStatus, showStatus);
        return this;
    }

    public PasswordStrengthConfig withErrorMessages(PasswordStrengthErrorMessages errorMessages) {
        uiConfig.put(UiConfig.ErrorMessages, errorMessages);
        return this;
    }

    public PasswordStrengthConfig withVerdicts(String[] verdicts) {
        Args.notNull(verdicts, "verdicts");
        Args.isTrue(verdicts.length == 5, "The 'verdicts' must be exactly five!");
        uiConfig.put(UiConfig.Verdicts, verdicts);
        return this;
    }

    public PasswordStrengthConfig withShowVerdicts(boolean showVerdicts) {
        uiConfig.put(UiConfig.ShowVerdicts, showVerdicts);
        return this;
    }

    public PasswordStrengthConfig withShowVerdictsInsideProgressBar(boolean showVerdictsInsideProgressBar) {
        uiConfig.put(UiConfig.ShowVerdictsInsideProgressBar, showVerdictsInsideProgressBar);
        return this;
    }

    public PasswordStrengthConfig withUseVerdictCssClass(boolean useVerdictCssClass) {
        uiConfig.put(UiConfig.UseVerdictCssClass, useVerdictCssClass);
        return this;
    }

    public PasswordStrengthConfig withShowErrors(boolean showErrors) {
        uiConfig.put(UiConfig.ShowErrors, showErrors);
        return this;
    }

    public PasswordStrengthConfig withScores(int[] scores) {
        Args.notNull(scores, "scores");
        Args.isTrue(scores.length == 4, "The 'scores' must be exactly four!");
        uiConfig.put(UiConfig.Scores, scores);
        return this;
    }

    private static class CommonConfig extends AbstractConfig {
        private static final IKey<Integer> MinChar = new Key<Integer>("minChar", 6);
        private static final IKey<String> UsernameField = new Key<String>("usernameField", "#username");
        private static final IKey<Boolean> Zxcvbn = new Key<Boolean>("zxcvbn", false);
        private static final IKey<Boolean> Debug = new Key<Boolean>("debug", false);
    }

    private static class UiConfig extends AbstractConfig {
        private static final IKey<Boolean> ShowProgressBar = new Key<Boolean>("showProgressBar", true);
        private static final IKey<Boolean> ShowPopover = new Key<Boolean>("showPopover", false);
        private static final IKey<Boolean> ShowStatus = new Key<Boolean>("showStatus", false);
        private static final IKey<Boolean> UseVerdictCssClass = new Key<Boolean>("useVerdictCssClass", false);
        private static final IKey<Boolean> ShowVerdicts = new Key<Boolean>("showVerdicts", true);
        private static final IKey<Boolean> ShowErrors = new Key<Boolean>("showErrors", false);
        private static final IKey<Boolean> ShowVerdictsInsideProgressBar = new Key<Boolean>("showVerdictsInsideProgressBar", false);
        private static final IKey<String[]> Verdicts = new Key<String[]>("verdicts", new String[]{"Weak", "Normal", "Medium", "Strong", "Very Strong"});
        private static final IKey<int[]> Scores = new Key<int[]>("scores", new int[]{17, 26, 40, 50});
        private static final IKey<PasswordStrengthErrorMessages> ErrorMessages =
            new Key<PasswordStrengthErrorMessages>("errorMessages", new PasswordStrengthErrorMessages());
        private static final IKey<Boolean> UseBootstrap4 = new Key<>("bootstrap4", false);
    }

    private static class RulesConfig extends AbstractConfig {
        private static final IKey<PasswordStrengthScores> Scores = new Key<PasswordStrengthScores>("scores", new PasswordStrengthScores());
        private static final IKey<PasswordStrengthActivated> Activated = new Key<PasswordStrengthActivated>("activated", new PasswordStrengthActivated());
        private static final IKey<Double> RaisePower = new Key<Double>("raisePower", 1.4d);
    }
}
