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

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import org.apache.wicket.AttributeModifier;

/**
 *
 * @author dbeer
 */
public enum BootstrapPanelType implements ICssClassNameProvider, ICssClassNameModifier {

    Default("panel-default"), // Standard gray button with gradient
    Primary("panel-primary"),
    Info("panel-info"), // Used as an alternate to the default styles
    Success("panel-success"), // Indicates a successful or positive action
    Warning("panel-warning"), // Indicates caution should be taken with this action
    Danger("panel-danger"); // Indicates a dangerous or potentially negative action

    private final String cssClassName;
    /**
     * Construct.
     *
     * @param cssClassName The css class name to use
     */
    private BootstrapPanelType(final String cssClassName) {
        this.cssClassName = cssClassName;
    }
    
    @Override
    public String cssClassName() {
        return cssClassName;
    }

    @Override
    public AttributeModifier newCssClassNameModifier() {
        return new CssClassNameAppender(this);
    }

}
