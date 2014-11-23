package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;


public class SpinnerConfig extends AbstractConfig {

	private static final long serialVersionUID = 2239107002237150197L;

	private static final IKey<Number> initval = newKey("initval", null);

	private static final IKey<Number> max = newKey("max", getDefaultValueAsNumber(100));

	private static final IKey<Number> min = newKey("min", getDefaultValueAsNumber(0));

	private static final IKey<Number> step = newKey("step", getDefaultValueAsNumber(1));

	private static final IKey<String> forcestepdivisibility = newKey("forcestepdivisibility", "round");

	private static final IKey<Integer> boostat = newKey("boostat", 10);

	private static final IKey<Boolean> booster = newKey("booster", Boolean.TRUE);

	private static final IKey<Integer> stepinterval = newKey("stepinterval", 100);

	private static final IKey<Integer> stepintervaldelay = newKey("stepintervaldelay", 100);

	private static final IKey<Boolean> maxboostedstep = newKey("maxboostedstep", Boolean.FALSE);

	private static final IKey<Boolean> mousewheel = newKey("mousewheel", Boolean.TRUE);

	private static final IKey<Integer> decimals = newKey("decimals", 0);

	private static final IKey<String> buttondown_class = newKey("buttondown_class", null);

	private static final IKey<String> buttonup_class = newKey("buttonup_class", null);

	private static final IKey<Boolean> verticalbuttons = newKey("verticalbuttons", Boolean.FALSE);

	private static final IKey<String> verticalupclass = newKey("verticalupclass", "glyphicon glyphicon-chevron-up");

	private static final IKey<String> verticaldownclass = newKey("verticaldownclass", "glyphicon glyphicon-chevron-down");

	private static final IKey<String> prefix = newKey("prefix", "");

	private static final IKey<String> prefix_extraclass = newKey("prefix_extraclass", null);

	private static final IKey<String> postfix = newKey("postfix", null);

	private static final IKey<String> postfix_extraclass = newKey("postfix_extraclass", null);


	private static Number getDefaultValueAsNumber(Number value){
		return value;
	}

	public SpinnerConfig withInitVal(Number value) {
		put(initval, value);
		return this;
	}

	public SpinnerConfig withMax(Number value) {
		put(max, value);
		return this;
	}

	public SpinnerConfig withMin(Number value) {
		put(min, value);
		return this;
	}

	public SpinnerConfig withStep(Number value) {
		put(step, value);
		return this;
	}

	public SpinnerConfig withForcestepDivisibility(String value) {
		put(forcestepdivisibility, value);
		return this;
	}

	public SpinnerConfig withBootster(Boolean value) {
		put(booster, value);
		return this;
	}

	public SpinnerConfig withBootstap(Integer value) {
		put(boostat, value);
		return this;
	}

	public SpinnerConfig withStepInterval(Integer value) {
		put(stepinterval, value);
		return this;
	}

	public SpinnerConfig withStepIntervalDelay(Integer value) {
		put(stepintervaldelay, value);
		return this;
	}

	public SpinnerConfig withMaxboostedstep(Boolean value) {
		put(maxboostedstep, value);
		return this;
	}

	public SpinnerConfig withMouseWheel(Boolean value) {
		put(mousewheel, value);
		return this;
	}

	public SpinnerConfig withDecimals(Integer value) {
		put(decimals, value);
		return this;
	}

	public SpinnerConfig withButtonDownClass(String value) {
		put(buttondown_class, value);
		return this;
	}

	public SpinnerConfig withButtonUpClass(String value) {
		put(buttonup_class, value);
		return this;
	}


	public SpinnerConfig withVerticalbuttons(Boolean value) {
		put(verticalbuttons, value);
		return this;
	}

	public SpinnerConfig withVerticalUpClass(String value) {
		put(verticalupclass, value);
		return this;
	}

	public SpinnerConfig withVerticalDownClass(String value) {
		put(verticaldownclass, value);
		return this;
	}

	public SpinnerConfig withPrefix(String value) {
		put(prefix, value);
		return this;
	}

	public SpinnerConfig withPrefixExtraClass(String value) {
		put(prefix_extraclass, value);
		return this;
	}

	public SpinnerConfig withPostfix(String value) {
		put(postfix, value);
		return this;
	}

	public SpinnerConfig withPostfixExtraClass(String value) {
		put(postfix_extraclass, value);
		return this;
	}
}
