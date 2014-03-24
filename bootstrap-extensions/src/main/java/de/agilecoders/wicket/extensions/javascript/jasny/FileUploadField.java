package de.agilecoders.wicket.extensions.javascript.jasny;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * An extension of Wicket's default FileUploadField that contributes
 * <a href="http://jasny.github.com/bootstrap/javascript.html#fileupload">Jasny</a>'s special
 * CSS and JS resources for making &lt;input type="file" /&gt; looking nicer.
 * <p/>
 * <p>As markup use any of the recommended markup snippets at
 * <a href="http://jasny.github.com/bootstrap/javascript.html#fileupload">Jasny File Upload</a></p>
 */
public class FileUploadField extends org.apache.wicket.markup.html.form.upload.FileUploadField {

    /**
     * Construct.
     *
     * @param id component id
     */
    public FileUploadField(String id) {
        super(id);
    }

    /**
     * Construct.
     *
     * @param id    component id
     * @param model model that holds the uploaded files
     */
    public FileUploadField(String id, IModel<List<FileUpload>> model) {
        super(id, model);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(newJasnyHeaderItem());
    }

    /**
     * @return jasny resource reference
     */
    protected HeaderItem newJasnyHeaderItem() {
        return JavaScriptHeaderItem.forReference(JasnyJsReference.instance());
    }
}
