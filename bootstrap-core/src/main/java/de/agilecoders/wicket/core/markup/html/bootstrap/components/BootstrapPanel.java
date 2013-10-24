/*
 * Copyright 2013 AgileCoders.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author dbeer
 */
public class BootstrapPanel extends Panel {
    
    private final WebMarkupContainer header;
    private final Label headerLabel;
    private final Label footerLabel;
    private final WebMarkupContainer footer;
    
    /**
     * Constructor.
     *
     * @param markupId The non-null id of this component
     */
    public BootstrapPanel(String markupId) {
        this(markupId, BootstrapPanelType.Default);
    }
    
    /**
     * Constructor Creates a default panel with header and header text of "Header".
     * Set Header text using method
     * @param markupId The non-null id of the component
     * @param type BootstrapPanelType
     */
    public BootstrapPanel(String markupId, BootstrapPanelType type) {
        super(markupId);
        
        footer = new WebMarkupContainer("footer");
        header = new WebMarkupContainer("header");
        header.add(headerLabel = new Label("header-label", ""));
        footer.add(footerLabel = new Label("footer-label", ""));
        footer.setVisible(false);
        headerLabel.setOutputMarkupId(true);
        footerLabel.setOutputMarkupId(true);
        
        add(header, footer);

        BootstrapResourcesBehavior.addTo(this);
        add(new BootstrapPanelBehavior(type));
    }
    
    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        
        checkComponentTag(tag, "div");
    }
    
    /**
     * Sets the header label text.
     *
     * @param label The header label
     * @return This
     */
    public BootstrapPanel header(IModel<String> label) {
        headerLabel.setDefaultModel(label);
        setHeaderVisible(true);
        return this;
    }
    
    /**
     * Sets the header label text.
     *
     * @param label The header label
     * @return This
     */
    public BootstrapPanel footer(IModel<String> label) {
        footerLabel.setDefaultModel(label);
        setFooterVisible(true);
        return this;
    }
    
    /**
     * Sets whether the header and any children are visible.
     *
     * @param visible True if header and any children should be visible
     * @return This
     */
    public BootstrapPanel setHeaderVisible(final boolean visible) {
        header.setVisible(visible);
        return this;
    }
    
    /**
     * Sets whether the footer and any children are visible.
     *
     * @param visible True if footer and any children should be visible
     * @return This
     */
    public BootstrapPanel setFooterVisible(final boolean visible) {
        footer.setVisible(visible);
        return this;
    }
    
}
