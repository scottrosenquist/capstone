import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Integer;
import java.util.Collections;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class Initializer
{
	ArrayList<ArrayList<String>> preferences;
	Calendar calendar = Calendar.getInstance();
	int daysInYear;
	ArrayList<ArrayList<String>> dayAttributes;

	public Initializer(ArrayList<ArrayList<String>> preferences, ArrayList<ArrayList<String>> config)
	{
		this.preferences=preferences;
		calendar.set(Calendar.YEAR,parseYear());
		daysInYear=calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		generateDayAttributes();
	}

	public int parseYear()
	{
		int year=0;
		if(preferences.get(0).get(0).equals("Year"))
		{
			year = Integer.parseInt(preferences.get(0).get(1));
		}
		else
		{
			System.out.println("Missing marker \"Year\" at beginning of preferences file.");
			System.exit(0);
		}
		return year;
	}

	public ArrayList<Doctor> parseDoctors()
	{
		ArrayList<Doctor> doctors=new ArrayList<Doctor>();
		for(int i=0;i<countDoctors();i++)
		{
			String name = preferences.get(4*i+1).get(1);
			ArrayList<ArrayList<String>> parameters = new ArrayList<ArrayList<String>>();
			for(int j=0;j<3;j++)
			{
				parameters.add(preferences.get(4*i+2+j));
			}
			ArrayList<Integer> preferences=decodePreferences(parameters);
			doctors.add(new Doctor(name,preferences));
		}
		return doctors;
	}

	ArrayList<Integer> decodePreferences(ArrayList<ArrayList<String>> parameters)
	{
		// ArrayList<Integer> defaultPreferences = new ArrayList<Integer>(Arrays.asList(4,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,4,2,2,4,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,4,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,4,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,4,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,4,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,4,4,2,2,1,1,1));//Default 2014 preferences
		// ArrayList<Integer> preferences = new ArrayList<Integer>(defaultPreferences);
		ArrayList<Integer> preferences = new ArrayList<Integer>(Collections.nCopies(daysInYear, 2));
		for(ArrayList<String> line : parameters)
		{
			int x=0;
			if (line.get(0).equals("Like")||line.get(0).equals("Likes"))
			{
				x=1;
			}
			else if(line.get(0).equals("Dislike")||line.get(0).equals("Dislikes"))
			{
				x=4;
			}
			else if(line.get(0).equals("Unavailable"))
			{
				x=1461;
			}
			else
			{
				System.out.println("Error in preferences formatting");
				System.exit(0);
			}
			for(int i=0;i<daysInYear;i++)
			{
				if(!Collections.disjoint(line,dayAttributes.get(i)))
				{
					preferences.set(i,x);
				}
			}
		}
		return preferences;
	}

	int countDoctors()
	{
		int numberOfDoctors=0;
		for(ArrayList<String> line : preferences)
		{
			numberOfDoctors+=Collections.frequency(line,"Name");
		}
		return numberOfDoctors;
	}

	public Schedule generateSchedule()
	{
		ArrayList<Integer> temp=new ArrayList<Integer>();
		int numberOfDoctors = countDoctors();
		int j=0;
		for(int i=0;i<daysInYear;i++)
		{
			temp.add(j);
			j++;
			if(j>=numberOfDoctors)
			{
				j=0;
			}
		}
		Schedule schedule = new Schedule(temp);
		return schedule;
	}

	public void generateDayAttributes()
	{
		dayAttributes = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> holidays = new ArrayList<Integer>(Arrays.asList(1,48,108,139,182,244,286,359,360));
		for(int i=1;i<=daysInYear;i++)
		{
			ArrayList<String> temp = new ArrayList<String>();
			calendar.set(Calendar.DAY_OF_YEAR,i);
			temp.add(calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT,Locale.CANADA));
			temp.add(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.CANADA));
			temp.add(calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT,Locale.CANADA));
			temp.add(calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.CANADA));
			temp.add(calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.CANADA)+"s");
			temp.add(calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT,Locale.CANADA)+(calendar.get(Calendar.DAY_OF_MONTH)));
			temp.add(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.CANADA)+(calendar.get(Calendar.DAY_OF_MONTH)));
			if(calendar.get(Calendar.DAY_OF_WEEK)==1||calendar.get(Calendar.DAY_OF_WEEK)==7)
			{
				temp.add("Weekend");
				temp.add("Weekends");	
			}
			else
			{
				temp.add("Weekday");
				temp.add("Weekdays");
			}
			if(holidays.contains(i))
			{
				temp.add("Holiday");
				temp.add("Holidays");
			}
			dayAttributes.add(temp);
		}
	}
}