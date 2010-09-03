package yyscamper.j2me;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MainMidlet extends MIDlet {
	static Display mDisplay = null;
	
	public MainMidlet(){
		
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		mDisplay = Display.getDisplay(this);
		mDisplay.setCurrent(HomeView.getInstance());
	}
	
	static public void show(Displayable disp){
		mDisplay.setCurrent(disp);
	}
	
}
