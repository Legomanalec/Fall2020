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
//		if(point.equals(dest))
//			return;
		PointNode xy = shortestValidDist(start);
		shortestValidDist(xy);
		
		//quickRec(xy);
	}
	
	private PointNode shortestValidDist(PointNode point)
	{
		//map to hold the distances and points to find the shortest valid option
		HashMap<Double, PointNode> shortestMap = new HashMap<Double, PointNode>();
		double n = -1;
		double s = -1;
		double e = -1;
		double w = -1;

		//north
		if(point.y > 0 && path[point.y - 1][point.x].getValue().equals("0"))
		{
			n = path[point.y - 1][point.x].distanceTo(dest);
			path[point.y - 1][point.x].setDirection("n");
			shortestMap.put(n, path[point.y - 1][point.x]);
			
		}
		
		//south
		if(point.y > 0 && path[point.y + 1][point.x].getValue().equals("0"))
		{
			s = path[point.y + 1][point.x].distanceTo(dest);
			path[point.y + 1][point.x].setDirection("s");
			shortestMap.put(s, path[point.y + 1][point.x]);
			
		}
		
		//east
		if(point.y > 0 && path[point.y][point.x + 1].getValue().equals("0"))
		{
			e = path[point.y][point.x + 1].distanceTo(dest);
			path[point.y][point.x + 1].setDirection("e");
			shortestMap.put(e, path[point.y][point.x + 1]);
			
		}	
		
		//west
		if(point.y > 0 && path[point.y][point.x - 1].getValue().equals("0"))
		{
			w = path[point.y][point.x - 1].distanceTo(dest);
			path[point.y][point.x - 1].setDirection("w");
			shortestMap.put(w, path[point.y][point.x - 1]);
		}

		
		//find the smallest
		double[] shortList = {n, s, e, w};
		double[] droppedNeg = new double[4];
		//make sure all non -1 elements are on the left 
		int k = 0;
		for(int i = 0; i < 4; i++)
			if(shortList[i] != -1)
				droppedNeg[k++] = shortList[i];
			
		double first = shortList[0];
		for(int i = 1; i < shortList.length; i++)
		{
			if(droppedNeg[i] < first && droppedNeg[i] != 0)
				first = shortList[i];			
		}
		
		point.changeValue(shortestMap.get(first).getDirection());
		return shortestMap.get(first);
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
