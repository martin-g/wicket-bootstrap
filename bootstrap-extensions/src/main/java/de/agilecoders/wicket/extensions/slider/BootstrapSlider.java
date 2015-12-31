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

import java.util.Locale;

/**
 * BootstrapSlider
 */
public class BootstrapSlider<T extends ISliderValue, N extends Number> extends TextField<T> {
    
    public static enum TooltipType {
        show, hide, always    
    }

    public static enum HandleType {
        round, square, triangle, custom;
    }
    
    public static class BootstrapDoubleSliderConverter implements IConverter<ISliderValue>
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

    public static class BootstrapLongSliderConverter implements IConverter<ISliderValue>
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

    public static class BootstrapIntegerSliderConverter implements IConverter<ISliderValue>
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
    
    public BootstrapSlider(String id, IModel<T> model, Class<T> typeClass)
    {
        super(id, model, typeClass);
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
    protected void onInitialize() {
        super.onInitialize();
        setOutputMarkupId(true);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
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
        tag.put("id", getMarkupId());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(BootstrapSliderResourceReference.getInstance()));
        response.render(CssHeaderItem.forReference(BootstrapSliderCssResourceReference.getInstance()));
        StringBuilder builder = new StringBuilder();
        builder.append("$('#").append(getMarkupId()).append("').slider();");
        response.render(OnDomReadyHeaderItem.forScript(builder));
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
}
