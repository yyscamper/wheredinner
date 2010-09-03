package yyscamper.j2me;

import java.io.IOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class RandAnimationView extends Canvas implements Runnable{
	DinnerItem[] mItems = null;
	static int mRoundNum = 20;
	Image[] mImages = null;
	Image mCurrImage = null;
	
	public RandAnimationView(){
		mItems = DinnerItem.getSelectedItems();
		if(mItems == null || mItems.length <= 0){
			MainMidlet.show(new Alert("你没有选中任何餐馆，请选择至少两个餐馆！"));
			MainMidlet.show(ItemSelectView.getInstance());
		}else if(mItems.length == 1){
			MainMidlet.show(new DinnerItemView(mItems[0], HomeView.getInstance()));
		}else{
			mImages = new Image[mItems.length];
			for(int i=0; i<mItems.length; i++){
				try {
					mImages[i] = Image.createImage(mItems[i].bigImage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			mCurrImage = mImages[0];
			new Thread(this).start();
		}
	}

	protected void paint(Graphics g) {
		if(mCurrImage == null){
			return;
		}
		int width = this.getWidth();
		int height = this.getHeight();
		
		g.setColor(0x808080);
		g.fillRect(0, 0, width, height);
		g.drawImage(mCurrImage, (width - mCurrImage.getWidth()) / 2, 
				(height - mCurrImage.getHeight()) / 2, 
				Graphics.TOP | Graphics.LEFT);
	}
	
	protected void pointerPressed(int x, int y) {
		MainMidlet.show(new DinnerItemView(DinnerItem.getRandItem(), HomeView.getInstance()));	
	}
	
	public void run() {
		int i = 0;
		while(true){
			mCurrImage = mImages[i];
			repaint();
			serviceRepaints();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(++i >= mItems.length){
				i = 0;
			}
		}
	}
}
