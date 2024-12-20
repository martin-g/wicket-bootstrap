package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.util.CssClassNames;

/**
 * #### Description
 *
 * A CssClassNameModifier replaces an existing value with the given value.
 *
 * #### Usage
 *
 * <pre>{@code
 *     <span class="className" wicket:id="foo"></span>
 * }</pre>
 *
 * can be modified with these CssClassNameAppender:
 *
 * ```java
 * link.add(new CssClassNameModifier("className1"));
 * link.add(new CssClassNameModifier(Arrays.asList("className2", "className3")));
 * ```
 *
 * this will result in the following markup:
 *
 * ```html
 *    {@code <span class="className2 className3" wicket:id="foo"></span>}
 * ```
 */
public class CssClassNameModifier extends AttributeModifier {
    private static final long serialVersionUID = 1L;

    /**
     * The name of the html class attribute name.
     */
    private static final String ATTRIBUTE_NAME = "class";

    /**
     * Creates an AttributeModifier that appends the appendModel's value to the current value of the
     * class attribute, and will add the attribute when it is not there already.
     *
     * @param appendModel the model supplying a single value to append
     */
    public CssClassNameModifier(IModel<String> appendModel) {
        super(ATTRIBUTE_NAME, appendModel);
    }

    /**
     * Constructor.
     *
     * @param appendValue one or more values to append
     */
    public CssClassNameModifier(String... appendValue) {
        this(List.of(appendValue));
    }

    /**
     * Constructor.
     *
     * @param appendValueList a list of values to append
     */
    public CssClassNameModifier(List<String> appendValueList) {
        this(Model.of(CssClassNames.join(appendValueList)));
    }

    /**
     * Constructor.
     *
     * @param cssClassNameProvider a css class name provider
     */
    public CssClassNameModifier(ICssClassNameProvider cssClassNameProvider) {
        this(Model.of(cssClassNameProvider.cssClassName()));
    }

}
