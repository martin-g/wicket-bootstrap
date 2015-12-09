package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

/**
 * A specialization of {@link AjaxButton} that should be used to
 * submit the data of a {@link SummernoteEditor} in air mode
 */
public class SummernoteAjaxButton extends AjaxButton {

    private final String editorInputName;

    public SummernoteAjaxButton(String id, SummernoteEditor editor) {
        this(id, null, null, editor);
    }

    public SummernoteAjaxButton(String id, IModel<String> model, SummernoteEditor editor) {
        this(id, model, null, editor);
    }

    public SummernoteAjaxButton(String id, Form<?> form, SummernoteEditor editor) {
        this(id, null, form, editor);
    }

    public SummernoteAjaxButton(String id, IModel<String> model, Form<?> form, SummernoteEditor editor) {
        super(id, model, form);

        this.editorInputName = editor.getInputName();
    }

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);

        String js = String.format("return [{\"name\": \"%s\", \"value\": $('div[name=%s]').summernote('code')}]", editorInputName, editorInputName);
        attributes.getDynamicExtraParameters().add(js);
    }
}
