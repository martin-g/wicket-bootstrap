package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.util.io.IClusterable;

/**
 * The {@code ICssClassNameModifier} provides an interface to abstract
 * the styling of a component and the rendering of the class attribute.
 * <p/>
 * This interface can be used with a {@link Enum}:
 * <pre>
 *     public enum Color implements {
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
 * </pre>
 *
 * @author miha
 */
public interface ICssClassNameModifier extends IClusterable {

    /**
     * @return a {@link org.apache.wicket.AttributeModifier} which contains
     *         the css class name
     */
    AttributeModifier newCssClassNameModifier();

}
