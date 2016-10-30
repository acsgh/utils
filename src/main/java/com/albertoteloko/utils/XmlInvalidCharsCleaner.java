package com.albertoteloko.utils;

/**
 * Strips invalid Unicode characters in XML from a String
 */

public class XmlInvalidCharsCleaner {

    private static final String INVALID_CHARACTERS_IN_XML = "[^"
               + "\u0001-\uD7FF"
               + "\uE000-\uFFFD"
               + "\ud800\udc00-\udbff\udfff"
               + "]+";

    public String clean(String source) {
        return source.replaceAll(INVALID_CHARACTERS_IN_XML, "");
    }
}