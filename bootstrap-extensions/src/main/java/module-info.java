module wicket.bootstrap.extensions {
    exports de.agilecoders.wicket.extensions.javascript;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.confirmation;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstraptoggle;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkboxx;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.clockpicker;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.password.strength;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.inputmask;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.markdown;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.navigation;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.references;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.spinner;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.table;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.table.filter;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.table.sort;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.table.toolbars;
    exports de.agilecoders.wicket.extensions.markup.html.bootstrap.tour;
    exports de.agilecoders.wicket.extensions.request;
    exports de.agilecoders.wicket.extensions.wizard;

    requires org.apache.commons.fileupload2.core;

    requires org.apache.wicket.core;
    requires org.apache.wicket.extensions;
    requires org.apache.wicket.request;
    requires org.apache.wicket.util;

    requires wicket.bootstrap.core;
    requires de.agilecoders.wicket.jquery;
    requires de.agilecoders.wicket.webjars;

    requires com.google.common;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires org.slf4j;

    requires static yuicompressor;
    requires static closure.compiler.unshaded.v20231112;
}
