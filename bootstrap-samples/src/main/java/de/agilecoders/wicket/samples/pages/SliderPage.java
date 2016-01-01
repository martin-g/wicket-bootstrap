package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.extensions.slider.AjaxBootstrapSlider;
import de.agilecoders.wicket.extensions.slider.AjaxNumericBootstrapSlider;
import de.agilecoders.wicket.extensions.slider.BootstrapSlider;
import de.agilecoders.wicket.extensions.slider.NumericBootstrapSlider;
import de.agilecoders.wicket.extensions.slider.util.INumericValue;
import de.agilecoders.wicket.extensions.slider.util.LongRangeValue;
import de.agilecoders.wicket.extensions.slider.util.LongValue;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.AbstractReadOnlyModel;
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
    
    private Model<LongRangeValue> longRangeModel = Model.of( new LongRangeValue(10, 20));

    private Model<LongRangeValue> disabledRangeModel = Model.of( new LongRangeValue(10, 20));
    
    private Model<LongRangeValue> tooltipFormRangeModel = Model.of( new LongRangeValue(10, 20));

    private Model<Long> ajaxSliderModel  = Model.of(100L);

    private Model<LongRangeValue> verticalRangeModel = Model.of( new LongRangeValue(10, 20));
    
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
            new Code("default-java-code", Model.of("//JAVA\nadd(new NumericBootstrapSlider<Long>(\"slider\")).setMin(min).setMax(max).setStep(step);")).setShowLineNumbers(true));

        Form<Void> rangeForm = new Form<Void>("rangeForm");
        add(rangeForm);
        rangeForm.add(newRangeSlider("rangeSlider", longRangeModel, 0L, 100L, 5L).setHandle(BootstrapSlider.HandleType.triangle));
        add(new Code("range-html-code", Model.of("//HTML\n<form><input wicket:id=\"rangeSlider\"></form>")).setShowLineNumbers(true),
                new Code("range-java-code", Model.of("//JAVA\nadd(new BootstrapSlider<LongRangeValue, Long>(\"rangeSlider\")).setMin(min).setMax(max).setStep(step);")).setShowLineNumbers(true));

        Form<Void> disabledForm = new Form<Void>("disabledForm");
        add(disabledForm);
        disabledForm.add(newRangeSlider("disabledSlider", disabledRangeModel, 0L, 100L, 5L).setEnabled(false));
        add(new Code("disabled-html-code", Model.of("//HTML\n<form><input wicket:id=\"disabledSlider\"></form>")).setShowLineNumbers(true),
                new Code("disabled-java-code", Model.of("//JAVA\nadd(new BootstrapSlider<LongRangeValue, Long>(\"disabledSlider\")).setMin(min).setMax(max).setStep(step);")).setShowLineNumbers(true));

        Form<Void> tooltipForm = new Form<Void>("tooltipForm");
        add(tooltipForm);
        tooltipForm.add(newRangeSlider("tooltipSlider", tooltipFormRangeModel, 0L, 100L, 5L).setTooltip(BootstrapSlider.TooltipType.always).setFormatter("function(value){ return 'The values is:' + value; }"));

        final Label feedback = new Label("feedback", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return ajaxSliderModel.getObject().toString();
            }
        });
        feedback.setOutputMarkupId(true);
        add(feedback);
        Form<Void> ajaxForm = new Form<Void>("ajaxForm");
        add(ajaxForm);
        ajaxForm.add(newAjaxSlider("ajaxSlider", ajaxSliderModel, 0L, 1000L, 10L, AjaxBootstrapSlider.SliderEvent.slideStop, new AjaxBootstrapSlider.EventHandler<LongValue>() {
            @Override
            public void onAjaxEvent(AjaxRequestTarget target, LongValue newValue) {
                target.add(feedback);
            }
        }));

        Form<Void> verticalForm = new Form<Void>("verticalForm");
        add(verticalForm);
        verticalForm.add(newRangeSlider("verticalSlider", verticalRangeModel, 0L, 100L, 5L).setOrientation(BootstrapSlider.Orientation.vertical));
    }

    private static  <T extends Number, V extends INumericValue> Component newAjaxSlider(String markupId, Model<T> longSliderModel, T min, T max, T step, AjaxBootstrapSlider.SliderEvent event, AjaxBootstrapSlider.EventHandler<V> handler) {
        return new AjaxNumericBootstrapSlider<T>(markupId, longSliderModel).addHandler(event, ( AjaxBootstrapSlider.EventHandler<INumericValue<T>>)handler).setMin(min).setMax(max).setStep(step);
    }

    private Component newSlider(String markupId, Model<Long> longSliderModel, Long min, Long max, Long step) {
        return new NumericBootstrapSlider<Long>(markupId, longSliderModel).setMin(min).setMax(max).setStep(step);
    }

    private BootstrapSlider newRangeSlider(String markupId, Model<LongRangeValue> longSliderModel, Long min, Long max, Long step) {
        return new BootstrapSlider<LongRangeValue, Long>(markupId, longSliderModel, LongRangeValue.class).setMin(min).setMax(max).setStep(step);
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
