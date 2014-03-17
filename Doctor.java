import java.util.ArrayList;

public class Doctor
{
	String name;
	ArrayList<Integer> preferences;

	public Doctor(String name, ArrayList<Integer> preferences)
	{
		this.name=name;
		this.preferences=preferences;
	}

	int getPreference(int day)
	{
		return preferences.get(day);
	}

	String getName()
	{
		return name;
	}
}