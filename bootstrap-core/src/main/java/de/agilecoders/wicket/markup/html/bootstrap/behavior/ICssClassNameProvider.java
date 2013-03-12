package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import org.apache.wicket.util.io.IClusterable;

/**
 * The {@code ICssClassNameProvider} provides an interface to abstract
 * the styling of a component and the rendering of the class attribute.
 * <p/>
 * This interface can be used with a {@link Enum}:
 * <pre>
 *     public enum Color implements {
 *         Red, Blue;
 *
 *         public String cssClassName() {
 *             return name().toLowerCase();
 *         }
 *
 *     }
 *
 *     public MyColoredComponent extends Component {
 *         public MyColoredComponent(String id, Color color) {
 *             super(id);
 *
 *             add(new CssClassNameAppender(color.cssClassName()));
 *         }
 *     }
 * </pre>
 *
 * @author miha
 */
public interface ICssClassNameProvider extends IClusterable {

    /**
     * @return a css class name
     */
    String cssClassName();

}