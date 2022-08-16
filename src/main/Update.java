package main;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Update {
	
	
	public static final int SCALE = 5;
	public static final int OFFSET = 300/SCALE;
	public static final int HANDLE_SIZE = 150/SCALE;
	public static final int STICK_SIZE = 700/SCALE;
	public static final double SENSITIVITY = 0.05;
	public static final int GROUND_DISTANCE = 300/SCALE;
	public static final int ORIGIN_RADIUS = 400/SCALE;

	public static final Vector2 ORIGIN = new Vector2(Controller.Size/2,HANDLE_SIZE-ORIGIN_RADIUS+OFFSET);
	
	public static final int DEFAULT_DISTANCE = STICK_SIZE + HANDLE_SIZE + GROUND_DISTANCE;
	public static final double SENSOR_WIDTH = 21.3/SCALE;
	public static final int MAX_RADIUS = 1000/SCALE;
	public static final double COVERAGE = 360/SCALE; //Human Width
	public static final double COVERAGE_RADIANS = Math.atan(COVERAGE / 2 / DEFAULT_DISTANCE);
	public static final double OFFEST_RADIANS = Math.atan(SENSOR_WIDTH / 2 / ORIGIN_RADIUS);
	public static final int SENSORS = (int) Math.round(COVERAGE_RADIANS/OFFEST_RADIANS) * 2 - 1;
	 
	public static Bezier bezA = new Bezier(false);
	public static Bezier bezB = new Bezier(true);

	public static Line[] rays = new Line[SENSORS];
	public static double[] distances = new double[SENSORS];
	public static double[] radians = new double[SENSORS];

	public static final int SEGMENTS = 100;

	public static Vector2 segmentsA[] = new Vector2[SEGMENTS+1];
	public static Vector2 segmentsB[] = new Vector2[SEGMENTS+1];

	public static Vector2[] rayHit = new Vector2[SENSORS];
	public static Boolean outlier = false;

	public static double vibration;

	
	public static void Start(){
		for(int i = 0; i < SENSORS; i++) {
			int o = i - SENSORS/2;
			Vector2 A = new Vector2(Math.sin(OFFEST_RADIANS * o) * ORIGIN_RADIUS + ORIGIN.x,Math.cos(OFFEST_RADIANS * o) * ORIGIN_RADIUS + ORIGIN.y);
			Vector2 B = new Vector2(Math.sin(OFFEST_RADIANS * o) * MAX_RADIUS + ORIGIN.x,Math.cos(OFFEST_RADIANS * o) * MAX_RADIUS + ORIGIN.y);
			rays[i] = new Line(A,B);
			radians[i] = OFFEST_RADIANS * o;
		}
		System.out.print("Sensors:"+SENSORS +"\n");
		System.out.print("Offset:"+ OFFEST_RADIANS * (180/Math.PI));


	}
	public static void Update(){
		for(int t = 0; t <= SEGMENTS; t++) {
			segmentsA[t] = bezA.Coord((double) t / SEGMENTS);
			segmentsB[t] = bezB.Coord((double) t / SEGMENTS);
		}
		for(int a = 0; a < SENSORS; a++) {
			distances[a] = Controller.Size;
			for(int b = 1; b < segmentsA.length; b++) {
				if(Line.Intersection(new Line(segmentsA[b],segmentsA[b - 1]),rays[a]) != null) {
					Vector2 hit = Line.Intersection(new Line(segmentsA[b],segmentsA[b - 1]),rays[a]);
					double distance = Math.sqrt(Math.pow(hit.x-Update.ORIGIN.x,2) + Math.pow(hit.y-Update.ORIGIN.y,2));
					if(distance<distances[a]) {distances[a] = Math.sqrt(Math.pow(hit.x-Update.ORIGIN.x,2) + Math.pow(hit.y-Update.ORIGIN.y,2));}
				}if(Line.Intersection(new Line(segmentsB[b],segmentsB[b - 1]),rays[a]) != null) {
					Vector2 hit = Line.Intersection(new Line(segmentsB[b],segmentsB[b - 1]),rays[a]);
					double distance = Math.sqrt(Math.pow(hit.x-Update.ORIGIN.x,2) + Math.pow(hit.y-Update.ORIGIN.y,2));
					if(distance<distances[a]) {distances[a] = Math.sqrt(Math.pow(hit.x-Update.ORIGIN.x,2) + Math.pow(hit.y-Update.ORIGIN.y,2));}
				}

			}
		}
		for(int i = 0; i < SENSORS; i++) {
			double x = distances[i] * Math.sin(radians[i]) + ORIGIN.x;
			double y = distances[i] * Math.cos(radians[i]) + ORIGIN.y; 
			rayHit[i] = new Vector2(x,y);
		}
		outlier = false;
		vibration = 0;
		for(int i = 1; i < SENSORS - 2; i++) {
			double a = Math.atan2(rayHit[i].y-rayHit[i-1].y,rayHit[i].x-rayHit[i-1].x);
			double b = Math.atan2(rayHit[i+1].y-rayHit[i].y,rayHit[i+1].x-rayHit[i].x);
			double c = Math.atan2(rayHit[i+2].y-rayHit[i+1].y,rayHit[i+2].x-rayHit[i+1].x);
			double d = Math.abs((a-b)-(b-c));
			outlier = d > SENSITIVITY || outlier;
			vibration += d;
		}
		//System.out.print(vibration+"\n");
	}
}
