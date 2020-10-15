package hw4;

import java.awt.Point;
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
	private PointNode[][] unModPath;
	private Queue<Integer> obstacles = new LinkedList<>();
	private int rows;
	private int columns;
	private PointNode start;
	private PointNode dest;
	private boolean stopRec = false;
	private String filename;
	
	
	
	
	public void readInput(String FileName) throws IOException
	{
		this.filename = FileName;
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
		
		
		path = new PointNode[rows][columns];
		unModPath = new PointNode[rows][columns];

		path[start.x][start.y] = start;
		path[dest.x][dest.y] = dest;
		unModPath[start.x][start.y] = start;
		unModPath[dest.x][dest.y] = dest;
		while(!obstacles.isEmpty())
		{
			int y = obstacles.poll();
			int x = obstacles.poll();
			path[y][x] = new PointNode(x, y, "*");
			unModPath[y][x] = new PointNode(x, y, "*");
			
		}
		
		for(int i = 0; i < rows; i++)
			for(int k = 0; k < columns; k++)
				if(path[i][k] == null)
				{
					path[i][k] = new PointNode(i, k, "0");
					path[i][k].setDistance(dest);
					unModPath[i][k] = new PointNode(i, k, "0");
					unModPath[i][k].setDistance(dest);
				}
		
						
	}
	
	public void planShortest() throws IOException
	{
		readInput(filename);
		bfs(start);
		backtrackLayers();

	}
	
	public void quickPlan() throws IOException
	{
		readInput(filename);
		try{quickRec(start);}
		catch(StackOverflowError e) {readInput(filename);}
		start.changeValue("S");
		
	}
	
	public void output() throws FileNotFoundException
	{
		File outputFile = new File("testOutput.txt");
		PrintWriter pn = new PrintWriter(outputFile);
		pn.print(pathToString(path));
		System.out.println(pathToString(path));
		pn.close();
	}
	
	private void quickRec(PointNode point)
	{
		if(point.equals(dest) || stopRec)
			return;

		PointNode xy = shortestValidDist(point);	
		quickRec(xy);
	}
	
	private PointNode shortestValidDist(PointNode point)
	{
		PointNode[] arr = new PointNode[4]; //{n, e, s, w}
		//North
		if(point.x > 0) {
			arr[0] = path[point.x - 1][point.y];
			path[point.x - 1][point.y].setDirection("n");
			path[point.x - 1][point.y].visit();
		}
		//East
		if(point.y < path[0].length) {
			arr[1] = path[point.x][point.y + 1];
			path[point.x][point.y + 1].setDirection("e");
			path[point.x][point.y + 1].visit();
		}
		//South
		if(point.x < path.length) {
			arr[2] = path[point.x + 1][point.y];
			path[point.x + 1][point.y].setDirection("s");
			path[point.x + 1][point.y].visit();
		}
		//West
		if(point.y > 0) {
			arr[3] = path[point.x][point.y - 1];
			path[point.x][point.y - 1].setDirection("w");
			path[point.x][point.y - 1].visit();
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
		System.out.println(first.x +", "+first.y);
		point.changeValue(first.getDirection());
		return first;
	}
	
	private void bfs(PointNode start)
	{
		Queue<PointNode> queue = new LinkedList<PointNode>();
		queue.add(start);
		start.visit();
		start.setLayer(0);
		PointNode n = null;
		PointNode s = null;
		PointNode e = null;
		PointNode w = null;
		
		while(!queue.isEmpty())
		{
			PointNode v = queue.poll();
			
			if(v.y < path[0].length-1) 
				e = path[v.x][v.y + 1];
			if(v.y > 0)
				w = path[v.x][v.y - 1];
			if(v.x < path.length-1)
				s = path[v.x + 1][v.y];
			if(v.x > 0)
				n = path[v.x - 1][v.y];
			
			//south
			if(v.x < path.length-1 && !s.getValue().equals("*") && !s.isVisited())
			{
				queue.add(s);
				s.setParent(v);
				s.visit();
				s.setLayer(s.getParent().getLayer() + 1);
			
				
			}
			//north
			if(v.x > 0 && !n.getValue().equals("*") && !n.isVisited())
			{
				queue.add(n);
				n.setParent(v);;
				n.visit();
				n.setLayer(n.getParent().getLayer() + 1);
				
			}
			//east
			if(v.y < path[0].length-1 && !e.getValue().equals("*") && !e.isVisited())
			{
				queue.add(e);
				e.setParent(v);
				e.visit();
				e.setLayer(e.getParent().getLayer() + 1);
				
			}
			//west
			if(v.y > 0 && !w.getValue().equals("*") && !w.isVisited())
			{
				queue.add(w);
				w.setParent(v);
				w.visit();
				w.setLayer(w.getParent().getLayer() + 1);	
			}
			
			if(v.getValue().equals("D"))
				break;
			
		}
		if(!dest.isVisited())
			path = unModPath.clone();
		
		

	}
	
	private void backtrackLayers()
	{
		Queue<PointNode> queue = new LinkedList<PointNode>();
		queue.add(dest);
		start.visit();
		
		PointNode n = null;
		PointNode s = null;
		PointNode e = null;
		PointNode w = null;
		while(!queue.isEmpty())
		{
			PointNode v = queue.poll();
			if(v.equals(start))
				break;
			if(v.y < path[0].length-1) 
				e = path[v.x][v.y + 1];
			if(v.y > 0)
				w = path[v.x][v.y - 1];
			if(v.x < path.length-1)
				s = path[v.x + 1][v.y];
			if(v.x > 0)
				n = path[v.x - 1][v.y];
			
			//south
			if(v.x < path.length-1 && s.getLayer() == v.getLayer() - 1)
			{
				queue.add(s);
				s.addValue("n");
			}
			//north
			if(v.x > 0 && n.getLayer() == v.getLayer() - 1)
			{
				queue.add(n);
				n.addValue("s");
			}
			//east
			if(v.y < path[0].length-1 && e.getLayer() == v.getLayer() - 1)
			{
				queue.add(e);
				e.addValue("w");
			}
			//west
			if(v.y > 0 && w.getLayer() == v.getLayer() - 1)
			{
				queue.add(w);
				w.addValue("e");
				
			}
			
		}
		start.changeValue("S");
			
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
	
	public class PointNode extends Point {
		
		private String value;
		private boolean visited;
		private String direction;
		private double distanceFromDest;
		private int layer;
		private PointNode parent;
		private String n = "";
		private String s = "";
		private String e = "";
		private String w = "";
				
		//Constructors
		public PointNode()
		{
			super();
		}
		public PointNode(int x, int y)
		{
			super(x, y);
			this.value = "0";
			visited = false;
			layer = -1;//not assigned a layer yet
		}
		public PointNode(int x, int y, String value)
		{
			super(x, y);
			this.value = value;
			visited = false;
			layer = -1;//not assigned a layer yet
		}
		
		//Methods
		public double distanceTo(PointNode dest)
		{
			int y = this.y - dest.y;
			int x = this.x - dest.x;
			double xsq = Math.pow(x, 2);
			double ysq = Math.pow(y, 2);
			return Math.sqrt(xsq + ysq);

		}
		public void setParent(PointNode parent)
		{
			this.parent = parent;
		}
		public PointNode getParent()
		{
			return parent;
		}
		
		public void addValue(String str)
		{
			if(str.equals("n"))
				n = "n";
			else if(str.equals("s"))
				s = "s";
			else if(str.equals("e"))
				e = "e";
			else if(str.equals("w"))
				w = "w";
			value = n + s + e + w;
		}
		
		public void setLayer(int depth)
		{
			layer = depth;
		}
		public int getLayer()
		{
			return layer;
		}
		
		public void setDistance(PointNode dest)
		{
			distanceFromDest = this.distanceTo(dest);
		}
		public double getDistanceToDest()
		{
			return distanceFromDest;
		}
		
		public void setDistanceManual(double dist)
		{
			distanceFromDest = dist;
		}
		
		
		public void setDirection(String str)
		{
			direction = str;
		}
		
		public String getDirection()
		{
			return direction;
		}
		
		public String getValue()
		{
			return value;
		}
		
		public boolean isVisited()
		{
			return visited;
		}
		
		public void visit()
		{
			visited = true;
		}
		
		public void changeValue(String str)
		{
			this.value = str;
		}
		
		
	}

	
	
}
