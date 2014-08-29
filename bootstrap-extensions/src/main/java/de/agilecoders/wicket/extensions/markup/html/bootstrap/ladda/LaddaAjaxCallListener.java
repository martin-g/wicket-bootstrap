package de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda;

import org.apache.wicket.ajax.attributes.AjaxCallListener;

/**
 * An {@link org.apache.wicket.ajax.attributes.IAjaxCallListener} that sets up Ladda for the
 * duration of the Ajax call
 */
public class LaddaAjaxCallListener extends AjaxCallListener {

    /**
     * Constructor
     */
    public LaddaAjaxCallListener() {
        onBeforeSend("var $this = jQuery('#'+attrs.c); var l = Ladda.create($this[0]); l.start(); $this.data('ladda', l)");
        onComplete("var $this = jQuery('#'+attrs.c); var l = $this.data('ladda'); if (l) {l.stop(); $this.removeData('ladda');}");
    }
}
