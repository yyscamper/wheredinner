package yyscamper.j2me;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ItemSelectView extends Canvas implements CommandListener{
	DinnerItem[] mAllItems = null;
	Displayable mPreView = null;
	Image mSelImage = null;
	Image mUnSelImage = null;
	static ItemSelectView mInstance = null;
	String[] mListStr = null;
	boolean[] mListSelFlag = null;
	int mWidth = 0;
	int mHeight = 0;
	
	static public ItemSelectView getInstance(){
		if(mInstance == null){
			mInstance = new ItemSelectView();
		}
		return mInstance;
	}
	
	public void setBackView(Displayable backview){
		this.mPreView = backview;
	}
	
	private ItemSelectView() {
		try {
			mSelImage = Image.createImage("/selected.png");
			mUnSelImage = Image.createImage("/unselected.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		
		this.addCommand(new Command("·µ»Ø", Command.ITEM, 0));
		this.setCommandListener(this);
		
		mListStr = DinnerItem.getAllItemNames();
		mListSelFlag = DinnerItem.getAllItemSelectedFlag();
		mWidth = this.getWidth();
		mHeight= this.getHeight();
	}

	public void commandAction(Command c, Displayable d) {
		if(c.getLabel().equals("·µ»Ø")){
			if(mPreView == null){
				MainMidlet.show(HomeView.getInstance());
			}else{
				MainMidlet.show(mPreView);
			}
		}	
	}

	protected void paint(Graphics g) {
		int woffset = 0, hoffset = 0;
		g.setColor(0x808080);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Font font = Font.getDefaultFont();
		
		g.setColor(0xFFFF00);
		for(int i=0; i<mListStr.length; i++){
			//paint select flag image
			if(mListSelFlag[i]){
				g.drawImage(mSelImage, woffset, hoffset, Graphics.TOP | Graphics.LEFT);
			}else{
				g.drawImage(mUnSelImage, woffset, hoffset, Graphics.TOP | Graphics.LEFT);
			}
			//paint string
			g.drawString(mListStr[i], woffset + mSelImage.getWidth(), 
					hoffset + (mSelImage.getHeight() - font.getHeight()) / 2, Graphics.TOP | Graphics.LEFT);
			
			hoffset += mSelImage.getHeight();
		}
		
		Util.drawRightButton(g, mWidth, mHeight, "·µ»Ø");
	}
	
	protected void pointerPressed(int x, int y){
		int index = (int)((double)y / (double)mSelImage.getHeight());
		
		if(index < mListSelFlag.length){
			mListSelFlag[index] = !mListSelFlag[index];
		}
		
		if(0 == Util.getClickButton(x, y, mWidth, mHeight, 1, Util.BOTTOM_BUTTON_TYPE_RIGHT)){
			if(mPreView == null){
				MainMidlet.show(HomeView.getInstance());
			}else{
				MainMidlet.show(mPreView);
			}
		}
		repaint();
		serviceRepaints();
	}
}
