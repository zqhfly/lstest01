package com.wantdo.cost.test;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.wantdo.cost.ec.EcEordermst;
import com.wantdo.cost.service.IPronameService;
import com.wantdo.cost.service.IStoService;
import com.wantdo.cost.service.IWspOrdermstService;
import com.wantdo.cost.utils.CostCalcUtil;
import com.wantdo.cost.wsp.WspOrdermst;

public class ServiceTest {

	@Test
	public void testTraceno() {
		XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		IWspOrdermstService service=(IWspOrdermstService)factory.getBean("WspOrdermstService");
		List<Object[]> list=service.getWspOrdermstByTraceno("668659860876");
		WspOrdermst wspOrdermst=(WspOrdermst)list.get(0)[0];
		EcEordermst ecEordermst=(EcEordermst)list.get(0)[1];
		CostCalcUtil costCalcUtil=new CostCalcUtil();
		Double cost=costCalcUtil.getCostByProno(wspOrdermst.getProno());
		System.out.println(cost);
		String eprintmanname=ecEordermst.getEprintmanname();
		System.out.println(eprintmanname);
		String memberId=wspOrdermst.getMemberid();
		System.out.println(memberId);
	}
	
	@Test
	public void testShopStat(){
		XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		IWspOrdermstService service=(IWspOrdermstService)factory.getBean("WspOrdermstService");
		List<Object[]> list=service.getShopStat();
		System.out.println(list.get(0)[0]);
	}
	@Test
	public void testProname(){
		XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		IPronameService service=(IPronameService)factory.getBean("PronameService");
		List list=service.findAll();
		System.out.println(list.size());
	}
	@Test
	public void testSto(){
		XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		IStoService service=(IStoService)factory.getBean("StoService");
		List list=service.findAll();
		System.out.println(list.size());
	}
}
