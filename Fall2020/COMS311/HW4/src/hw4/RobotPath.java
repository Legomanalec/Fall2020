package hw4;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RobotPath {
	private String[][] path;
	public void readInput(String FileName) throws IOException
	{
		int rows = 0;
		int columns = 0;
		int[] startDest = new int[4]; //first element = tuple of start coords, second element = tuple of end coords.
		Queue<Integer> obstacles = new LinkedList<>();
		File file = new File(FileName);
		Scanner sn = new Scanner(file);
		
		while(sn.hasNext())
		{
			if(sn.next().equals("nrows"))
				rows = sn.nextInt();
			if(sn.next().equals("ncols"))
				columns = sn.nextInt();
			if(sn.next().equals("start"))
			{
				startDest[0] = sn.nextInt();
				startDest[1] = sn.nextInt();
			}
			if(sn.next().equals("dest"))
			{
				startDest[2] = sn.nextInt();
				startDest[3] = sn.nextInt();
			}
			if(sn.next().equals("obstacles"))
			{
				while(sn.hasNext())
					obstacles.add(sn.nextInt());
			}		
		}
		System.out.println("rows: " + rows + "\n" 
				+ "columns: " + columns + "\n"
				+ "start: " + startDest[0] + ", " + startDest[1] + "\n"
				+ "end: " + startDest[2] + ", " + startDest[3] + "\n"
				+ obstacles.toString());
		
		
		path = new String[rows][columns];
		path[startDest[0]][startDest[1]] = "S";
		path[startDest[2]][startDest[3]] = "D";
		while(!obstacles.isEmpty())
		{
			path[obstacles.poll()][obstacles.poll()] = "*";
		}
		
		for(int i = 0; i < rows; i++)
			for(int k = 0; k < columns; k++)
				if(path[i][k] == null)
					path[i][k] = "0";
		
		
		File outputFile = new File("testOutput.txt");
		PrintWriter pn = new PrintWriter(outputFile);
		pn.print(pathToString(path));
		pn.close();
						
	}
	
	public void planShortest()
	{
		
	}
	
	public void quickPlan()
	{
		
	}
	
	public void output()
	{
		
	}
	
	
	
	private String pathToString(String[][] arr)
	{
		String str = "";
		for(int i = 0; i < path.length; i++)
		{
			for(int k = 0; k < path[i].length; k++)
				str += path[i][k] + "	";
			str += "\n";
		}
		return str;
	}
}
