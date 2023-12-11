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

import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior.Color;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.ColorBehavior;

/**
 * Automatically sets panel's header background and border.
 */
public enum PanelType
{
	Default(null, null, null),
	Primary(BackgroundColorBehavior.Color.Primary, ColorBehavior.Color.White, BorderBehavior.Color.Primary),
	Primary_subtle(BackgroundColorBehavior.Color.Primary_subtle, ColorBehavior.Color.Primary_emphasis, BorderBehavior.Color.Primary_subtle),
	Secondary(BackgroundColorBehavior.Color.Secondary, ColorBehavior.Color.White, BorderBehavior.Color.Secondary),
	Secondary_subtle(BackgroundColorBehavior.Color.Secondary_subtle, ColorBehavior.Color.Secondary_emphasis, BorderBehavior.Color.Secondary_subtle),
	Success(BackgroundColorBehavior.Color.Success, ColorBehavior.Color.White, BorderBehavior.Color.Success),
	Success_subtle(BackgroundColorBehavior.Color.Success_subtle, ColorBehavior.Color.Success_emphasis, BorderBehavior.Color.Success_subtle),
	Danger(BackgroundColorBehavior.Color.Danger, ColorBehavior.Color.White, BorderBehavior.Color.Danger),
	Danger_subtle(BackgroundColorBehavior.Color.Danger_subtle, ColorBehavior.Color.Danger_emphasis, BorderBehavior.Color.Danger_subtle),
	Warning(BackgroundColorBehavior.Color.Warning, ColorBehavior.Color.Black, BorderBehavior.Color.Warning),
	Warning_subtle(BackgroundColorBehavior.Color.Warning_subtle, ColorBehavior.Color.Warning_emphasis, BorderBehavior.Color.Warning_subtle),
	Info(BackgroundColorBehavior.Color.Info, ColorBehavior.Color.White, BorderBehavior.Color.Info),
	Info_subtle(BackgroundColorBehavior.Color.Info_subtle, ColorBehavior.Color.Info_emphasis, BorderBehavior.Color.Info_subtle),
	Light(BackgroundColorBehavior.Color.Light, ColorBehavior.Color.Black, BorderBehavior.Color.Light),
	Light_subtle(BackgroundColorBehavior.Color.Light_subtle, ColorBehavior.Color.Light_emphasis, BorderBehavior.Color.Light_subtle),
	Dark(BackgroundColorBehavior.Color.Dark, ColorBehavior.Color.White, BorderBehavior.Color.Dark),
	Dark_subtle(BackgroundColorBehavior.Color.Dark_subtle, ColorBehavior.Color.Dark_emphasis, BorderBehavior.Color.Dark_subtle),
	Body_secondary(BackgroundColorBehavior.Color.Body_secondary, null, BorderBehavior.Color.Primary),
	Body_tertiary(BackgroundColorBehavior.Color.Body_tertiary, null, BorderBehavior.Color.Primary),
	Body(BackgroundColorBehavior.Color.Body, ColorBehavior.Color.Body, BorderBehavior.Color.Primary),
	Black(BackgroundColorBehavior.Color.Black, ColorBehavior.Color.White, BorderBehavior.Color.Black),
	White(BackgroundColorBehavior.Color.White, ColorBehavior.Color.Black, BorderBehavior.Color.White),
	Transparent(BackgroundColorBehavior.Color.Transparent, ColorBehavior.Color.Body, null);

	
	private BackgroundColorBehavior.Color backgroundColor;
	private ColorBehavior.Color textColor;
	private BorderBehavior.Color borderColor;
	
	private PanelType(BackgroundColorBehavior.Color backgroundColor, ColorBehavior.Color textColor, Color borderColor) {
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
		this.borderColor = borderColor;
	}

	/**
	 * @return the backgroundColor
	 */
	public BackgroundColorBehavior.Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @return the textColor
	 */
	public ColorBehavior.Color getTextColor() {
		return textColor;
	}

	/**
	 * @return the borderColor
	 */
	public BorderBehavior.Color getBorderColor() {
		return borderColor;
	}
	
}