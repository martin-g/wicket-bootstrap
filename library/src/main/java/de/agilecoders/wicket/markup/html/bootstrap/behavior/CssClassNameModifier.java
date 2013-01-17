package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.util.CssClassNames;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

/**
 * A CssClassNameModifier replaces an existing value with the given value.
 *
 * <pre>
 *     &lt;span class=&quot;className&quot; wicket:id=&quot;foo&quot;&gt;
 * </pre>
 *
 * can be modified with these CssClassNameAppender:
 *
 * <pre>
 * link.add(new CssClassNameModifier(&quot;className1&quot;));
 * link.add(new CssClassNameModifier(Arrays.asList(&quot;className2&quot;,&quot;className3&quot;)));
 * </pre>
 *
 * this will result in the following markup:
 *
 * <pre>
 *     &lt;span class=&quot;className2 className3&quot; wicket:id=&quot;foo&quot; &gt;
 * </pre>
 *
 * @author miha
 * @version 1.0
 */
public class CssClassNameModifier extends AttributeModifier {

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
        this(Lists.newArrayList(appendValue));
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
