package com.nokia.maps.demo;

import javax.microedition.lcdui.Display;

import com.nokia.maps.map.MapCanvas;

public class DemoMapCanvas extends MapCanvas {

	public DemoMapCanvas(Display display) {
		super(display);
	}

	public void onMapContentComplete() {

	}

	public void onMapUpdateError(String description, Throwable detail,
			boolean critical) {
		
		//Report problems to the user in here.
		detail.printStackTrace();

	}

}
