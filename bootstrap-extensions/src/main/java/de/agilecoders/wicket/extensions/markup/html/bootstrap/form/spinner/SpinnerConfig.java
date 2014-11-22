package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * @author daniel.jipa
 * @reference http://www.virtuosoft.eu/code/bootstrap-touchspin/
 */
public class SpinnerConfig extends AbstractConfig {

	private static final long serialVersionUID = 2239107002237150197L;

	private static final IKey<Number> initval = newKey("initval", null);

	private static final IKey<Number> max = newKey("max", null);

	private static final IKey<Number> min = newKey("min", null);

	private static final IKey<Number> step = newKey("step", null);

	private static final IKey<Integer> boostat = newKey("boostat", null);

	private static final IKey<Integer> maxboostedstep = newKey("maxboostedstep", null);

	private static final IKey<Integer> decimals = newKey("decimals", null);

	private static final IKey<String> buttondown_class = newKey("buttondown_class", null);

	private static final IKey<String> buttonup_class = newKey("buttonup_class", null);

	private static final IKey<Boolean> verticalbuttons = newKey("verticalbuttons", null);

	private static final IKey<String> verticalupclass = newKey("verticalupclass", null);

	private static final IKey<String> verticaldownclass = newKey("verticaldownclass", null);

	private static final IKey<String> prefix = newKey("prefix", null);

	private static final IKey<String> prefix_extraclass = newKey("prefix_extraclass", null);

	private static final IKey<String> postfix = newKey("postfix", null);

	private static final IKey<String> postfix_extraclass = newKey("postfix_extraclass", null);

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

	public SpinnerConfig withBootstap(Integer value) {
		put(boostat, value);
		return this;
	}

	public SpinnerConfig withMaxboostedstep(Integer value) {
		put(maxboostedstep, value);
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
