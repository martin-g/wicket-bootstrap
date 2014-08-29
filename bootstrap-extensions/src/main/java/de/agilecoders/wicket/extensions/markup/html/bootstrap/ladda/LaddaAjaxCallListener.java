package de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda;

import org.apache.wicket.ajax.attributes.AjaxCallListener;

/**
 *
 */
public class LaddaAjaxCallListener extends AjaxCallListener {

    public LaddaAjaxCallListener() {
        onBeforeSend("var $this = jQuery('#'+attrs.c); var l = Ladda.create($this[0]); l.start(); $this.data('ladda', l)");
        onComplete("var $this = jQuery('#'+attrs.c); var l = $this.data('ladda'); if (l) {l.stop();}");
    }
}
