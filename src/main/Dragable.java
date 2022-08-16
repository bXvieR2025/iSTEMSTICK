package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Dragable implements MouseListener, MouseMotionListener{
	Vector2 vector;
	public Dragable(Vector2 vector) {
		this.vector = vector;
		CanvasRenderer.render.addMouseListener(this);
		CanvasRenderer.render.addMouseMotionListener(this);

	}
	private Boolean dragging = false;
	public void mousePressed(MouseEvent e) {
	    if (Math.abs(vector.x - e.getX()) + Math.abs(vector.y - e.getY()) < 20) {
			dragging = true;

	    }
	}
	public void mouseReleased(MouseEvent e) {
		dragging = false;
	}

	public void mouseDragged(MouseEvent e) {
		if (dragging) {
			vector.set(e.getX(),e.getY());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
