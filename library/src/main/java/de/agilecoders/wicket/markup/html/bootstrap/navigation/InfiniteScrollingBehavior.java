package de.agilecoders.wicket.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * TODO: document
 *
 * @link http://www.infinite-scroll.com/
 * @author miha
 * @version 1.0
 */
public class InfiniteScrollingBehavior extends BootstrapBaseBehavior {

    private static final ResourceReference JS = new JQueryPluginResourceReference(InfiniteScrollingBehavior.class, "js/jquery.infinitescroll.js");

    private String navSelector;
    private String nextSelector;
    private String itemSelector;
    private boolean autoScroll = true;

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render(JavaScriptHeaderItem.forReference(JS));
        headerResponse.render(OnDomReadyHeaderItem.forScript(createScript(component)));
    }

    protected CharSequence createScript(Component component) {
        CharSequence script = "$('#" + component.getMarkupId() + "').infinitescroll({"
                              + "  navSelector  : \"#" + navSelector + "\","    // selector for the paged navigation (it will be hidden)
                              + "  nextSelector : \"#" + nextSelector + "\","   // selector for the NEXT link (to page 2)
                              + "  itemSelector : \"" + itemSelector + "\","    // selector for all items you'll retrieve
                              + "  localMode    : true,"
                              + "  debug        : false,"                       // enable debug messaging ( to console.log )
                              + "  animate      : true,"                        // boolean, if the page will do an animated scroll when new content loads
                              + "  errorCallback: function(){}"                 // called when a requested page 404's or when there is no more content
                              + "},function(arrayOfNewElems){});";              // optional callback when new content is successfully loaded in.

        if (!autoScroll) {
            script = script + "$(window).unbind('.infscr');";
            script = script + "$('a#next').click(function() {"
                     + "            $(document).trigger('retrieve.infscr');"
                     + "            return false;"
                     + "       });";
        }

        return script;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

    public InfiniteScrollingBehavior setNextSelector(Component component) {
        component.setOutputMarkupId(true);
        nextSelector = component.getMarkupId();
        return this;
    }

    public InfiniteScrollingBehavior setNavSelector(Component component) {
        component.setOutputMarkupId(true);
        navSelector = component.getMarkupId();
        return this;
    }

    public InfiniteScrollingBehavior setItemSelector(Component component, String selector) {
        component.setOutputMarkupId(true);
        itemSelector = "#" + component.getMarkupId() + " " + selector;
        return this;
    }

    public InfiniteScrollingBehavior useAutoScroll(boolean autoScroll) {
        this.autoScroll = autoScroll;
        return this;
    }

    /*
  loadingImg   : "/img/loading.gif",
                 // loading image.
                 // default: "http://www.infinite-scroll.com/loading.gif"

  loadingText  : "Loading new posts...",
                 // text accompanying loading image
                 // default: "<em>Loading the next set of posts...</em>"

  extraScrollPx: 50,
                 // number of additonal pixels that the page will scroll
                 // (in addition to the height of the loading div)
                 // animate must be true for this to matter
                 // default: 150

  donetext     : "I think we've hit the end, Jim" ,
                 // text displayed when all items have been retrieved
                 // default: "<em>Congratulations, you've reached the end of the internet.</em>"

  bufferPx     : 40,
                 // increase this number if you want infscroll to fire quicker
                 // (a high number means a user will not see the loading message)
                 // new in 1.2
                 // default: 40

  localMode    : true
                 // enable an overflow:auto box to have the same functionality
                 // demo: http://paulirish.com/demo/infscr
                 // instead of watching the entire window scrolling the element this plugin
                 //   was called on will be watched
                 // new in 1.2
                 // default: false


    },function(arrayOfNewElems){

     //

     // keyword `this` will refer to the new DOM content that was just added.
     // as of 1.5, `this` matches the element you called the plugin on (e.g. #content)
     //                   all the new elements that were found are passed in as an array

});
    *
    * */
}
