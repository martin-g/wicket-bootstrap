package de.agilecoders.wicket.themes.markup.html.italia;

import org.apache.wicket.request.resource.CssResourceReference;

public class ItaliaCssReference extends CssResourceReference {
	
	private static final long serialVersionUID = 4361205879317055824L;

    public ItaliaCssReference() {
        super(ItaliaCssReference.class, "css/bootstrap-italia.min.css");
    }

	/**
     * @return singleton instance of {@link ItaliaCssReference}
     */
    public static ItaliaCssReference instance() {
        return Holder.INSTANCE;
    }
    
    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final ItaliaCssReference INSTANCE = new ItaliaCssReference();
    }

}
