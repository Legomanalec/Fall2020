package hw4;

import java.io.IOException;

public class RobotPathMain {
	public static void main(String[] args) throws IOException
	{
		RobotPath test = new RobotPath();
		test.readInput("Grid.txt");
				
		System.out.println("\n planShortest:\n");
//		test.quickPlan();
		test.output();
//		
//		System.out.println("\n quickPlan:\n");
//		test.quickPlan();
//		test.output();
	}
}
