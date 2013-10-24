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

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import org.apache.wicket.Component;

/**
 *
 * @author dbeer
 */
public final class BootstrapPanelBehavior extends BootstrapBaseBehavior {
    
    private BootstrapPanelType type;

    public BootstrapPanelBehavior() {
        this(BootstrapPanelType.Default);
    }

    public BootstrapPanelBehavior(BootstrapPanelType type) {
        this.type = type;
    }

    public BootstrapPanelBehavior type(BootstrapPanelType type) {
        this.type = type;
        return this;
    }
    
    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(type.newCssClassNameModifier());
    }
}
