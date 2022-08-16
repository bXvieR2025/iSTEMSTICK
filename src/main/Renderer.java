package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Renderer {
	public static void render(Graphics g){
				
		int size = 4;
		Graphics2D g2d = (Graphics2D) g;
		
		int stroke = 6;
		g2d.setColor(Color.black);
		g2d.fillRect((int)Update.ORIGIN.x-stroke/2,Update.OFFSET + Update.HANDLE_SIZE,stroke,Update.OFFSET + Update.STICK_SIZE + Update.HANDLE_SIZE);
		g2d.setColor(Color.red);
		g2d.fillRect((int)Update.ORIGIN.x-stroke/2,Update.OFFSET,stroke, Update.HANDLE_SIZE);
		g2d.fillOval((int)Update.ORIGIN.x-5, (int)Update.ORIGIN.y-5, 10,10);
		
		g2d.fillOval((int)(Update.bezA.p1.vector.x - size), (int)(Update.bezA.p1.vector.y - size),size*2,size*2);
		g2d.fillOval((int)(Update.bezA.p2.vector.x - size), (int)(Update.bezA.p2.vector.y - size),size*2,size*2);
		g2d.fillOval((int)(Update.bezA.p3.vector.x - size), (int)(Update.bezA.p3.vector.y - size),size*2,size*2);
		g2d.fillOval((int)(Update.bezA.p4.vector.x - size), (int)(Update.bezA.p4.vector.y - size),size*2,size*2);
		
		if(Update.segmentsA[0] != null) {
			for(int i = 1; i < Update.segmentsA.length; i++) {
				g.drawLine((int)Update.segmentsA[i-1].x, (int)Update.segmentsA[i-1].y, (int)Update.segmentsA[i].x, (int)Update.segmentsA[i].y);
			}
		}
		
		g2d.setColor(Color.red);
		
		g2d.fillOval((int)(Update.bezB.p1.vector.x - size), (int)(Update.bezB.p1.vector.y - size),size*2,size*2);
		g2d.fillOval((int)(Update.bezB.p2.vector.x - size), (int)(Update.bezB.p2.vector.y - size),size*2,size*2);
		g2d.fillOval((int)(Update.bezB.p3.vector.x - size), (int)(Update.bezB.p3.vector.y - size),size*2,size*2);
		g2d.fillOval((int)(Update.bezB.p4.vector.x - size), (int)(Update.bezB.p4.vector.y - size),size*2,size*2);
		
		if(Update.segmentsB[0] != null) {
			for(int i = 1; i < Update.segmentsB.length; i++) {
				g.drawLine((int)Update.segmentsB[i-1].x, (int)Update.segmentsB[i-1].y, (int)Update.segmentsB[i].x, (int)Update.segmentsB[i].y);
			}
		}

		g2d.setColor(Color.black);

		int i = 0;
		for(Vector2 vec : Update.rayHit) {
			if(vec != null) {
				g2d.drawLine((int)vec.x, (int)vec.y,(int)Update.rays[i].A.x, (int)Update.rays[i].A.y);
			}i++;
		}
		i = 0;
		if(Update.outlier) {g2d.setColor(Color.red);}
		else {g2d.setColor(Color.black);}
		for(Vector2 vec : Update.rayHit) {
			if(vec != null) {
				g2d.fillOval((int)(vec.x - size/2), (int)(vec.y - size/2),size,size);
			}i++;
		}
		g2d.setStroke(new BasicStroke(1));
		g2d.drawArc((int)Update.ORIGIN.x-Update.ORIGIN_RADIUS, (int)Update.ORIGIN.y-Update.ORIGIN_RADIUS, Update.ORIGIN_RADIUS*2, Update.ORIGIN_RADIUS*2, (int)(270-(Update.COVERAGE_RADIANS-Update.OFFEST_RADIANS/2)*(180/Math.PI)), (int)((Update.COVERAGE_RADIANS)*(360/Math.PI)));
		g2d.setStroke(new BasicStroke(1));

	}
}
