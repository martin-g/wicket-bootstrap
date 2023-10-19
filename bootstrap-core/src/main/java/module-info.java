module wicket.bootstrap.core {
    exports de.agilecoders.wicket.core;
    exports de.agilecoders.wicket.core.markup.html;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.badge;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.behavior;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.block;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.button;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.carousel;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.common;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.components;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.dialog;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.form;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.form.radio;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.heading;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.html;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.image;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.layout;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.layout.col;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.layout.order;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.list;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.navbar;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.navigation;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.panel;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.table;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.tabs;
    exports de.agilecoders.wicket.core.markup.html.bootstrap.utilities;
    exports de.agilecoders.wicket.core.markup.html.references;
    exports de.agilecoders.wicket.core.markup.html.themes.bootstrap;
    exports de.agilecoders.wicket.core.settings;
    exports de.agilecoders.wicket.core.util;

    requires org.apache.commons.lang3;

    requires org.apache.wicket.core;
    requires org.apache.wicket.extensions;
    requires org.apache.wicket.request;
    requires org.apache.wicket.util;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires org.slf4j;

    requires de.agilecoders.wicket.jquery;
    requires de.agilecoders.wicket.webjars;
}
