package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.util.io.IClusterable;

/**
 * #### Description
 *
 * The {@code ICssClassNameModifier} provides an interface to abstract
 * the styling of a component and the rendering of the class attribute.
 * <p/>
 * #### Usage
 *
 * This interface can be used with a {@link Enum}:
 *
 * ```java
 *     public enum Color implements ICssClassNameModifier {
 *         Red, Blue;
 *
 *         public CssClassNameAppender newCssClassNameModifier() {
 *             return new CssClassNameAppender(name());
 *         }
 *     }
 *
 *     public MyColoredComponent extends Component {
 *         public MyColoredComponent(String id, Color color) {
 *             super(id);
 *
 *             add(color.newCssClassNameModifier());
 *         }
 *     }
 * ```
 */
public interface ICssClassNameModifier extends IClusterable {

    /**
     * @return a {@link org.apache.wicket.AttributeModifier} which contains
     *         the css class name
     */
    AttributeModifier newCssClassNameModifier();

}
