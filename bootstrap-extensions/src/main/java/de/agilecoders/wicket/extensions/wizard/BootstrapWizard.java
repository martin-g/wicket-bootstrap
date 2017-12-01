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
package de.agilecoders.wicket.extensions.wizard;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;

/**
 * @author vit
 */
public class BootstrapWizard extends Wizard
{

	/**
	 * Construct.
	 *
	 * @param id
	 * @param wizardModel
	 */
	public BootstrapWizard(String id, IWizardModel wizardModel)
	{
		super(id, wizardModel);
	}

	/**
	 * Construct.
	 *
	 * @param id
	 */
	public BootstrapWizard(String id)
	{
		super(id);
	}

	@Override
	protected Component newFeedbackPanel(String id)
	{
		return new NotificationPanel(id, new ContainerFeedbackMessageFilter(this));
	}

	@Override
	protected Component newButtonBar(String id)
	{
		return new BootstrapWizardButtonBar(id, this);
	}
}
