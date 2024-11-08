package lesson8.utils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatters {
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

}
