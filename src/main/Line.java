package main;

public class Line {
	Vector2 A;
	Vector2 B;
	public Line(Vector2 A, Vector2 B) {
		this.A = A;
		this.B = B;
	}    
	static Vector2 Intersection(Line A, Line B)
	{
		 
	     double a1 = A.B.y - A.A.y;
	     double b1 = A.A.x - A.B.x;
	     double c1 = a1*(A.A.x) + b1*(A.A.y);
	      
	     double a2 = B.B.y - B.A.y;
	     double b2 = B.A.x - B.B.x;
	     double c2 = a2*(B.A.x)+ b2*(B.A.y);
	      
	     double determinant = a1*b2 - a2*b1;
	     
	     if (determinant == 0){
	            return null;
	     }
	     else
	     {
	        double x2 = (b2*c1 - b1*c2)/determinant;
	        double y2 = (a1*c2 - a2*c1)/determinant;
	        if (x2 == Double.MAX_VALUE && y2 == Double.MAX_VALUE){
	        	return null;
	       	}
	        if(A.A.x > A.B.x) {
	        	if(A.B.x >= x2 || x2 >= A.A.x) {
	        		return null;
	        	}
	        }else {
	        	if(A.B.x <= x2 || x2 <= A.A.x) {
	        		return null;
	        	}
	        }
	        return new Vector2(x2, y2);
	        
		}
	}

}
