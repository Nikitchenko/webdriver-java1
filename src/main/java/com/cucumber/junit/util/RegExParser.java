package com.cucumber.junit.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExParser {

    private RegExParser() {
    }

    protected static final String REGEX_TEMPLATE_URL = "^(\\w+)\\:\\/\\/([\\w\\-\\.]+)(\\/[\\w\\-]+)(.*)$";
    private static final Logger logger = LogManager.getLogger(RegExParser.class.getName());

    public static String getInnerURL(String wholeURL) {

        Matcher matcher = Pattern.compile(REGEX_TEMPLATE_URL).matcher(wholeURL);

        if (matcher.find()) {
            logger.debug("URL parsed: " + matcher.group(3));
            return matcher.group(3);
        }

        logger.warn("URL not parsed.");
        return "no groups of URL";
    }
}
