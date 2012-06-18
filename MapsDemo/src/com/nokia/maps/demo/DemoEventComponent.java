package com.nokia.maps.demo;

import javax.microedition.lcdui.Graphics;

import com.nokia.maps.common.GeoCoordinate;
import com.nokia.maps.map.EventListener;
import com.nokia.maps.map.MapComponent;
import com.nokia.maps.map.MapDisplay;
import com.nokia.maps.map.Point;

public class DemoEventComponent implements MapComponent {
	private EventListener listener;
	private MapDisplay map;

	public void attach(MapDisplay map) {
		this.map = map;

	}

	public void detach(MapDisplay map) {
		this.map = null;

	}

	public EventListener getEventListener() {
		if (listener == null ){
			listener = new EventListener() {
				
				public boolean pointerReleased(int x, int y) {
					if (map == null ) return false;
					GeoCoordinate coords = map.pixelToGeo(new Point(x, y));
					System.out.println("Nokta: "+ coords.getLatitude()+ " , "+ coords.getLongitude());
					return false;
				}
				
				public boolean pointerPressed(int x, int y) {
					
					return false;
				}
				
				public boolean pointerDragged(int x, int y) {

					return false;
				}
				
				public boolean keyRepeated(int keyCode, int gameAction, int repeatCount) {

					return false;
				}
				
				public boolean keyReleased(int keyCode, int gameAction) {

					return false;
				}
				
				public boolean keyPressed(int keyCode, int gameAction) {

					return false;
				}
			}; 
		}
		return listener;
	}

	public String getId() {
		
		return "com.nokia.maps.demo.event";
	}

	public String getVersion() {
		
		return "1.0";
	}

	public void mapUpdated(boolean zoomChanged) {
	

	}

	public void paint(Graphics g) {


	}

}
