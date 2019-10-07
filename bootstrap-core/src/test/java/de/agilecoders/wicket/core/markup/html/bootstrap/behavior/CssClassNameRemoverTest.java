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

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.IntegrationTest;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests for CssClassNameRemover
 */
@IntegrationTest
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
    void removeCssClasses() {
        // add 3 classes
        component.add(new CssClassNameAppender("classX classY classZ"));

        // then remove one of them
        component.add(new CssClassNameRemover("classZ"));

        tester().startComponentInPage(component);
        TagTester tester = tester().getTagByWicketId("id");

        // make sure the removed one is not here anymore
        assertThat("classX classY", is(equalTo(tester.getAttribute("class"))));
    }

    /**
     * Tests removing a value for tag that has no 'class' attribute at all.
     */
    @Test
    void removeNonExistingCssClasses() {
        // the component has no CSS classes

        // try to remove a class
        component.add(new CssClassNameRemover("classZ"));

        tester().startComponentInPage(component);
        TagTester tester = tester().getTagByWicketId("id");

        // make sure the removed one is not here at all
        assertNull(tester.getAttribute("class"), "The removed CSS class should not be set if the old value of 'class' is null");
    }
}
