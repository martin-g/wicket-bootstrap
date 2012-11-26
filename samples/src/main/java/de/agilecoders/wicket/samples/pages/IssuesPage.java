package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.AbstractNavbarComponent;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarAjaxLink;
import de.agilecoders.wicket.samples.components.issues.CustomNavbarForm;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.Serializable;
import java.util.Date;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/issues")
public class IssuesPage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public IssuesPage(PageParameters parameters) {
        super(parameters);

        // issue #80
        add(new ParentNavbar("navbar-parent"),
            new SubNavbar("navbar-child"));

        // issue #88
        add(createDatePickerForm("datepicker-form"));

        // issue #-1
        add(new Navbar("navbar-form").addComponents(new AbstractNavbarComponent(Navbar.ComponentPosition.LEFT) {
            @Override
            public Component create(String markupId) {
                return new CustomNavbarForm(markupId);
            }
        }));
    }

    private DateBean dateBean = new DateBean();

    /**
     * creates a form that contains a datepicker.
     *
     * @param markupId The components markup id
     * @return new form
     */
    public Form createDatePickerForm(final String markupId) {
        Form<DateBean> form = new Form<DateBean>(markupId,
                                                 new CompoundPropertyModel<DateBean>(
                                                         new PropertyModel<DateBean>(this, "dateBean")));
        add(form);
        DateTextField dueDate = new DateTextField("dueDate");
        form.add(dueDate);

        form.add(new AjaxSubmitLink("submit", form) {
            private static final long serialVersionUID = -2647897814406807218L;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new ButtonBehavior());
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                System.out.print(dateBean);
                target.appendJavaScript("alert('DateBean.dueDate is: " + dateBean.getDueDate() + "');");
            }
        });

        return form;
    }

    public class DateBean implements Serializable {
        private static final long serialVersionUID = 7570029514918506580L;

        private Date dueDate;

        public DateBean() {
            this(new Date());
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

    @Override
    protected boolean hasNavigation() {
        return true;
    }

    private static final class SubNavbar extends Navbar {

        private final IModel<SubNavGroup> group = Model.of(SubNavGroup.NONE);

        public SubNavbar(final String componentId) {
            super(componentId);

            invert(true);
            setOutputMarkupId(true);

            addComponents(
                    new ImmutableNavbarComponent(new NavbarAjaxLink<String>(Model.of("button a")) {
                        @Override
                        public void onClick(AjaxRequestTarget target) {
                            target.appendJavaScript("alert('button A clicked');");
                        }

                        @Override
                        public boolean isVisible() {
                            return SubNavGroup.A.equals(group.getObject());
                        }
                    }, ComponentPosition.LEFT),
                    new ImmutableNavbarComponent(new NavbarAjaxLink<String>(Model.of("button b")) {
                        @Override
                        public void onClick(AjaxRequestTarget target) {
                            target.appendJavaScript("alert('button B clicked');");
                        }

                        @Override
                        public boolean isVisible() {
                            return SubNavGroup.B.equals(group.getObject());
                        }
                    }, ComponentPosition.LEFT)
            );
        }

        /**
         * @see org.apache.wicket.Component#onEvent(org.apache.wicket.event.IEvent)
         */
        @Override
        public void onEvent(IEvent<?> event) {
            super.onEvent(event);

            if (event.getPayload() instanceof ToggleSubNavbarState) {
                ToggleSubNavbarState stateEvent = (ToggleSubNavbarState) event.getPayload();

                group.setObject(stateEvent.getGroup());

                stateEvent.getTarget().add(this);
            }
        }
    }

    private static final class ParentNavbar extends Navbar {
        public ParentNavbar(final String componentId) {
            super(componentId);

            invert(false);
            setOutputMarkupId(true);

            addComponents(
                    new ImmutableNavbarComponent(new NavbarAjaxLink<String>(Model.of("button group a")) {
                        @Override
                        public void onClick(AjaxRequestTarget target) {
                            send(getPage(), Broadcast.BREADTH, new ToggleSubNavbarState(target, SubNavGroup.A));
                        }
                    }, ComponentPosition.LEFT),
                    new ImmutableNavbarComponent(new NavbarAjaxLink<String>(Model.of("button group b")) {
                        @Override
                        public void onClick(AjaxRequestTarget target) {
                            send(getPage(), Broadcast.BREADTH, new ToggleSubNavbarState(target, SubNavGroup.B));
                        }
                    }, ComponentPosition.LEFT)
            );
        }
    }

    private enum SubNavGroup {
        NONE, A, B
    }

    private static final class ToggleSubNavbarState {
        private final AjaxRequestTarget target;
        private final SubNavGroup group;

        /**
         * Constructor
         *
         * @param target
         */
        public ToggleSubNavbarState(AjaxRequestTarget target, SubNavGroup group) {
            this.target = target;
            this.group = group;
        }

        /**
         * @return nav bar button group id
         */
        public SubNavGroup getGroup() {
            return group;
        }

        /**
         * @return ajax request target
         */
        public AjaxRequestTarget getTarget() {
            return target;
        }
    }

}
