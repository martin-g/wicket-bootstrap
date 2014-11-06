package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.core.util.Dependencies;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * Collect js resource for validation
 *
 * @author Alexey Volkov
 * @since 07.11.14
 */
public final class ValidationJS {

    private static class CommonValidationJSReference extends JQueryPluginResourceReference {

        private static final long serialVersionUID = -3871695265486921009L;

        private static CommonValidationJSReference instance = new CommonValidationJSReference();

        private CommonValidationJSReference() {
            super(ValidationJS.class, "js/jquery.wb.validation.js");
        }
    }

    private static class TooltipValidationJSReference extends JQueryPluginResourceReference {

        private static final long serialVersionUID = -3871695265486921009L;

        private static TooltipValidationJSReference instance = new TooltipValidationJSReference();

        private TooltipValidationJSReference() {
            super(ValidationJS.class, "js/jquery.wb.validation.tooltip.js");
        }

        @Override
        public Iterable<? extends HeaderItem> getDependencies() {
            return Dependencies.combine(super.getDependencies(), JavaScriptHeaderItem.forReference(common()));
        }
    }

    private static class SimpleValidationJSReference extends JQueryPluginResourceReference {

        private static final long serialVersionUID = -3871695265486921009L;

        private static SimpleValidationJSReference instance = new SimpleValidationJSReference();

        private SimpleValidationJSReference() {
            super(ValidationJS.class, "js/jquery.wb.validation.message.js");
        }

        @Override
        public Iterable<? extends HeaderItem> getDependencies() {
            return Dependencies.combine(super.getDependencies(), JavaScriptHeaderItem.forReference(common()));
        }

    }

    private ValidationJS() {
    }

    /**
     * @return common validation js reference
     */
    public static JavaScriptResourceReference common() {
        return CommonValidationJSReference.instance;
    }

    /**
     * @return tooltip validation js reference
     */
    public static JavaScriptResourceReference tooltip() {
        return TooltipValidationJSReference.instance;
    }

    /**
     * @return simple messages validation js reference
     */
    public static JavaScriptResourceReference simple() {
        return SimpleValidationJSReference.instance;
    }
}
