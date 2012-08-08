package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * A CssClassNameAppender appends the given value, rather than replace it. This is especially useful
 * for adding CSS classes to markup elements.
 *
 * <pre>
 *     &lt;span class=&quot;className&quot; wicket:id=&quot;foo&quot;&gt;
 * </pre>
 *
 * can be modified with these CssClassNameAppender:
 *
 * <pre>
 * link.add(new CssClassNameAppender(&quot;className2&quot;));
 * link.add(new CssClassNameAppender(Arrays.asList(&quot;className2&quot;,&quot;className3&quot;)));
 * </pre>
 *
 * this will result in the following markup:
 *
 * <pre>
 *     &lt;span class=&quot;className className2 className3&quot; wicket:id=&quot;foo&quot; &gt;
 * </pre>
 *
 * @author miha
 * @version 1.0
 */
public class CssClassNameAppender extends AttributeAppender {

    /**
     * Separator between the current value and the concatenated value.
     */
    private static final String SEPARATOR = " ";

    /**
     * The name of the html class attribute name.
     */
    private static final String ATTRIBUTE_NAME = "class";

    /**
     * @return separator between the current value and the concatenated value.
     */
    public static String separator() {
        return SEPARATOR;
    }
    
    /**
     * Creates an AttributeModifier that appends the appendModel's value to the current value of the
     * class attribute, and will add the attribute when it is not there already.
     *
     * @param appendModel the model supplying a single value to append
     */
    public CssClassNameAppender(IModel<String> appendModel) {
        super(ATTRIBUTE_NAME, appendModel, SEPARATOR);
    }

    /**
     * Constructor.
     * {@link CssClassNameAppender#CssClassNameAppender(org.apache.wicket.model.IModel)}
     *
     * @param appendValue one or more values to append
     */
    public CssClassNameAppender(String... appendValue) {
        this(Lists.newArrayList(appendValue));
    }

    /**
     * Constructor.
     * {@link CssClassNameAppender#CssClassNameAppender(org.apache.wicket.model.IModel)}
     *
     * @param appendValueList a list of values to append
     */
    public CssClassNameAppender(List<String> appendValueList) {
        this(Model.of(Joiner.on(SEPARATOR).skipNulls().join(appendValueList)));
    }

    /**
     * Constructor.
     * {@link CssClassNameAppender#CssClassNameAppender(org.apache.wicket.model.IModel)}
     *
     * @param cssClassNameProvider a css class name provider
     */
    public CssClassNameAppender(CssClassNameProvider cssClassNameProvider) {
        this(Model.of(cssClassNameProvider.cssClassName()));
    }

    private String value() {
        return nullToEmpty((String) getReplaceModel().getObject());
    }

    @Override
    public void beforeRender(Component component) {
        super.beforeRender(component);

        removeDuplicates(component);
    }

    /**
     * removes all duplicated css class name appender
     *
     * @param component the bound component
     */
    private void removeDuplicates(Component component) {
        List<CssClassNameAppender> behaviors = component.getBehaviors(CssClassNameAppender.class);
        for (CssClassNameAppender behavior : behaviors) {
            if (behavior.value().equals(value())) {
                component.remove(behavior);
            }
        }
    }
}
