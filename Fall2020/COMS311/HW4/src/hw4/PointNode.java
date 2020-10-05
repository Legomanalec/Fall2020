package hw4;

import java.awt.Point;

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
