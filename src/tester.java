import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class tester {

	public static void main(String[] args) throws ParseException {
		java.util.Date myDate = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  // United States style of format.
		myDate = format.parse( "12/10/2009" );
		
		java.sql.Date sqlDate = new java.sql.Date( myDate.getTime() );
		System.out.println(myDate.getTime()+ "  "+myDate+ "  "+sqlDate);
		
        
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
        System.out.println(simpleDateformat.format(myDate));
 
        simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        System.out.println(simpleDateformat.format(myDate));
 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); // the day of the week in numerical format
        
        Time t = new Time(9, 11, 34);
        System.out.println(t);
        
        String myTime = "10:30";
        String nexTime ="13:30";
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date date = null;
        Date date1 = null;
        try {
            date = sdf.parse(myTime);
            date1 = sdf.parse(nexTime);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = sdf.format(date);
        String formattedTime1 = sdf.format(date1);
        System.out.println(Integer.parseInt(formattedTime.replace(":", ""))- Integer.parseInt(formattedTime1.replace(":", "")));
        System.out.println(date + formattedTime);
        System.out.println(date1 +formattedTime1);
        System.out.println(getDateDiff(date,date1));
        
	}
	
    public static long getDateDiff(Date date1, Date date2) {
    	TimeUnit timeUnit = TimeUnit.HOURS;
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

}
