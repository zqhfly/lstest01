package com.wantdo.cost.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wantdo.cost.express.Express;

public class CostCalcUtil {
	
	private List<Express> list;

	public CostCalcUtil() {
		super();
		// TODO Auto-generated constructor stub
		list=new ArrayList<Express>();
		list.add(new Express("�㽭",2.0,4.5,1.0));
		list.add(new Express("����",2.0,4.5,1.0));
		list.add(new Express("�Ϻ�",2.0,4.5,1.0));
		list.add(new Express("����",1.0,6.0,6.0));
		list.add(new Express("����",1.0,8.0,8.0));
		list.add(new Express("�㶫",1.0,8.0,8.0));
		list.add(new Express("����",1.0,8.0,8.0));
		list.add(new Express("ɽ��",1.0,8.0,8.0));
		list.add(new Express("����",1.0,8.0,8.0));
		list.add(new Express("����",1.0,8.0,8.0));
		list.add(new Express("����",1.0,9.0,9.0));
		list.add(new Express("����",1.0,8.0,8.0));
		list.add(new Express("�ӱ�",1.0,8.0,8.0));
		list.add(new Express("ɽ��",1.0,9.0,9.0));
		list.add(new Express("����",1.0,9.0,9.0));
		list.add(new Express("����",1.0,9.0,9.0));
		list.add(new Express("����",1.0,9.0,9.0));
		list.add(new Express("����",1.0,8.0,8.0));
		list.add(new Express("���",1.0,8.0,8.0));
		list.add(new Express("�Ĵ�",1.0,9.0,9.0));
		list.add(new Express("����",1.0,10.0,10.0));
		list.add(new Express("����",1.0,10.0,10.0));
		list.add(new Express("����",1.0,10.0,10.0));
		list.add(new Express("����",1.0,10.0,10.0));
		list.add(new Express("������",1.0,10.0,10.0));
		list.add(new Express("�ຣ",1.0,15.0,15.0));
		list.add(new Express("����",1.0,15.0,15.0));
		list.add(new Express("����",1.0,15.0,15.0));
		list.add(new Express("���ɹ�",1.0,15.0,15.0));
		list.add(new Express("�½�",1.0,15.0,15.0));
		list.add(new Express("����",1.0,15.0,15.0));
	}
	
	public double getCostByProno(String prono){
		if (prono!=null) {
			for(int i=0;i<list.size();++i){
				if (prono.contains(list.get(i).getProno())) {
					System.out.println(list.get(i).toString());
					return list.get(i).getFirstPrice();
				}
			}
		}
		return 0.0;
	}
	
	public double getCostByProno(String prono,Double weight){
		if (prono!=null) {
			for(int i=0;i<list.size();++i){
				Express express=list.get(i);
				if (prono.contains(express.getProno())) {
					System.out.println(express.toString()+"weight:"+weight);
					if (weight<=express.getFirst()) {
						return express.getFirstPrice();
					}else {
						System.out.println(express.getFirst());
						if (express.getFirst()==2.0) {
							return express.getFirstPrice()+Math.ceil(weight-express.getFirst())*express.getSecPrice();
						}else {
							return Math.round(express.getFirstPrice()+(weight-express.getFirst())*express.getSecPrice());
						}
					}
				}
			}
		}
		return 0.0;
	}

}
