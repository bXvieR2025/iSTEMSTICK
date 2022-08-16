package main;

public class Vector2
{
	 public double x;
	 public double y;
	 public Vector2(double x, double y) 
	 {
		this.x = x;
	 	this.y = y;
	 }
	 public void add(Vector2 additive)
	 {
		 x += additive.x;
		 y += additive.y;
	 }
	 public void set(double x, double y) {
		 this.x = x;
		 this.y = y;
	 }
	
}
