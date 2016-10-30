package com.albertoteloko.utils.selector;


/**
 * This enum represent the selector options.
 * 
 */
public enum SelectorOptions {
	NO_OPTIONS, NO_MOVE, OPTIONAL, INCLUDE_DELIMITERS, IGNORE_CASE;

	public boolean isInclude(SelectorOptions... options) {
		for (SelectorOptions selectorOptions : options) {
			if (selectorOptions == this) {
				return true;
			}
		}
		return false;
	}
}
