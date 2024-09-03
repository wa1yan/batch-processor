package dev.waiyanhtet.batch_processor.utility;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MapperUtils {

    private static final DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate convertLocalDate(String dateString) {
        try {
            return LocalDate.parse(dateString, isoFormatter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
