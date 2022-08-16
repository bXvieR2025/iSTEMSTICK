package main;

import java.util.ArrayList;
import java.util.List;

 
public class Clock implements Runnable
{
	  private static boolean running = false;
	  private static Thread thread;
	  
	  public static Clock clock;
	  private static final int fps = 60;
	  
	  public Clock()
	  {
		  clock = this;
		  start();
	  }
	  public void run() 
	  {
	      long lastTime = System.nanoTime();

	      double nsPerTick = 1000000000/fps;

	      double delta = 0;
	      
	      Update.Start();
	      
	      while(running)
	      {
	          long now = System.nanoTime();
	          delta += (now - lastTime) / nsPerTick;
	          lastTime = now;

	          while(delta >= 1 )
	          {
	        	  Update.Update();
	              delta -= 1;
	              
	          }
              CanvasRenderer.render.draw();

	      }
	  }
	  public synchronized void start()
	  {
	      if(running) return;
	      running = true;
	      thread = new Thread(this, "Thread");
	      thread.start();
	  }

	  public synchronized void stop(){
	      if(!running) return;
	      running = false;
	      try 
	      {
	          System.exit(1);
	          Controller.frame.dispose();
	          thread.join();
	      } 
	      catch (InterruptedException e) 
	      {
	          e.printStackTrace();
	      }
	  }
}
