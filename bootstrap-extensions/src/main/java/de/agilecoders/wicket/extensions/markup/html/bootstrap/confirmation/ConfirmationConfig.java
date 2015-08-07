package de.agilecoders.wicket.extensions.markup.html.bootstrap.confirmation;

import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipConfig;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.Key;
import org.apache.wicket.util.lang.Args;

/**
 * The configuration of {@link ConfirmationBehavior}.
 * <br/>
 * See <a href="http://mistic100.github.io/Bootstrap-Confirmation/#usage">Bootstrap Confirmation usage</a>
 * for all possible options and their meaning.
 */
public class ConfirmationConfig extends AbstractConfig {

    private static final IKey<String> Title = new Key<String>("title", "Are you sure?");
    private static final IKey<Boolean> Singleton = new Key<Boolean>("singleton", Boolean.FALSE);
    private static final IKey<Boolean> Popout = new Key<Boolean>("popout", Boolean.FALSE);
    private static final IKey<String> BtnOkClass = new Key<String>("btnOkClass", "btn-xs btn-primary");
    private static final IKey<String> BtnOkIcon = new Key<String>("btnOkIcon", "glyphicon glyphicon-ok");
    private static final IKey<String> BtnOkLabel = new Key<String>("btnOkLabel", "Yes");
    private static final IKey<String> BtnCancelClass = new Key<String>("btnCancelClass", "btn-xs btn-default");
    private static final IKey<String> BtnCancelIcon = new Key<String>("btnCancelIcon", "glyphicon glyphicon-remove");
    private static final IKey<String> BtnCancelLabel = new Key<String>("btnCancelLabel", "No");
    private static final IKey<TooltipConfig.Placement> Placement = new Key<TooltipConfig.Placement>("placement", TooltipConfig.Placement.top);

    public ConfirmationConfig withTitle(String title) {
        put(Title, title);
        return this;
    }

    public ConfirmationConfig withBtnOkClass(String btnOkClass) {
        put(BtnOkClass, btnOkClass);
        return this;
    }

    public ConfirmationConfig withBtnOkIcon(String btnOkIcon) {
        put(BtnOkIcon, btnOkIcon);
        return this;
    }

    public ConfirmationConfig withBtnOkLabel(String btnOkLabel) {
        put(BtnOkLabel, btnOkLabel);
        return this;
    }

    public ConfirmationConfig withBtnCancelClass(String btnCancelClass) {
        put(BtnCancelClass, btnCancelClass);
        return this;
    }

    public ConfirmationConfig withBtnCancelIcon(String btnCancelIcon) {
        put(BtnCancelIcon, btnCancelIcon);
        return this;
    }

    public ConfirmationConfig withBtnCancelLabel(String btnCancelLabel) {
        put(BtnCancelLabel, btnCancelLabel);
        return this;
    }

    public ConfirmationConfig withSingleton(boolean singleton) {
        put(Singleton, singleton);
        return this;
    }

    public ConfirmationConfig withPopout(boolean popout) {
        put(Popout, popout);
        return this;
    }

    public ConfirmationConfig withPlacement(TooltipConfig.Placement placement) {
        put(Placement, Args.notNull(placement, "placement"));
        return this;
    }
}
