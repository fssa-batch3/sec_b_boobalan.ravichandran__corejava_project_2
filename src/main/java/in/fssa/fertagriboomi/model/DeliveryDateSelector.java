package in.fssa.fertagriboomi.model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DeliveryDateSelector {

    public static List<Date> getDeliveryDateOptions(Date deliveryDate) {
        List<Date> deliveryDateOptions = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());  // Set the calendar to the current date

        // Calculate the maximum date as deliveryDate + 3 days
        Calendar maxDate = Calendar.getInstance();
        maxDate.setTime(deliveryDate);
        maxDate.add(Calendar.DAY_OF_YEAR, 3);

        while (calendar.before(maxDate)) {
            deliveryDateOptions.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return deliveryDateOptions;
    }

    public static List<java.sql.Date> convertDatesToSqlDates(List<Date> dates) {
        List<java.sql.Date> sqlDates = new ArrayList<>();

        for (Date date : dates) {
            sqlDates.add(new java.sql.Date(date.getTime()));
        }

        return sqlDates;
    }
}
