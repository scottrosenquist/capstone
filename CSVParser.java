import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.String;

public class CSVParser
{
	Scanner scanner;

	public CSVParser(String filename) 
	{
		try
		{
			scanner=new Scanner(new File(filename));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File "+filename+" was not found.");
			System.exit(0);
		}
	}

	public ArrayList<ArrayList<String>> parse()
	{
		ArrayList<ArrayList<String>> csv=new ArrayList<ArrayList<String>>();

		while(scanner.hasNext())
		{
			ArrayList<String> input = new ArrayList<String>(Arrays.asList(scanner.next().toLowerCase().split(",")));
			ArrayList<String> output = new ArrayList<String>();
			for(String eachValue:input)
			{
				output.add(eachValue.substring(0, 1).toUpperCase() + eachValue.substring(1));
			}
			csv.add(output);
		}
		scanner.close();
		return csv;	
	}
}