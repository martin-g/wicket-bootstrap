package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.template.PackageTextTemplate;

public class SummernoteEditor extends Panel {

    private static final long serialVersionUID = 1L;

    private SummernoteConfig config;

    public SummernoteEditor(String id) {
	this(id, null, new SummernoteConfig());
    }

    public SummernoteEditor(String id, IModel<?> model) {
	this(id, model, new SummernoteConfig());
    }

    public SummernoteEditor(String id, IModel<?> model, SummernoteConfig config) {
	super(id, model);
	this.config = Args.notNull(config, "config");
    }

    @Override
    public void renderHead(IHeaderResponse response) {
	response.render(JavaScriptHeaderItem.forReference(SummernoteEditorJavaScriptReference.instance()));
	response.render(CssHeaderItem.forReference(SummernoteEditorCssReference.instance()));
	response.render(CssHeaderItem.forUrl("//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"));
	PackageTextTemplate summernoteTemplate = null;
	try {
	    summernoteTemplate = new PackageTextTemplate(SummernoteEditor.class, "js/summernote_init.js");
	    String jsonConfig = config.toJsonString();
	    Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put("config", jsonConfig);
	    String summernoteTemplateJavaScript = summernoteTemplate.asString(variables);
	    response.render(OnDomReadyHeaderItem.forScript(summernoteTemplateJavaScript));
	} finally {
	    IOUtils.closeQuietly(summernoteTemplate);
	}
    }
}
