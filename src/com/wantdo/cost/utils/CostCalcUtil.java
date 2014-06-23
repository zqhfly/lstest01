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
		list.add(new Express("浙江",2.0,4.5,1.0));
		list.add(new Express("江苏",2.0,4.5,1.0));
		list.add(new Express("上海",2.0,4.5,1.0));
		list.add(new Express("安徽",1.0,6.0,6.0));
		list.add(new Express("福建",1.0,8.0,8.0));
		list.add(new Express("广东",1.0,8.0,8.0));
		list.add(new Express("江西",1.0,8.0,8.0));
		list.add(new Express("山东",1.0,8.0,8.0));
		list.add(new Express("湖南",1.0,8.0,8.0));
		list.add(new Express("湖北",1.0,8.0,8.0));
		list.add(new Express("广西",1.0,9.0,9.0));
		list.add(new Express("河南",1.0,8.0,8.0));
		list.add(new Express("河北",1.0,8.0,8.0));
		list.add(new Express("山西",1.0,9.0,9.0));
		list.add(new Express("陕西",1.0,9.0,9.0));
		list.add(new Express("重庆",1.0,9.0,9.0));
		list.add(new Express("海南",1.0,9.0,9.0));
		list.add(new Express("北京",1.0,8.0,8.0));
		list.add(new Express("天津",1.0,8.0,8.0));
		list.add(new Express("四川",1.0,9.0,9.0));
		list.add(new Express("云南",1.0,10.0,10.0));
		list.add(new Express("贵州",1.0,10.0,10.0));
		list.add(new Express("辽宁",1.0,10.0,10.0));
		list.add(new Express("吉林",1.0,10.0,10.0));
		list.add(new Express("黑龙江",1.0,10.0,10.0));
		list.add(new Express("青海",1.0,15.0,15.0));
		list.add(new Express("宁夏",1.0,15.0,15.0));
		list.add(new Express("甘肃",1.0,15.0,15.0));
		list.add(new Express("内蒙古",1.0,15.0,15.0));
		list.add(new Express("新疆",1.0,15.0,15.0));
		list.add(new Express("西藏",1.0,15.0,15.0));
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
