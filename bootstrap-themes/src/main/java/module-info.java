module wicket.bootstrap.themes {
    exports de.agilecoders.wicket.themes.markup.html.bootstrap;
    exports de.agilecoders.wicket.themes.markup.html.bootswatch;
    exports de.agilecoders.wicket.themes.markup.html.italia;
    exports de.agilecoders.wicket.themes.markup.html.material_design;

    requires org.apache.wicket.core;
    requires org.apache.wicket.request;
    requires org.apache.wicket.util;

    requires wicket.bootstrap.core;
    requires wicket.webjars;
}
