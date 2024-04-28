package vn.spring.rnd.Common.Utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class DateUtils {
  // String pattern = "yyyy-MM-dd HH:mm:ss";
  public static String format(Date date, String pattern) {
    Timestamp ts = new Timestamp(date.getTime());
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);

    return formatter.format(ts);
  }

  public static Timestamp getCurrentTimestamp() {
    LocalDateTime localDateTime = LocalDateTime.now();
    return Timestamp.valueOf(localDateTime);
  }
}
