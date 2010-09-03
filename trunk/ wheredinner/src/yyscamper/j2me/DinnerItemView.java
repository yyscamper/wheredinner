package yyscamper.j2me;

import java.io.IOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class DinnerItemView extends Canvas implements CommandListener{
	private DinnerItem mItem = null;
	private Displayable mPreUi = null;
	private Command backCmd = new Command("·µ»Ø", Command.ITEM, 1);
	private Image mTitleImage = null;
	private Image mBigImage = null;
	
	public DinnerItemView(DinnerItem item, Displayable preUi){
		this.mItem = item;
		this.mPreUi = preUi;
		this.addCommand(backCmd);
		this.setCommandListener(this);
		try {
			mTitleImage = Image.createImage(item.titleImage);
			mBigImage = Image.createImage(item.bigImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void paint(Graphics g) {
		int width, height, tempint, woffset, hoffset;
		char[] charArr = null;
		
		width = this.getWidth();
		height = this.getHeight();
		
		g.setColor(0x808080);
		g.fillRect(0, 0, width, height);
		
		g.setColor(0x000000);
		Font font = Font.getDefaultFont();
		charArr = mItem.name.toCharArray();
		tempint = font.charsWidth(charArr, 0, charArr.length);
		woffset = (width - tempint) / 2;
		hoffset = 0;
		g.drawString(mItem.name, woffset, hoffset, Graphics.TOP | Graphics.LEFT);
		
		hoffset = font.getHeight();
		if(mTitleImage != null){
			woffset = (width - mTitleImage.getWidth()) / 2;
			g.drawImage(mTitleImage, woffset, hoffset, Graphics.TOP | Graphics.LEFT);
			hoffset += (mTitleImage.getHeight() + font.getHeight());
		}
		
		if(mBigImage != null){
			woffset = (width - mBigImage.getWidth()) / 2;
			g.drawImage(mBigImage, woffset, hoffset, Graphics.TOP | Graphics.LEFT);
		}
	}
	
	protected void pointerPressed(int x, int y) {
		MainMidlet.show(mPreUi);
	}

	public void commandAction(Command c, Displayable d) {
		if(c.getLabel().equals("·µ»Ø")){
			MainMidlet.show(mPreUi);
		}
	}
	
}
