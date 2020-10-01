package hw4;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RobotPath {
	private PointNode[][] path;
	private Queue<Integer> obstacles = new LinkedList<>();
	private int rows;
	private int columns;
	private PointNode start;
	private PointNode dest;
	
	//graph stuff
	private LinkedList<Integer> adj[];
	
	
	
	
	public void readInput(String FileName) throws IOException
	{
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
				start = new PointNode(sn.nextInt(), sn.nextInt(), "S");
			}
			if(sn.next().equals("dest"))
			{
				dest = new PointNode(sn.nextInt(), sn.nextInt(), "D");
			}
			if(sn.next().equals("obstacles"))
			{
				while(sn.hasNext())
					obstacles.add(sn.nextInt());
			}		
		}
		System.out.println("rows: " + rows + "\n" 
				+ "columns: " + columns + "\n"
				+ "start: " + start.x + ", " + start.y + "\n"
				+ "end: " + dest.x + ", " + dest.y + "\n"
				+ obstacles.toString());
		
		
		path = new PointNode[rows][columns];
		path[start.x][start.y] = start;
		path[dest.x][dest.y] = dest;
		while(!obstacles.isEmpty())
		{
			int y = obstacles.poll();
			int x = obstacles.poll();
			path[y][x] = new PointNode(x, y, "*");
		}
		
		for(int i = 0; i < rows; i++)
			for(int k = 0; k < columns; k++)
				if(path[i][k] == null)
					path[i][k] = new PointNode(k, i, "0");
		
		
						
	}
	
	public void planShortest()
	{
	
	}
	
	public void quickPlan()
	{
		
		quickRec(start);
	}
	
	public void output() throws FileNotFoundException
	{
		File outputFile = new File("testOutput.txt");
		PrintWriter pn = new PrintWriter(outputFile);
		pn.print(pathToString(path));
		pn.close();
	}
	
	private void quickRec(PointNode point)
	{

	}
	
	private PointNode shortestValidDist(PointNode point)
	{
		
		return null;
	}
	
	private String pathToString(PointNode[][] arr)
	{
		String str = "";
		for(int i = 0; i < path.length; i++)
		{
			for(int k = 0; k < path[i].length; k++)
				str += path[i][k].getValue() + "	";
			str += "\n";
		}
		return str;
	}
	
	
}
