package ga.asev.ant.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    private DateUtil() {
    }

    public static Date toDate(Object value) {
        DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        return  (Date) value;
    }

}
