package de.agilecoders.wicket.extensions.slider;

import de.agilecoders.wicket.extensions.slider.res.BootstrapSliderCssResourceReference;
import de.agilecoders.wicket.extensions.slider.res.BootstrapSliderResourceReference;
import de.agilecoders.wicket.extensions.slider.util.*;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * BootstrapSlider: a Wicket wrapper for: https://github.com/seiyria/bootstrap-slider
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class BootstrapSlider<T extends ISliderValue, N extends Number> extends TextField<T> {
    
    private static Logger logger = LoggerFactory.getLogger(BootstrapSlider.class);
    
    public enum TooltipType {
        show, hide, always    
    }

    public enum HandleType {
        round, square, triangle, custom
    }

    public enum Orientation {
        horizontal, vertical
    }
    
    private static class BootstrapDoubleSliderConverter implements IConverter<ISliderValue>
    {
        @Override
        public ISliderValue convertToObject(String value, Locale locale) throws ConversionException {
            if(value.contains("[")) {
                return new DoubleRangeValue().fromString(value);
            } else {
                return new DoubleValue().fromString(value);
            }
        }

        @Override
        public String convertToString(ISliderValue value, Locale locale) {
            return value.toString();
        }
    }

    private static class BootstrapLongSliderConverter implements IConverter<ISliderValue>
    {
        @Override
        public ISliderValue convertToObject(String value, Locale locale) throws ConversionException {
            if(value.contains("[")) {
                return new LongRangeValue().fromString(value);
            } else {
                return new LongValue().fromString(value);
            }
        }

        @Override
        public String convertToString(ISliderValue value, Locale locale) {
            return value.toString();
        }
    }

    private static class BootstrapIntegerSliderConverter implements IConverter<ISliderValue>
    {
        @Override
        public ISliderValue convertToObject(String value, Locale locale) throws ConversionException {
            if(value.contains("[")) {
                return new IntegerRangeValue().fromString(value);
            } else {
                return new IntegerValue().fromString(value);
            }
        }

        @Override
        public String convertToString(ISliderValue value, Locale locale) {
            return value.toString();
        }
    }
    
    private  IConverter<ISliderValue> converter;
    
    private N min;
    private N max;
    private N step;
    private TooltipType tooltip;
    private HandleType handle;
    // function to create custom tooltip.
    private String formatter;
    private Orientation orientation;
    
    public BootstrapSlider(final String id, final IModel<T> model, final Class<T> typeClass)
    {
        super(id, model, typeClass);
        setOutputMarkupId(true);
        try {
            if(Double.class.isAssignableFrom(typeClass.newInstance().getNumberType())) {
                converter = new BootstrapDoubleSliderConverter();
            } else if(Integer.class.isAssignableFrom(typeClass.newInstance().getNumberType())) {
                converter = new BootstrapIntegerSliderConverter();
            } else {
                converter = new BootstrapLongSliderConverter();
            }
        } catch (InstantiationException e) {
            throw new WicketRuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new WicketRuntimeException(e);
        }
    }
    
    protected T newInstance() {
        try {
            return getType().newInstance();
        } catch (InstantiationException e) {
            logger.error("newInstance", e);
        } catch (IllegalAccessException e) {
            logger.error("newInstance", e);
        }
        return null;
    }

    @Override
    public <C> IConverter<C> getConverter(final Class<C> type)
    {
        if (ISliderValue.class.isAssignableFrom(type))
        {
            return (IConverter<C>)converter;
        }
        else
        {
            return super.getConverter(type);
        }
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        // Must be attached to an input tag
        checkComponentTag(tag, "input");
        tag.put("data-slider-value", converter.convertToString(getModelObject(), getLocale()));
        tag.put("data-slider-min", min != null ? min.toString() : "0");
        tag.put("data-slider-max",max != null? max.toString(): "10");
        tag.put("data-slider-step",step != null? step.toString(): "1");
        tag.put("type", "text");
        if(tooltip != null) {
            tag.put("data-slider-tooltip", tooltip.name());
        }
        if(handle != null) {
            tag.put("data-slider-handle", handle.name());
        }
        if(!isEnabled()) {
            tag.put("data-slider-enabled", false);
        }
        if(orientation != null) {
            tag.put("data-slider-orientation", orientation.name());
        }
        tag.put("id", getMarkupId());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(BootstrapSliderResourceReference.getInstance()));
        response.render(CssHeaderItem.forReference(BootstrapSliderCssResourceReference.getInstance()));
        StringBuilder builder = new StringBuilder();
        builder.append("$('#").append(getMarkupId()).append("').slider({");
        if(formatter != null) {
            builder.append("'formatter':").append(formatter);
        }
        configExtraParams(builder);
        builder.append("})");
        configEvents(builder);
        builder.append(";");
        response.render(OnDomReadyHeaderItem.forScript(builder));
    }
    
    protected void configExtraParams(final StringBuilder builder) {
        
        
    }

    protected void configEvents(StringBuilder builder) {


    }

    public N getMin() {
        return min;
    }

    public BootstrapSlider setMin(N min) {
        this.min = min;
        return this;
    }

    public N getMax() {
        return max;
    }

    public BootstrapSlider setMax(N max) {
        this.max = max;
        return this;
    }

    public N getStep() {
        return step;
    }

    public BootstrapSlider setStep(N step) {
        this.step = step;
        return this;
    }

    public TooltipType getTooltip() {
        return tooltip;
    }

    public BootstrapSlider setTooltip(TooltipType tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public HandleType getHandle() {
        return handle;
    }

    public BootstrapSlider setHandle(HandleType handle) {
        this.handle = handle;
        return this;
    }

    public String getFormatter() {
        return formatter;
    }

    public BootstrapSlider setFormatter(String formatter) {
        this.formatter = formatter;
        return this;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public BootstrapSlider setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }
}
