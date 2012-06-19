package com.nokia.maps.demo;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.QualifiedCoordinates;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.nokia.maps.common.ApplicationContext;
import com.nokia.maps.common.GeoBoundingBox;
import com.nokia.maps.common.GeoCoordinate;
import com.nokia.maps.common.Place;
import com.nokia.maps.map.EventListener;
import com.nokia.maps.map.MapComponent;
import com.nokia.maps.map.MapDisplay;
import com.nokia.maps.map.MapStandardMarker;
import com.nokia.maps.map.Point;
import com.nokia.maps.search.PlaceSearchRequest;
import com.nokia.maps.search.PlaceSearchRequestListener;
import com.nokia.maps.search.SearchFactory;
import com.nokia.mid.location.LocationUtil;

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
		final DemoMapCanvas canvas = new DemoMapCanvas(display);
		display.setCurrent(canvas);		
		final MapDisplay map = canvas.getMapDisplay();
		map.addMapComponent(new DemoEventComponent());
		
		try {
			LocationProvider provider = LocationUtil.getLocationProvider(new int[]{Location.MTA_ASSISTED|
					Location.MTE_CELLID|
					Location.MTY_NETWORKBASED}, null);
			Location l = provider.getLocation(2000);
			QualifiedCoordinates cq = l.getQualifiedCoordinates();
			MapStandardMarker marker = canvas.getMapFactory().createStandardMarker(new GeoCoordinate( cq.getLatitude(), cq.getLongitude(), 0));
			marker.setText("Test");
			marker.setColor(0x99ff0000);
			map.addMapObject(marker);
			map.zoomTo(marker.getBoundingBox(),false);
			
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	
		
		Command searchCommand = new Command("Ara", Command.SCREEN, 10);
		
		canvas.addCommand(searchCommand);
		canvas.setCommandListener(new CommandListener() {
			
			public void commandAction(Command arg0, Displayable arg1) {
				PlaceSearchRequest request = SearchFactory.getInstance().createPlaceSearchRequest();
				
				GeoCoordinate topLeft = map.pixelToGeo(new Point(0,0));
				GeoCoordinate bottomRight = map.pixelToGeo(new Point(map.getWidth(),map.getHeight()));
				request.search("cafe", new GeoBoundingBox(topLeft, bottomRight),new PlaceSearchRequestListener() {
					
					public void onRequestError(PlaceSearchRequest request, Throwable error) {
						// TODO Auto-generated method stub
						
					}
					
					public void onRequestComplete(PlaceSearchRequest request, Place[] result) {
						for (int i = 0; i < result.length; i++) {
							MapStandardMarker m = canvas.getMapFactory().createStandardMarker(result[i].getLocations()[0].getDisplayPosition());
							canvas.getMapDisplay().addMapObject(m);
							m.setText(result[i].getName());
						}
						
					}
				});

				
			}
		});
	}

}
