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
package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior.BackgroundColor;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior.Color;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.TextColorBehavior;

/**
 * Automatically sets panel's header background and border.
 */
public enum PanelType {
	Default(null, null, null),
	Primary(BackgroundColor.Primary, TextColorBehavior.TextColor.White, BorderBehavior.Color.Primary),
	Primary_subtle(BackgroundColor.Primary_Subtle, TextColorBehavior.TextColor.Primary_Emphasis, BorderBehavior.Color.Primary_subtle),
	Secondary(BackgroundColor.Secondary, TextColorBehavior.TextColor.White, BorderBehavior.Color.Secondary),
	Secondary_subtle(BackgroundColor.Secondary_Subtle, TextColorBehavior.TextColor.Secondary_Emphasis, BorderBehavior.Color.Secondary_subtle),
	Success(BackgroundColor.Success, TextColorBehavior.TextColor.White, BorderBehavior.Color.Success),
	Success_subtle(BackgroundColor.Success_Subtle, TextColorBehavior.TextColor.Success_Emphasis, BorderBehavior.Color.Success_subtle),
	Danger(BackgroundColor.Danger, TextColorBehavior.TextColor.White, BorderBehavior.Color.Danger),
	Danger_subtle(BackgroundColor.Danger_Subtle, TextColorBehavior.TextColor.Danger_Emphasis, BorderBehavior.Color.Danger_subtle),
	Warning(BackgroundColor.Warning, TextColorBehavior.TextColor.Black, BorderBehavior.Color.Warning),
	Warning_subtle(BackgroundColor.Warning_Subtle, TextColorBehavior.TextColor.Warning_Emphasis, BorderBehavior.Color.Warning_subtle),
	Info(BackgroundColor.Info, TextColorBehavior.TextColor.White, BorderBehavior.Color.Info),
	Info_subtle(BackgroundColor.Info_Subtle, TextColorBehavior.TextColor.Info_Emphasis, BorderBehavior.Color.Info_subtle),
	Light(BackgroundColor.Light, TextColorBehavior.TextColor.Black, BorderBehavior.Color.Light),
	Light_subtle(BackgroundColor.Light_Subtle, TextColorBehavior.TextColor.Light_Emphasis, BorderBehavior.Color.Light_subtle),
	Dark(BackgroundColor.Dark, TextColorBehavior.TextColor.White, BorderBehavior.Color.Dark),
	Dark_subtle(BackgroundColor.Dark_Subtle, TextColorBehavior.TextColor.Dark_Emphasis, BorderBehavior.Color.Dark_subtle),
	Body_secondary(BackgroundColor.Body_Secondary, null, BorderBehavior.Color.Primary),
	Body_tertiary(BackgroundColor.Body_Tertiary, null, BorderBehavior.Color.Primary),
	Body(BackgroundColor.Body, TextColorBehavior.TextColor.Body, BorderBehavior.Color.Primary),
	Black(BackgroundColor.Black, TextColorBehavior.TextColor.White, BorderBehavior.Color.Black),
	White(BackgroundColor.White, TextColorBehavior.TextColor.Black, BorderBehavior.Color.White),
	Transparent(BackgroundColor.Transparent, TextColorBehavior.TextColor.Body, null);

	private final BackgroundColor backgroundColor;
	private final TextColorBehavior.TextColor textColor;
	private final BorderBehavior.Color borderColor;

	PanelType(final BackgroundColor backgroundColor, final TextColorBehavior.TextColor textColor, final Color borderColor) {
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
		this.borderColor = borderColor;
	}

	/**
	 * @return the backgroundColor
	 */
	public BackgroundColor getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @return the textColor
	 */
	public TextColorBehavior.TextColor getTextColor() {
		return textColor;
	}

	/**
	 * @return the borderColor
	 */
	public BorderBehavior.Color getBorderColor() {
		return borderColor;
	}

}
