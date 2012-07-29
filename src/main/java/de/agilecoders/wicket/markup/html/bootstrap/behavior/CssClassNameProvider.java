package de.agilecoders.wicket.markup.html.bootstrap.behavior;

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
 *         public CssClassNameAppender newCssClassNameAppender() {
 *             return new CssClassNameAppender(cssClassName());
 *         }
 *     }
 *
 *     public MyColoredComponent extends Component {
 *         public MyColoredComponent(String id, Color color) {
 *             super(id);
 *
 *             add(color.newCssClassNameAppender());
 *         }
 *     }
 * </pre>
 *
 * @author miha
 * @version 1.0
 */
public interface CssClassNameProvider {

    /**
     * @return a css class name
     */
    String cssClassName();

    /**
     * TODO: refactor this interface to be more abstract and not depending on a
     *       specific implementation. (Could be a special Behavior)
     *
     * @return a {@link CssClassNameAppender} which contains the class name
     *         from {@link de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider#cssClassName()}
     */
    CssClassNameAppender newCssClassNameAppender();

}
