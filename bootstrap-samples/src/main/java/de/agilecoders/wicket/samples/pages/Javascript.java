package de.agilecoders.wicket.samples.pages;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.carousel.Carousel;
import de.agilecoders.wicket.core.markup.html.bootstrap.carousel.CarouselImage;
import de.agilecoders.wicket.core.markup.html.bootstrap.carousel.ICarouselImage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.PopoverBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.AjaxLazyLoadTextContentTab;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.Collapsible;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.TextContentTab;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.DataSet;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.Typeahead;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.TypeaheadConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.Bloodhound;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.LocalBloodhound;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.samples.panels.pagination.AjaxPaginationPanel;
import de.agilecoders.wicket.samples.panels.pagination.PaginationPanel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/javascript", alt = "/js")
public class Javascript extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public Javascript(PageParameters parameters) {
        super(parameters);

        Collapsible collapsible = new Collapsible("accordion42", Lists.<ITab>newArrayList(
            new TextContentTab(Model.of("Title 1"), Model.of("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.")),
            new TextContentTab(Model.of("Title 2"), Model.of("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.")),
            new TextContentTab(Model.of("Title 3"), Model.of("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.")),
            new AjaxLazyLoadTextContentTab(Model.of("Title 4"), Model.of("Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS."))),
            Model.of(2));
        add(collapsible);

        add(new Label("tooltip-top", Model.of("Tooltip on top")).add(new TooltipBehavior(Model.of("Tooltip on top"))));

        add(new BootstrapAjaxLink<Void>("popover", Buttons.Type.Danger) {

            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new PopoverBehavior(Model.of("A Title"),
                    Model.of("And here's some \"amazing\" content. It's very engaging. right?")));
            }

            @Override
            public void onClick(AjaxRequestTarget target) {
                // nothing to do.
            }
        }.setLabel(Model.of("click here")));

        // issue #89
        add(newDropDown("dropdown"));

        add(new PaginationPanel("pagingNavigator"));
        add(new AjaxPaginationPanel("ajaxPagingNavigator"));

        add(newCarousel("carousel"));

        add(newLocalTypeahead("localTypeahead"));
        add(newRemoteTypeahead("remoteTypeahead"));
    }

    /**
     * creates a new typeahead instance
     *
     * @param markupId The component id
     * @return new typeahead instance
     */
    private Typeahead<String> newLocalTypeahead(final String markupId) {
        final List<String> dataSource = Lists.newArrayList("abc", "def", "ghi");

        Bloodhound<String> bloodhound = new LocalBloodhound<>("sample", dataSource);
        TypeaheadConfig<String> config = new TypeaheadConfig<>(new DataSet<>(bloodhound));
        return new Typeahead<>(markupId, Model.of(""), config);
    }


    /**
     * creates a new typeahead instance
     *
     * @param id The component id
     * @return new typeahead instance
     */
    private Component newRemoteTypeahead(final String id) {

        final List<String> dataSource = Lists.newArrayList(
            "Alabama", "Alaska", "Arizona", "Arkansas",
            "California", "Colorado", "Connecticut",
            "Delaware", "Florida", "Georgia", "Hawaii",
            "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
            "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota",
            "Mississippi", "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey",
            "New Mexico", "New York", "North Dakota",
            "North Carolina", "Ohio", "Oklahoma", "Oregon",
            "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah",
            "Vermont", "Virginia", "Washington",
            "West Virginia", "Wisconsin", "Wyoming"
        );

        Bloodhound<String> bloodhound = new Bloodhound<String>("remote") {
            @Override
            public Iterable<String> getChoices(String input) {
                return dataSource.stream()
                        .filter(state -> state.toLowerCase().contains(input.toLowerCase()))
                        .collect(Collectors.toList());
            }

            @Override
            public String renderChoice(String choice) {
                // create the datum for the choice
                return String.format("{\"value\":\"%s\"}", choice);
            }
        };

        TypeaheadConfig<String> config = new TypeaheadConfig<>(new DataSet<>(bloodhound));

        return new Typeahead<>(id, Model.of(""), config);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(new CssResourceReference(Javascript.class, "css/typeahead-demo.css")));
    }

    /**
     * creates a new carousel image
     *
     * @param markupId The component id
     * @return new carousel component
     */
    private Component newCarousel(String markupId) {
        final List<ICarouselImage> images = Lists.<ICarouselImage>newArrayList(
            new CarouselImage("http://placehold.it/820x480", "Header 1", "Description 1"),
            new CarouselImage("http://placehold.it/820x480", "Header 2"),
            new CarouselImage("http://placehold.it/820x480")
        );

        return new Carousel(markupId, images);
    }

    private Component newDropDown(String markupId) {
        return new DropDownButton(markupId, Model.of("Dropdown (#89)"), Model.of(FontAwesomeIconType.bookmark)) {
            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                List<AbstractLink> subMenu = new ArrayList<>();
                subMenu.add(new MenuBookmarkablePageLink<Void>(Javascript.class).setLabel(Model.of("Link")));
                subMenu.add(new NavbarAjaxLink<String>("button", Model.of("Ajax Link")) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        ((DropDownButton) getParent()).appendToggleMenuScript(target);

                        target.appendJavaScript("alert('clicked');");
                    }
                });

                return subMenu;
            }
        };
    }


    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
