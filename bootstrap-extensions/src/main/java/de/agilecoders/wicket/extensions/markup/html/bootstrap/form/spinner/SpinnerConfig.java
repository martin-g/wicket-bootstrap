package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

public class SpinnerConfig extends AbstractConfig{

	private static final long serialVersionUID = 2239107002237150197L;

	private static final IKey<Integer> maximum = newKey("maximum", null);

	private static final IKey<Integer> minimum = newKey("minimum", null);

	private static final IKey<Integer> step = newKey("step", null);

	public SpinnerConfig withMaximum(Integer value){
		put(maximum, value);
		return this;
	}

	public SpinnerConfig withMinimum(Integer value){
		put(minimum, value);
		return this;
	}

	public SpinnerConfig withStep(Integer value){
		put(step, value);
		return this;
	}

}
