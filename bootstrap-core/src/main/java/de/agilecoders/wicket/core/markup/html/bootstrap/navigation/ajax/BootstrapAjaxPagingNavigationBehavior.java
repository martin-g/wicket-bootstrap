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
package de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.IAjaxLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigationBehavior;
import org.apache.wicket.markup.html.navigation.paging.IPageable;

/**
 * A specialization of AjaxPagingNavigationBehavior that expects to have parent of type
 * {@link BootstrapAjaxPagingNavigator}
 */
public class BootstrapAjaxPagingNavigationBehavior extends AjaxPagingNavigationBehavior {

    /**
     * The ajaxian link that should receive the event.
     */
    private final IAjaxLink owner;

    /**
     * Attaches the navigation behavior to the owner link and drives the pageable component. The
     * behavior is attached to the markup event.
     *
     * @param owner    the owner ajax link
     * @param pageable the pageable to update
     * @param event    the javascript event to bind to (e.g. onclick)
     */
    public BootstrapAjaxPagingNavigationBehavior(IAjaxLink owner, IPageable pageable, String event) {
        super(owner, pageable, event);
        this.owner = owner;
    }

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);
        attributes.setPreventDefault(true);
    }

    /**
     * The ajax event handler. This will execute the event, and update the following components,
     * when present: the navigator the owner link is part of, or when the link is a stand alone
     * component, the link itself. Also the pageable's parent markup container is updated, so its
     * contents can be replaced with the newly generated pageable.
     *
     * @see org.apache.wicket.ajax.AjaxEventBehavior#onEvent(org.apache.wicket.ajax.AjaxRequestTarget)
     */
    @Override
    protected void onEvent(AjaxRequestTarget target) {
        // handle the event
        owner.onClick(target);

        // find the PagingNavigator parent of this link
        BootstrapAjaxPagingNavigator navigator = ((Component) owner).findParent(BootstrapAjaxPagingNavigator.class);

        // if this is embedded inside a navigator
        if (navigator != null) {
            // tell the PagingNavigator to update the IPageable
            navigator.onAjaxEvent(target);
        }
    }
}
