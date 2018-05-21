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

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.extensions.wizard.WizardButton;
import org.apache.wicket.extensions.wizard.WizardButtonBar;


/**
 * @author vit
 */
public class BootstrapWizardButtonBar extends WizardButtonBar
{

	/**
	 * Construct.
	 *
	 * @param id
	 * @param wizard
	 */
	public BootstrapWizardButtonBar(String id, IWizard wizard)
	{
		super(id, wizard);
	}

	@Override
	protected WizardButton newCancelButton(String id, IWizard wizard)
	{
        WizardButton button = super.newCancelButton(id, wizard);
		button.add(new ButtonBehavior(Type.Warning, Buttons.Size.Medium));
		return button;
	}

	@Override
	protected WizardButton newFinishButton(String id, IWizard wizard)
	{
        WizardButton button = super.newFinishButton(id, wizard);
		button.add(new ButtonBehavior(Type.Success, Buttons.Size.Medium));
		return button;
	}

	@Override
	protected WizardButton newLastButton(String id, IWizard wizard)
	{
        WizardButton button = super.newLastButton(id, wizard);
		button.add(new ButtonBehavior(Type.Default, Buttons.Size.Medium));
		return button;
	}

	@Override
	protected WizardButton newNextButton(String id, IWizard wizard)
	{
        WizardButton button = super.newNextButton(id, wizard);
		button.add(new ButtonBehavior(Type.Default, Buttons.Size.Medium));
		return button;
	}

	@Override
	protected WizardButton newPreviousButton(String id, IWizard wizard)
	{
        WizardButton button = super.newPreviousButton(id, wizard);
		button.add(new ButtonBehavior(Type.Default, Buttons.Size.Medium));
		return button;
	}
}
