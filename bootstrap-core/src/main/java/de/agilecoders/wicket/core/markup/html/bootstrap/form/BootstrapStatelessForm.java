/*
 * Copyright 2014 AgileCoders.
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

package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.IModel;

/**
 *
 * @author dbeer
 */
public class BootstrapStatelessForm<T> extends StatelessForm<T> {

    private FormBehavior formBehavior;
    
    /**
     * Constructs a from with no validation
     *
     * @param id See Component
     */
    public BootstrapStatelessForm(String id) {
        super(id);
        
        commonInit();
    }

    /**
     * @see org.apache.wicket.Component#Component(String, IModel)
     * @param id See Component
     * @param model See Component
     */
    public BootstrapStatelessForm(String id, IModel<T> model) {
        super(id, model);
        
        commonInit();
    }
    
    /**
     * Common code executed by constructors.
     */
    private void commonInit() {
        add(formBehavior = new FormBehavior());
    }
    
    public BootstrapStatelessForm<T> type(FormType type) {
        formBehavior.type(type);
        return this;
    }
    
}
