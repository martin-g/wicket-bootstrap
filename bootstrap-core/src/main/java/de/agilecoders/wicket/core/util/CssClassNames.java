package de.agilecoders.wicket.core.util;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.jquery.util.Generics2;
import de.agilecoders.wicket.jquery.util.Strings2;

/**
 * #### Description
 *
 * helper class for css class names
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public final class CssClassNames {

    /**
     * creates a new {@link ICssClassNameProvider} from a given model.
     *
     * @param model the model to use for class name provider
     * @return new {@link ICssClassNameProvider} implementation
     */
    public static ICssClassNameProvider newProvider(final IModel<String> model) {
        Args.notNull(model, "model");

        return new ICssClassNameProvider() {
            @Override
            public String cssClassName() {
                return model.getObject();
            }
        };
    }

    /**
     * Constructor.
     */
    private CssClassNames() {
        throw new UnsupportedOperationException();
    }

    /**
     * joins a list of css class names.
     *
     * @param classNames The css class names to join
     * @return the joined class names.
     */
    public static String join(Iterable<String> classNames) {
        return StringUtils.join(classNames, " ");
    }

    /**
     * splits a given class attribute value.
     *
     * @param classValue The css class names as string
     * @return a new set of css class names.
     */
    public static Set<String> split(final CharSequence classValue) {
        final Set<String> result = new LinkedHashSet<>();
        final String clazzes = (classValue != null) ? classValue.toString() : null;

        if (StringUtils.isNotBlank(clazzes)) {
            for (String clazz : StringUtils.split(clazzes, " ")) {
                clazz = StringUtils.trimToNull(clazz);
                if (clazz != null) {
                    result.add(clazz);
                }
            }
        }

        return result;
    }

    /**
     * returns a {@link Builder} to add/remove new values.
     *
     * @return new Builder.
     */
    public static Builder newBuilder() {
        return new Builder("");
    }

    /**
     * parses the given value and returns a {@link Builder} to add/remove
     * new values.
     *
     * @param classValue The class attribute value
     * @return new Builder.
     */
    public static Builder parse(final String classValue) {
        return new Builder(classValue);
    }

    /**
     * Builder for css class names. All css class names will be hold in a
     * {@link Set} of strings.
     */
    public static final class Builder {
        private final Set<String> classValues;

        /**
         * Construct.
         *
         * @param classValue the initial class name string
         */
        private Builder(final String classValue) {
            classValues = Generics2.newLinkedHashSet(Collections.<String>emptySet());

            addRaw(classValue);
        }

        /**
         * adds an unparsed css class name string.
         *
         * @param rawCssString the raw css classes
         * @return this instance for chaining
         */
        public Builder addRaw(final String rawCssString) {
            add(split(Strings2.nullToEmpty(rawCssString)));
            return this;
        }

        /**
         * removes all class names that are set on given builder
         *
         * @param builder The {@link Builder}
         * @return this builder instance for chaining
         */
        public Builder remove(final Builder builder) {
            Args.notNull(builder, "builder");

            return remove(builder.asSet());
        }

        /**
         * removes all class names that are given
         *
         * @param classNames The css class names to remove
         * @return this builder instance for chaining
         */
        public Builder remove(final String... classNames) {
            Args.notNull(classNames, "classNames");

            return remove(Set.of(classNames));
        }

        /**
         * removes all given class names
         *
         * @param classNames The css class names to remove
         * @return this builder instance for chaining
         */
        public Builder remove(final Set<String> classNames) {
            Args.notNull(classNames, "classNames");

            classValues.removeAll(classNames);
            return this;
        }

        /**
         * adds all class names that are set on given builder
         *
         * @param builder The {@link Builder}
         * @return this builder instance for chaining
         */
        public Builder add(final Builder builder) {
            Args.notNull(builder, "builder");

            return add(builder.asSet());
        }

        /**
         * adds all given class names
         *
         * @param classNames The css class names to add
         * @return this builder instance for chaining
         */
        public Builder add(final String... classNames) {
            Args.notNull(classNames, "classNames");

            return add(Generics2.newLinkedHashSet(List.of(classNames)));
        }

        /**
         * adds all given class names
         *
         * @param classNames The css class names to add
         * @return this builder instance for chaining
         */
        public Builder add(final Set<String> classNames) {
            Args.notNull(classNames, "classNames");

            classValues.addAll(classNames);
            return this;
        }

        /**
         * @return all css class names as valid html attribute value
         */
        public String asString() {
            return join(classValues);
        }

        /**
         * @return all css class names as list of strings
         */
        public List<String> asList() {
            return Generics2.newArrayList(classValues);
        }

        /**
         * @return all css class names as set of strings
         */
        public Set<String> asSet() {
            return Generics2.newLinkedHashSet(classValues);
        }

        /**
         * checks if given class name was added before
         *
         * @param className The class name to check
         * @return true, if class name was set
         */
        public boolean contains(final String className) {
            return classValues.contains(className);
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static final class Helper {
        public static final String clearfix = "clearfix";
        public static final String visuallyHidden = "visually-hidden";
    }

    @SuppressWarnings("UnusedDeclaration")
    public static final class Form {
        public static final String form = "form";
        public static final String formRow = "form-row";
        public static final String control = "form-control";
        public static final String label = "col-form-label";
        public static final String staticControl = "form-control-static";
        public static final String feedbackControl = "form-control-feedback";
        public static final String group = "form-group";
        public static final String groupLarge = "form-control-lg";
        public static final String groupSmall = "form-control-sm";
        public static final String horizontal = "form-horizontal";
        public static final String help = "form-text";
        public static final String inline = "form-inline";
        public static final String disabled = "disabled";
        public static final String formCheck = "form-check";
        public static final String hasSuccess = "has-success";
        public static final String hasWarning = "has-warning";
        public static final String hasFeedback = "has-feedback";
        public static final String hasError = "has-error";
        public static final String large = "form-control-lg";
        public static final String small = "form-control-sm";
    }

    @SuppressWarnings("UnusedDeclaration")
    public static final class Table {
        public static final String table = "table";
        public static final String striped = "table-striped";
        public static final String bordered = "table-bordered";
        public static final String hover = "table-hover";
        public static final String sm = "table-sm";
        public static final String responsive = "table-responsive";
        public static final String dark = "table-dark";
        public static final String light = "table-light";
    }

    @SuppressWarnings("UnusedDeclaration")
    public static final class Grid {
        public static final String container = "container"; // fixed-width
        public static final String containerFluid = "container-fluid"; // full-width
        public static final String row = "row";
        public static final String colLarge = "col-lg";
        public static final String colSmall = "col-sm";
        public static final String colMedium = "col-md";
        public static final String colXtraSmall = "col-xs";
        public static final String visibleLargeBlock = "visible-lg-block";
        public static final String visibleSmallBlock = "visible-sm-block";
        public static final String visibleMediumBlock = "visible-md-block";
        public static final String visibleXtraSmallBlock = "visible-xs-block";
    }

    @SuppressWarnings("UnusedDeclaration")
    public static final class Typography {
        public static final String lead = "lead";
        public static final String lowercase = "text-lowercase";
        public static final String uppercase = "text-uppercase";
        public static final String capitalize = "text-capitalize";
        public static final String initialism = "initialism";
        public static final String textLeft = "text-left";
        public static final String textCenter = "text-center";
        public static final String textRight = "text-right";
        public static final String textJustify = "text-justify";
        public static final String textNowrap = "text-nowrap";
        public static final String listInline = "list-inline";
    }
}
