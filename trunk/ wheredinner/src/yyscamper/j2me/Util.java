package yyscamper.j2me;

import java.util.Random;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class Util {
	private static Random mrand = new Random();
	private static int mBtmBtnHeight = 32;
	
	static final int BOTTOM_BUTTON_TYPE_LEFT = 1;
	static final int BOTTOM_BUTTON_TYPE_RIGHT = 2;
	static final int BOTTOM_BUTTON_TYPE_CENTER = 3;
	static final int BOTTOM_BUTTON_TYPE_MULTIPLE = 4;
	
	static boolean isInRectRange(int x, int y, int rectx, int recty, int rectWidth, int rectHeight){
		if(x >= rectx && x <= rectx + rectWidth && y >= recty && y <= recty + rectHeight){
			return true;
		}else{
			return false;
		}
	}
	
	static int getRandInt(int max){
		return mrand.nextInt(max);
	}
	
	static int getStrFontWidth(String str, Font font){
		char[] data = str.toCharArray();
		return font.charsWidth(data, 0, data.length);
	}
	
	static int getOffset(int smallone, int bigone){
		return (bigone - smallone) / 2;
	}
	
	static public void paint_button(Graphics g, int width, int height, String[] strArr){
		Font font = Font.getDefaultFont();
		int num = strArr.length;
		int woffset = 0;
		int eachWidth = width / num;
		int strWidth = 0;
		for(int i=0; i<num; i++){
			g.setColor(0xFF);
			g.fillRoundRect(woffset, height-mBtmBtnHeight, eachWidth, mBtmBtnHeight, mBtmBtnHeight/3, mBtmBtnHeight/3);
			g.setColor(0xFFFF00);
			g.drawRoundRect(woffset, height-mBtmBtnHeight, eachWidth, mBtmBtnHeight, mBtmBtnHeight/3, mBtmBtnHeight/3);
			strWidth = font.stringWidth(strArr[i]);
			g.drawString(strArr[i], woffset + (eachWidth - strWidth)/2, 
					height-mBtmBtnHeight + (mBtmBtnHeight-font.getHeight())/2, 
					Graphics.TOP | Graphics.LEFT);
			woffset += eachWidth;
		}
	}
	
	static public void drawRightButton(Graphics g, int width, int height, String str){
		Font font = Font.getDefaultFont();
		int strWidth = 0;
		g.setColor(0xFF);
		g.fillRoundRect(width/2, height-mBtmBtnHeight, width/2, mBtmBtnHeight, mBtmBtnHeight/3, mBtmBtnHeight/3);
		g.setColor(0xFFFF00);
		g.drawRoundRect(width/2, height-mBtmBtnHeight, width/2, mBtmBtnHeight, mBtmBtnHeight/3, mBtmBtnHeight/3);
		strWidth = font.stringWidth(str);
		g.drawString(str, width/2 + (width/2 - strWidth)/2, 
				height-mBtmBtnHeight + (mBtmBtnHeight-font.getHeight())/2, 
				Graphics.TOP | Graphics.LEFT);
	}
	
	static public int getClickButton(int x, int y, int width, int height, int numOfBtns, int type){
		if(y < height - mBtmBtnHeight){
			return -1;
		}
		switch(type){
			case BOTTOM_BUTTON_TYPE_MULTIPLE:
				return x * numOfBtns / width;
			case BOTTOM_BUTTON_TYPE_RIGHT:
				if(x > width /2){
					return 0;
				}else{
					return -1;
				}
			case BOTTOM_BUTTON_TYPE_LEFT:
				if(x <= width /2){
					return 0;
				}else{
					return -1;
				}
			case BOTTOM_BUTTON_TYPE_CENTER:
				if(x >= (width / 4) && x <= (width*3)/4){
					return 0;
				}else{
					return -1;
				}
			default:
				return -1;
		}
	}
}
