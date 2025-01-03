package guru.qa.lesson12.jenkins.utils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatters {
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

}
