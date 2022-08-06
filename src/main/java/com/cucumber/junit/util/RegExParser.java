package com.cucumber.junit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExParser {

    private RegExParser() {
    }
    public static final String REGEX_TEMPLATE_URL = "^(\\w+)\\:\\/\\/([\\w\\-\\.]+)(\\/[\\w\\-]+)(.*)$";

    public static String getInnerURL(String wholeURL) {

        Matcher matcher = Pattern.compile(REGEX_TEMPLATE_URL).matcher(wholeURL);

        if (matcher.find()) {
            return matcher.group(3);
        }

        return "no groups of URL";
    }
}
