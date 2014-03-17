import java.util.ArrayList;

public class Schedule
{
	ArrayList<Integer> schedule;
	public Schedule(ArrayList<Integer> schedule)
	{
		ArrayList<Integer> tempSchedule = new ArrayList<Integer>(schedule);
		this.schedule=tempSchedule;
	}

	public Schedule(Schedule schedule)
	{
		this.schedule=schedule.getSchedule();
	}

	ArrayList<Integer> getSchedule()
	{
		ArrayList<Integer> tempSchedule = new ArrayList<Integer>(schedule);
		return tempSchedule;
	}
}