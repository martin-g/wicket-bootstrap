/**
 * 
 */
package de.agilecoders.wicket.samples.pages;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.markup.html.bootstrap.form.DateTextFieldConfig;

/**
 * @author reimer
 *
 */
@SuppressWarnings("rawtypes")
public class DatePickerPropertyModelPage extends BasePage {

	private static final long serialVersionUID = -7696639596738805884L;

	private DateBean dateBean = new DateBean();

	public DatePickerPropertyModelPage(final PageParameters parameters) {
		super(parameters);

		Form<DateBean> form = new Form<DateBean>("testform",
				new CompoundPropertyModel<DateBean>(
						new PropertyModel<DateBean>(this, "dateBean")));
		add(form);
		DateTextField dueDate = new DateTextField("dueDate",
				new DateTextFieldConfig());
		form.add(dueDate);
		
		form.add(new AjaxSubmitLink("submit", form) {

			private static final long serialVersionUID = -2647897814406807218L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				System.out.print(dateBean);
				target.appendJavaScript("alert('DateBean.dueDate is: "
						+ dateBean.getDueDate() + "');");
			}
		});
	}

	public class DateBean implements Serializable {

		private static final long serialVersionUID = 7570029514918506580L;

		private Date dueDate;

		public DateBean() {
		}

		public DateBean(Date dueDate) {
			this.dueDate = dueDate;
		}

		public Date getDueDate() {
			return dueDate;
		}

		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "DateBean [dueDate=" + dueDate + "]";
		}
	}
}
