public class DoctorScheduler
{
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			System.out.println("Correct program execution is: java DoctorScheduler filename.csv");
			System.exit(0);
		}
		CSVParser preferencesParser = new CSVParser(args[0]);
		CSVParser configParser = new CSVParser("config.csv");
		Initializer initializer = new Initializer(preferencesParser.parse(),configParser.parse());
		TabuSearch tabuSearch = new TabuSearch(initializer.generateSchedule(),initializer.parseDoctors());
		
	}
}