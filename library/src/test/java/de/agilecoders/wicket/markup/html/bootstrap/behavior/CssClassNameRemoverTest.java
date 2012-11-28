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
package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.test.IntegrationTest;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for CssClassNameRemover
 */
@Category(IntegrationTest.class)
public class CssClassNameRemoverTest extends WicketApplicationTest {

    private Component component;

    @Override
    protected void onBefore() {
        super.onBefore();

        component = new WebMarkupContainer("id");
        component.setOutputMarkupId(true);
    }

    /**
     * Tests removing a value for a list
     */
    @Test
    public void removeCssClasses() {
        // add 3 classes
        component.add(new CssClassNameAppender("classX classY classZ"));

        // then remove one of them
        component.add(new CssClassNameRemover("classZ"));

        tester().startComponentInPage(component);
        TagTester tester = tester().getTagByWicketId("id");

        // make sure the removed one is not here anymore
        assertEquals("classX classY", tester.getAttribute("class"));
    }
}
