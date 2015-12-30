package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.extensions.slider.NumericBootstrapSlider;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/slider")
public class SliderPage extends BasePage {

    private Model<Long> longSliderModel  = Model.of(100L);
    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public SliderPage(PageParameters parameters) {
        super(parameters);

        Form<Void> sliderForm = new Form<Void>("sliderForm");
        add(sliderForm);
        sliderForm.add(newSlider("slider", longSliderModel, 0L, 100000L, 10L));
        add(new Code("default-html-code", Model.of("//HTML\n<form><input wicket:id=\"slider\"></form>")).setShowLineNumbers(true),
            new Code("default-java-code", Model.of("//JAVA\nadd(new DateTextField(\"default\"));")).setShowLineNumbers(true));
    }

    private Component newSlider(String markupId, Model<Long> longSliderModel, Long min, Long max, Long step) {
        return new NumericBootstrapSlider<Long>(markupId, longSliderModel).setMin(min).setMax(max).setStep(step);
    }


    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
