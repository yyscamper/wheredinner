package yyscamper.j2me;

import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class DinnerItem {
	public int id;
	public String name;
	public String description;
	public String titleImage;
	public String bigImage;
	public String place;
	public String telephone;
	public int score;
	public int count;
	public Date[] dates;
	public boolean isSelected;
	public int prob; //����
	
	static public int PROB_BIG = 3;
	static public int PROB_MEDIAN = 2;
	static public int PROB_SMALL = 1;
	
	static private DinnerItem ALL_DINNER_ITEMS[] = {
		new DinnerItem(1, "����������","����������","/tangbao_title.jpg", "/tangbao.jpg","�������ɽ·7��(��Ԫ��·��)", "021-64475086", 80, 0, null, true, PROB_MEDIAN),
		new DinnerItem(2, "���ʹ���","���ʹ���","/yonghe_title.jpg", "/yonghe.jpg","�������Ԫ��·98��(������·)", "021-64073508", 80, 0, null, true, PROB_MEDIAN),
		new DinnerItem(3, "ţ�S", "ʳ��ҡ���ţ�S", "/sukiya_title.jpg", "/sukiya.jpg", "�������Ԫ��·88��(����ɽ·)", " 021-64470770", 80, 0, null, true, PROB_MEDIAN),
		new DinnerItem(4, "�ĺ�����", "�ĺ�����(Overseas Dragon)","/dragon_title.jpg", "/dragon.jpg", "�������خ·955��(������·)", "021-64480577", 80, 0, null, true, PROB_MEDIAN)
	};
	
	public DinnerItem(int id, String name, String description, String titleImage, String bigImage, String place, String telephone,
		int score, int count, Date[] dates, boolean isSelected, int prob){
			this.id = id;
			this.name = name;
			this.description = description;
			this.titleImage = titleImage;
			this.bigImage = bigImage;
			this.place = place;
			this.score = score;
			this.count = count;
			this.dates = dates;
			this.isSelected = isSelected;
			this.prob = prob;
	}
	
	static public DinnerItem parser(byte[] data){
		DinnerItem item = null;
		
		return item;
	}
	
	static public byte[] serialize(DinnerItem item){
		byte[] data = null;
		
		return data;
	}
	
	static public int getNumOfAllItems(){
		return ALL_DINNER_ITEMS.length;
	}
	
	static public DinnerItem[] getAllItems(){
		return ALL_DINNER_ITEMS;
	}
	
	static public String[] getAllItemNames(){
		String[] names = new String[ALL_DINNER_ITEMS.length];
		for(int i=0; i<names.length; i++){
			names[i] = ALL_DINNER_ITEMS[i].name;
		}
		return names;
	}
	
	static public boolean[] getAllItemSelectedFlag(){
		boolean[] flags = new boolean[ALL_DINNER_ITEMS.length];
		for(int i=0; i < flags.length; i++){
			flags[i] = ALL_DINNER_ITEMS[i].isSelected;
		}
		return flags;
	}
	
	static public int getNumOfSelected(){
		int selcnt = 0;
		for(int i=0; i<ALL_DINNER_ITEMS.length; i++){
			if(ALL_DINNER_ITEMS[i].isSelected){
				selcnt++;
			}
		}
		return selcnt;
	}
	
	static public DinnerItem[] getSelectedItems(){
		Vector vec = new Vector();
		for(int i=0; i<ALL_DINNER_ITEMS.length; i++){
			if(ALL_DINNER_ITEMS[i].isSelected){
				vec.addElement(ALL_DINNER_ITEMS[i]);
			}
		}
		DinnerItem[] items = new DinnerItem[vec.size()];
		vec.copyInto(items);
		return items;
	}
	
	static public DinnerItem getItemById(int id){
		for(int i=0; i<ALL_DINNER_ITEMS.length; i++){
			if(ALL_DINNER_ITEMS[i].id == id){
				return ALL_DINNER_ITEMS[i];
			}
		}
		return null;
	}
	
	static public DinnerItem getRandItem(){
		int numItems = getNumOfSelected();
		Random theRandom = new Random();
		int selIndex = theRandom.nextInt(numItems);
		int tempcnt = 0;
		for(int i=0; i<ALL_DINNER_ITEMS.length; i++){
			if(ALL_DINNER_ITEMS[i].isSelected){
				if(tempcnt++ == selIndex){
					return ALL_DINNER_ITEMS[i];
				}
			}
		}
		return null;
	}
}
