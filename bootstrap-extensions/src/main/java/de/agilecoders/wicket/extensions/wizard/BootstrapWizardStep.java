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
import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.model.IModel;

/**
 * @author vit
 */
public class BootstrapWizardStep extends WizardStep
{

	/**
	 * Construct.
	 */
	public BootstrapWizardStep()
	{
		super();
	}

	/**
	 * Construct.
	 *
	 * @param title
	 * @param summary
	 * @param model
	 */
	public BootstrapWizardStep(IModel<String> title, IModel<String> summary, IModel<?> model)
	{
		super(title, summary, model);
	}

	/**
	 * Construct.
	 *
	 * @param title
	 * @param summary
	 */
	public BootstrapWizardStep(IModel<String> title, IModel<String> summary)
	{
		super(title, summary);
	}

	/**
	 * Construct.
	 *
	 * @param title
	 * @param summary
	 * @param model
	 */
	public BootstrapWizardStep(String title, String summary, IModel<?> model)
	{
		super(title, summary, model);
	}

	/**
	 * Construct.
	 *
	 * @param title
	 * @param summary
	 */
	public BootstrapWizardStep(String title, String summary)
	{
		super(title, summary);
	}

	@Override
	public Component getHeader(String id, Component parent, IWizard wizard)
	{
		return new BootstrapWizardStepHeader(id, this, wizard);
	}
}
