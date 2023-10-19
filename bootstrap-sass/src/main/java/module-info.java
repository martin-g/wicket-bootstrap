module wicket.bootstrap.saas {
    exports de.agilecoders.wicket.sass;

    requires jsass;
    requires de.agilecoders.wicket.webjars;

    requires org.apache.commons.io;
    requires org.apache.commons.lang3;

    requires org.apache.wicket.core;
    requires org.apache.wicket.request;
    requires org.apache.wicket.util;

    requires jakarta.servlet;

    requires org.slf4j;
}
