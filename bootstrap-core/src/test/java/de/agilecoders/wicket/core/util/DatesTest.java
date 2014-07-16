package de.agilecoders.wicket.core.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.agilecoders.wicket.core.test.TestCategory;

/**
 * Tests the {@link Dates} class
 * 
 * @author miha
 */
@Category(TestCategory.UnitTest.class)
public class DatesTest {

	@Test
	public void toJavaScriptWorks() {

		assertThat(Dates.toJavaScriptDateFormat("M"), is(equalTo("m")));
		assertThat(Dates.toJavaScriptDateFormat(".M"), is(equalTo(".m")));
		assertThat(Dates.toJavaScriptDateFormat("M."), is(equalTo("m.")));
		assertThat(Dates.toJavaScriptDateFormat(".M."), is(equalTo(".m.")));

		assertThat(Dates.toJavaScriptDateFormat("MM"), is(equalTo("m")));
		assertThat(Dates.toJavaScriptDateFormat(".MM"), is(equalTo(".m")));
		assertThat(Dates.toJavaScriptDateFormat("MM."), is(equalTo("m.")));
		assertThat(Dates.toJavaScriptDateFormat(".MM."), is(equalTo(".m.")));

		assertThat(Dates.toJavaScriptDateFormat("MMM"), is(equalTo("M")));
		assertThat(Dates.toJavaScriptDateFormat(".MMM"), is(equalTo(".M")));
		assertThat(Dates.toJavaScriptDateFormat("MMM."), is(equalTo("M.")));
		assertThat(Dates.toJavaScriptDateFormat(".MMM."), is(equalTo(".M.")));

		assertThat(Dates.toJavaScriptDateFormat("MMMM"), is(equalTo("MM")));
		assertThat(Dates.toJavaScriptDateFormat(".MMMM"), is(equalTo(".MM")));
		assertThat(Dates.toJavaScriptDateFormat("MMMM."), is(equalTo("MM.")));
		assertThat(Dates.toJavaScriptDateFormat(".MMMM."), is(equalTo(".MM.")));

		assertThat(Dates.toJavaScriptDateFormat("d"), is(equalTo("d")));
		assertThat(Dates.toJavaScriptDateFormat(".d"), is(equalTo(".d")));
		assertThat(Dates.toJavaScriptDateFormat("d."), is(equalTo("d.")));
		assertThat(Dates.toJavaScriptDateFormat(".d."), is(equalTo(".d.")));

		assertThat(Dates.toJavaScriptDateFormat("dd"), is(equalTo("d")));
		assertThat(Dates.toJavaScriptDateFormat(".dd"), is(equalTo(".d")));
		assertThat(Dates.toJavaScriptDateFormat("dd."), is(equalTo("d.")));
		assertThat(Dates.toJavaScriptDateFormat(".dd."), is(equalTo(".d.")));

		assertThat(Dates.toJavaScriptDateFormat("E"), is(equalTo("D")));
		assertThat(Dates.toJavaScriptDateFormat(".E"), is(equalTo(".D")));
		assertThat(Dates.toJavaScriptDateFormat("E."), is(equalTo("D.")));
		assertThat(Dates.toJavaScriptDateFormat(".E."), is(equalTo(".D.")));

		assertThat(Dates.toJavaScriptDateFormat("EE"), is(equalTo("D")));
		assertThat(Dates.toJavaScriptDateFormat(".EE"), is(equalTo(".D")));
		assertThat(Dates.toJavaScriptDateFormat("EE."), is(equalTo("D.")));
		assertThat(Dates.toJavaScriptDateFormat(".EE."), is(equalTo(".D.")));

		assertThat(Dates.toJavaScriptDateFormat("EEE"), is(equalTo("D")));
		assertThat(Dates.toJavaScriptDateFormat(".EEE"), is(equalTo(".D")));
		assertThat(Dates.toJavaScriptDateFormat("EEE."), is(equalTo("D.")));
		assertThat(Dates.toJavaScriptDateFormat(".EEE."), is(equalTo(".D.")));

		assertThat(Dates.toJavaScriptDateFormat("EEEE"), is(equalTo("DD")));
		assertThat(Dates.toJavaScriptDateFormat(".EEEE"), is(equalTo(".DD")));
		assertThat(Dates.toJavaScriptDateFormat("EEEE."), is(equalTo("DD.")));
		assertThat(Dates.toJavaScriptDateFormat(".EEEE."), is(equalTo(".DD.")));
	}

	@Test
	public void toJavaWorks() {

		assertThat(Dates.toJavaDateFormat("m"), is(equalTo("MM")));
		assertThat(Dates.toJavaDateFormat(".m"), is(equalTo(".MM")));
		assertThat(Dates.toJavaDateFormat("m."), is(equalTo("MM.")));
		assertThat(Dates.toJavaDateFormat(".m."), is(equalTo(".MM.")));

		assertThat(Dates.toJavaDateFormat("M"), is(equalTo("MMM")));
		assertThat(Dates.toJavaDateFormat(".M"), is(equalTo(".MMM")));
		assertThat(Dates.toJavaDateFormat("M."), is(equalTo("MMM.")));
		assertThat(Dates.toJavaDateFormat(".M."), is(equalTo(".MMM.")));

		assertThat(Dates.toJavaDateFormat("MM"), is(equalTo("MMMM")));
		assertThat(Dates.toJavaDateFormat(".MM"), is(equalTo(".MMMM")));
		assertThat(Dates.toJavaDateFormat("MM."), is(equalTo("MMMM.")));
		assertThat(Dates.toJavaDateFormat(".MM."), is(equalTo(".MMMM.")));

		assertThat(Dates.toJavaDateFormat("d"), is(equalTo("dd")));
		assertThat(Dates.toJavaDateFormat(".d"), is(equalTo(".dd")));
		assertThat(Dates.toJavaDateFormat("d."), is(equalTo("dd.")));
		assertThat(Dates.toJavaDateFormat(".d."), is(equalTo(".dd.")));

		assertThat(Dates.toJavaDateFormat("D"), is(equalTo("EEE")));
		assertThat(Dates.toJavaDateFormat(".D"), is(equalTo(".EEE")));
		assertThat(Dates.toJavaDateFormat("D."), is(equalTo("EEE.")));
		assertThat(Dates.toJavaDateFormat(".D."), is(equalTo(".EEE.")));

		assertThat(Dates.toJavaDateFormat("DD"), is(equalTo("EEEE")));
		assertThat(Dates.toJavaDateFormat(".DD"), is(equalTo(".EEEE")));
		assertThat(Dates.toJavaDateFormat("DD."), is(equalTo("EEEE.")));
		assertThat(Dates.toJavaDateFormat(".DD."), is(equalTo(".EEEE.")));
	}
}
