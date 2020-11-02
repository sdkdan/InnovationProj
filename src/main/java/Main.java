import ru.innovat.models.VerificationToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws ParseException {
        String dateInString = "2222-03-12";
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
        Date date = formatter.parse(dateInString);
        System.out.println(date);

    }
}
