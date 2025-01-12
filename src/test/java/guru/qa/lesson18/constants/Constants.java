package guru.qa.lesson18.constants;

import java.util.Map;

public class Constants {
        public final static String URI_SERVER = "https://demoqa.com";
        public final static String PROFILE_URL = "/profile";
        public final static String WRONG_ISBN = "97814493650381449365035";
        public final static int BAD_REQUEST = 400;
        public final static int UNAUTHORIZED = 401;
        public final static String ICON_URL = "/favicon.ico";
        public final static int ERROR_CODE_BAD_REQUEST = 1206;
        public final static int ERROR_CODE_UNAUTHORIZED = 1200;
        public final static String ERROR_MESSAGE_BAD_REQUEST  = "ISBN supplied is not available in User's Collection!";
        public final static String ERROR_MESSAGE_UNAUTHORIZED = "User not authorized!";

        public static final Map<String, String> BOOK_JAVASCRIPT =
                Map.of("title", "Speaking JavaScript", "isbn", "9781449365035");

        public static final Map<String, String> BOOK_GIT =
                Map.of("title", "Git Pocket Guide", "isbn", "9781449325862");

        public static final Map<String, String> BOOK_JS =
                Map.of("title", "You Don't Know JS", "isbn", "9781491904244");
}
