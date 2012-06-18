package com.nokia.maps.demo;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.nokia.maps.common.ApplicationContext;
import com.nokia.maps.map.EventListener;
import com.nokia.maps.map.MapComponent;
import com.nokia.maps.map.MapDisplay;

public class DemoMidlet extends MIDlet {

	public DemoMidlet() {
		
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		
	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		
		ApplicationContext.getInstance().setAppID("-WE4HZW98Ujn8O_9yxd3");
		ApplicationContext.getInstance().setToken("10cas5cSC9o7XzVdCFEBrQ");
		Display display = Display.getDisplay(this);
		DemoMapCanvas canvas = new DemoMapCanvas(display);
		display.setCurrent(canvas);		
		canvas.getMapDisplay().addMapComponent(new DemoEventComponent());

	}

}
