package hw4;

import java.awt.Point;

public class PointNode extends Point {
	
	private String value;
	private boolean visited;
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
	}
	public PointNode(int x, int y, String value)
	{
		super(x, y);
		this.value = value;
		visited = false;
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
	
	public PointNode getSouth()
	{
		return new PointNode(this.x, this.y + 1);
	}
	public PointNode getNorth()
	{
		return new PointNode(this.x, this.y - 1);
	}
	public PointNode getEast()
	{
		return new PointNode(this.x + 1, this.y);
	}
	public PointNode getWest()
	{
		return new PointNode(this.x - 1, this.y);
	}
	
}
