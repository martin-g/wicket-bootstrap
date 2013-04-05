package de.agilecoders.wicket.core.util;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * A simple String model that indicates that it should be serialized as
 * plain String for JSON configurations.
 * Uses a special Json serializer.
 */
public class ConfigModel implements IModel<String>
{
    private final IModel<String> wrapped;

    public ConfigModel(IModel<String> wrapped) {
        this.wrapped = Args.notNull(wrapped, "wrapped");
    }

    @Override
    public String getObject()
    {
        return wrapped.getObject();
    }

    @Override
    public void setObject(String object)
    {
        wrapped.setObject(object);
    }

    @Override
    public void detach()
    {
        wrapped.detach();
    }

}