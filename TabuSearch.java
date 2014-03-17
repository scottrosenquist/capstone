import java.util.ArrayList;

public class TabuSearch
{
	Schedule schedule;
	//Schedule schedule2;
	ArrayList<Doctor> doctors;
	public TabuSearch(Schedule schedule,ArrayList<Doctor> doctors)
	{
		this.schedule=schedule;
		//schedule2=schedule;
		this.doctors=doctors;
	}

	void performTabuSearchOptimization(int iterations)
	{
		Schedule bestSchedule = new Schedule(schedule);
	}

	void getBestTabuShift()
	{

	}
}