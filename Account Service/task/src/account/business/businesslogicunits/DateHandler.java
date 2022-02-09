package account.business.businesslogicunits;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHandler {

    private static final String PATTERN = "mm-YYYY";

    public static Date toDate(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
        format.setLenient(false);
        Date javaDate;
        try {
             javaDate = format.parse(stringDate);
        }
        catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect date");
        }
        return javaDate;
    }

    public static String parseString(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        return month + "-" + year;
    }
}
