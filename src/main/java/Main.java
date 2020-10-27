import ru.innovat.models.VerificationToken;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        int EXPIRATION = 60 * 24;
        Date expiryDate;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, EXPIRATION);
        cal.getTime();
        expiryDate = cal.getTime();
        System.out.println();
    }
}
