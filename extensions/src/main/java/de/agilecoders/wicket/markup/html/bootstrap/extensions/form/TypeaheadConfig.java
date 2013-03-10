package de.agilecoders.wicket.markup.html.bootstrap.extensions.form;

import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;

/**
 * 
 * @author sobert
 * @see http://twitter.github.com/bootstrap/javascript.html#typeahead
 * 
 */
public class TypeaheadConfig extends AbstractConfig {

	private Integer items;

	private Integer minLength;

	private String matcher;

	private String sorter;

	private String updater;

	private String highlighter;

	public String getMatcher() {
		return matcher;
	}

	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getHighlighter() {
		return highlighter;
	}

	public void setHighlighter(String highlighter) {
		this.highlighter = highlighter;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}
}
