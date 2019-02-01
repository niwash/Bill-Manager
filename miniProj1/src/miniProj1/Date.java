package miniProj1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {

	 private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	public static void main(String[] args) {
	
		  LocalDate localDate = LocalDate.now();
	        System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));

	}

}
