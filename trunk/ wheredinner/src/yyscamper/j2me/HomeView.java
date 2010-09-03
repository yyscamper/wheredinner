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

public class HomeView extends Canvas implements CommandListener{
	Command startCmd = new Command("开始", Command.ITEM, 0);
	Command chooseCmd = new Command("选择", Command.ITEM, 0);
	Image mMainImage = null;
	Image mSettingImage = null;
	int mImageWidthStart = 0;
	int mImageHeightStart = 0;
	static private String[] RAND_MESSAGE = {"试试手气","今天吃什么好呢?", "天灵灵，地灵灵"};
	static private HomeView mInstance = null;
	String[] mBtnStr = new String[]{"设置","开始"};
	
	static public HomeView getInstance(){
		if(mInstance == null){
			mInstance = new HomeView();
		}
		return mInstance;
	}
	
	private HomeView(){
		try {
			mMainImage = Image.createImage("/home.png");
			mSettingImage = Image.createImage("/setting.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		this.addCommand(startCmd);
		this.addCommand(chooseCmd);
		this.setCommandListener(this);
		
		mImageWidthStart = (this.getWidth() - mMainImage.getWidth()) / 2;
		mImageHeightStart = (this.getHeight() - mMainImage.getHeight()) / 2;
	}

	public void commandAction(Command c, Displayable d) {
		if(c.getLabel().equalsIgnoreCase("开始")){
			MainMidlet.show(new DinnerItemView(DinnerItem.getRandItem(), this));
		}else if(c.getLabel().equalsIgnoreCase("选择")){
			MainMidlet.show(ItemSelectView.getInstance());
		}
	}

	protected void paint(Graphics g) {
		int woffset, hoffset;
		int width, height;
		int tempint;
		Font font = Font.getDefaultFont();
		
		String randMsg = RAND_MESSAGE[Util.getRandInt(RAND_MESSAGE.length)];
		width = this.getWidth();
		height = this.getHeight();
		
		g.setColor(0x808080);
		g.fillRect(0, 0, width, height);
		
		tempint = Util.getStrFontWidth(randMsg, font);
		woffset = (width - tempint) / 2;
		hoffset = 0;
		g.setColor(0x000000);
		g.drawString(randMsg, woffset, hoffset, Graphics.TOP | Graphics.LEFT);
		
		g.drawImage(mMainImage, mImageWidthStart, mImageHeightStart, Graphics.TOP | Graphics.LEFT);
		//g.drawImage(mSettingImage, width - mSettingImage.getWidth(), height - mSettingImage.getHeight(), Graphics.TOP | Graphics.LEFT);
	
		Util.paint_button(g, width, height, mBtnStr);
	}
	
	protected void pointerPressed(int x, int y) {
		int btn = Util.getClickButton(x, y, this.getWidth(), this.getHeight(), 2, Util.BOTTOM_BUTTON_TYPE_MULTIPLE);
		if(btn == 0){
			MainMidlet.show(ItemSelectView.getInstance());
		}else if(btn == 1){
			MainMidlet.show(new RandAnimationView());
		}else if(Util.isInRectRange(x, y, mImageWidthStart, mImageHeightStart, mMainImage.getWidth(), mMainImage.getHeight())){
			MainMidlet.show(new RandAnimationView());
		}
	}
}
