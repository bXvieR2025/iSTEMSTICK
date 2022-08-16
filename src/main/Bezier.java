package main;

public class Bezier {
	Dragable p1 = new Dragable(new Vector2(Controller.Size-50,Controller.Size-50));
	Dragable p2 = new Dragable(new Vector2(Controller.Size-51,Controller.Size-200));
	Dragable p3 = new Dragable(new Vector2(50,Controller.Size-200));
	Dragable p4 = new Dragable(new Vector2(50,Controller.Size-50));
	public Bezier(boolean floor) {
		if(floor) {
			p1 = new Dragable(new Vector2(Controller.Size-100,Controller.Size-400));
			p2 = new Dragable(new Vector2(Controller.Size-101,Controller.Size-300));
			p3 = new Dragable(new Vector2(100,Controller.Size-300));
			p4 = new Dragable(new Vector2(100,Controller.Size-400));
		}
	}
	public Vector2 Coord(double t) {
		double x = 
				Math.pow(1-t,3) * p1.vector.x
				+3 * t * Math.pow(1-t,2) * p2.vector.x
				+3 * (1-t) * Math.pow(t,2) * p3.vector.x
				+Math.pow(t,3) * p4.vector.x;
		double y = 
				Math.pow(1-t,3) * p1.vector.y
				+3 * t * Math.pow(1-t,2) * p2.vector.y
				+3 * (1-t) * Math.pow(t,2) * p3.vector.y
				+Math.pow(t,3) * p4.vector.y;
		return new Vector2(x,y);
					
					
	}
}
