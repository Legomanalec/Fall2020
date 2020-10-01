package hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
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
	
	private Graph graph;
	
	
	
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
		graph = new Graph();
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
				{
					path[i][k] = new PointNode(i, k, "0");
					path[i][k].setDistance(dest);
				}
		
		
						
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
		if(point.equals(dest))
			return;

		PointNode xy = shortestValidDist(point);	
		quickRec(xy);
//		PointNode xy = shortestValidDist(start);
//		for(int i = 0; i < 13; i++)
//			xy = shortestValidDist(xy);
		
	}
	
	private PointNode shortestValidDist(PointNode point)
	{
		PointNode[] arr = new PointNode[4]; //{n, e, s, w}
		//North
		if(point.x > 0) {
			arr[0] = path[point.x - 1][point.y];
			path[point.x - 1][point.y].setDirection("n");
		}
		//East
		if(point.y < path[0].length) {
			arr[1] = path[point.x][point.y + 1];
			path[point.x][point.y + 1].setDirection("e");
		}
		//South
		if(point.x < path.length) {
			arr[2] = path[point.x + 1][point.y];
			path[point.x + 1][point.y].setDirection("s");
		}
		//West
		if(point.y > 0) {
			arr[3] = path[point.x][point.y - 1];
			path[point.x][point.y - 1].setDirection("w");
		}
		
		PointNode first = new PointNode();
		first.setDistanceManual(Double.MAX_VALUE);
		for(int i = 0; i < 4; i++)
		{
			if(arr[i] != null && (arr[i].getValue().equals("0") || arr[i].getValue().equals("D")) && arr[i].getDistanceToDest() < first.getDistanceToDest())
			{	
				first = arr[i];
			}
			//checks if they are equal and compares x
			else if(arr[i] != null && arr[i].getDistanceToDest() == first.getDistanceToDest())
			{
				if(arr[i].x < first.x)
					first = arr[i];
			}
			//if they are still equal after checking x, it will check y
			else if(arr[i] != null && arr[i].getDistanceToDest() == first.getDistanceToDest())
			{
				if(arr[i].y < first.y)
					first = arr[i];
			}
				
		}
		
		System.out.println(first.x + ", " + first.y);
		point.changeValue(first.getDirection());
		return first;
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
