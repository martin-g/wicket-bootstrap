package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarAjaxLink;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

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

        add(new ParentNavbar("navbar-parent"),
            new SubNavbar("navbar-child"));
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
