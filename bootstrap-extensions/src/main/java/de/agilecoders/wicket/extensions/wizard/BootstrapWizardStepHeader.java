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

import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * @author rozkovec
 */
class BootstrapWizardStepHeader extends Panel
{

	/**
	 * Construct.
	 *
	 * @param id
	 * @param wizardStep
	 * @param wizard
	 */
	public BootstrapWizardStepHeader(final String id, BootstrapWizardStep wizardStep, final IWizard wizard)
	{
		super(id);
		setDefaultModel(new CompoundPropertyModel<>(wizard));
		add(new Label("title", wizardStep::getTitle));
		add(new Label("summary", wizardStep::getSummary));
	}
}
