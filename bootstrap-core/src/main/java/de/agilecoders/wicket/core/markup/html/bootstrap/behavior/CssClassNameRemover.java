/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.util.CssClassNames;

/**
 * #### Description
 *
 * Removes a value for the CSS class attribute
 *
 * #### Usage
 *
 * ```java
 * component.add(new CssClassNameRemover("class-name-a", "class-name-b"));
 * component.add(new CssClassNameRemover(List("class-name-a", "class-name-b")));
 * component.add(new CssClassNameRemover(Model.of("class-name-a")));
 * ```
 */
public class CssClassNameRemover extends CssClassNameAppender {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an AttributeModifier that appends the removeModel's value to the current value of the
     * class attribute, and will add the attribute when it is not there already.
     *
     * @param removeModel the model supplying a single value to append
     */
    public CssClassNameRemover(IModel<String> removeModel) {
        super(removeModel);
    }

    /**
     * Constructor.
     * {@link CssClassNameAppender#CssClassNameAppender(org.apache.wicket.model.IModel)}
     *
     * @param removeValue one or more values to append
     */
    public CssClassNameRemover(String... removeValue) {
        this(List.of(removeValue));
    }

    /**
     * Constructor.
     * {@link CssClassNameAppender#CssClassNameAppender(org.apache.wicket.model.IModel)}
     *
     * @param removeValueList a list of values to append
     */
    public CssClassNameRemover(List<String> removeValueList) {
        this(Model.of(CssClassNames.join(removeValueList)));
    }

    /**
     * Constructor.
     * {@link CssClassNameAppender#CssClassNameAppender(org.apache.wicket.model.IModel)}
     *
     * @param cssClassNameProvider a css class name provider
     */
    public CssClassNameRemover(ICssClassNameProvider cssClassNameProvider) {
        this(Model.of(cssClassNameProvider.cssClassName()));
    }

    @Override
    protected String newValue(String currentValue, String removeValue) {
        // Short circuit when one of the values is empty: return the other value.
        if (Strings.isEmpty(currentValue)) {
            return null;
        } else if (Strings.isEmpty(removeValue)) {
            return currentValue != null ? currentValue : null;
        }

        return CssClassNames.parse(currentValue).remove(CssClassNames.parse(removeValue)).asString();
    }
}
