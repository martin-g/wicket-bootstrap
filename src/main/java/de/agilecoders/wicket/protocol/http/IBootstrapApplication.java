package de.agilecoders.wicket.protocol.http;

import de.agilecoders.wicket.settings.IBootstrapSettings;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public interface IBootstrapApplication {

    public void setBootstrapSettings(IBootstrapSettings settings);

    public IBootstrapSettings getBootstrapSettings();

}
