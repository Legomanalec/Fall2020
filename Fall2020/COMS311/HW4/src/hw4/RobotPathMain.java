package hw4;

import java.io.IOException;

public class RobotPathMain {
	public static void main(String[] args) throws IOException
	{
		RobotPath rPath = new RobotPath();
		rPath.readInput(args[0]);
		
		System.out.println("\n planShortest:\n");
		rPath.planShortest();
		rPath.output();
		
		System.out.println("\n quickPlan:\n");
		rPath.quickPlan();
		rPath.output();
	}
}
