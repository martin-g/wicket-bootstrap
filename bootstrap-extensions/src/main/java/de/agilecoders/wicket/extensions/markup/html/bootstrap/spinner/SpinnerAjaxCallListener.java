package de.agilecoders.wicket.extensions.markup.html.bootstrap.spinner;

import org.apache.wicket.ajax.attributes.AjaxCallListener;

/**
 * An {@link org.apache.wicket.ajax.attributes.IAjaxCallListener} that sets up Ladda for the
 * duration of the Ajax call
 */
public class SpinnerAjaxCallListener extends AjaxCallListener {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public SpinnerAjaxCallListener() {
        onBeforeSend("var $this = jQuery('#'+attrs.c); var l = Ladda.create($this[0]); l.start(); $this.data('ladda', l)");
        onComplete("var $this = jQuery('#'+attrs.c); var l = $this.data('ladda'); if (l) {l.stop(); $this.removeData('ladda');}");
    }
}
