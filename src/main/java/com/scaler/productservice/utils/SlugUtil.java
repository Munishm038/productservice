package com.scaler.productservice.utils;

import java.text.Normalizer;
import  java.util.*;
import java.util.regex.Pattern;

public class SlugUtil {
    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String createSlug(String input) {
        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        String starter = slug.startsWith("/") ? "" : "/";
        return starter + slug.toLowerCase(Locale.ENGLISH);
    }

}
