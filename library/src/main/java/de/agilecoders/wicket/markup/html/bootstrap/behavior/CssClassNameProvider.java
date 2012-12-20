package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.util.io.IClusterable;

/**
 * The {@code CssClassNameProvider} provides an interface to abstract
 * the styling of a component and the rendering of the class attribute.
 *
 * This interface can be used with a {@link Enum}:
 * <pre>
 *     public enum Color implements {
 *         Red, Blue;
 *
 *         public String cssClassName() {
 *             return name().toLowerCase();
 *         }
 *
 *         public CssClassNameAppender newCssClassNameModifier() {
 *             return new CssClassNameAppender(cssClassName());
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
 * @version 1.0
 */
public interface CssClassNameProvider extends IClusterable {

    /**
     * @return a css class name
     */
    String cssClassName();

    /**
     * @return a {@link AttributeModifier} which contains the class name
     *         from {@link CssClassNameProvider#cssClassName()}
     */
    AttributeModifier newCssClassNameModifier();

}
