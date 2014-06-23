package com.wantdo.cost.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wantdo.cost.dao.IWspOrdermstDAO;
import com.wantdo.cost.ec.EcEordermst;
import com.wantdo.cost.service.IWspOrdermstService;
import com.wantdo.cost.shops.WspShops;
import com.wantdo.cost.wsp.WspOrdermst;

public class WspOrdermstService implements IWspOrdermstService {
	
	private IWspOrdermstDAO wspOrdermstDao;

	@Override
	public void addWspOrdermst(WspOrdermst transientInstance) {
		// TODO Auto-generated method stub
		wspOrdermstDao.save(transientInstance);
	}

	@Override
	public WspOrdermst getWspOrdermstById(String id) {
		// TODO Auto-generated method stub
		return wspOrdermstDao.findById(id);
	}

	@Override
	public WspOrdermst getWspOrdermstByRelid(Object relid) {
		// TODO Auto-generated method stub
		List<Object[]> list=wspOrdermstDao.findByRelid(relid);
		return (WspOrdermst)list.get(0)[0];
	}

	@Override
	public List<Object[]> getWspOrdermstByTraceno(Object traceno) {
		// TODO Auto-generated method stub
		/*
		List<Object[]> list = wspOrdermstDao.findByTraceno(traceno);
		if (list.isEmpty()) {
			list=new ArrayList<Object[]>();
			Object[] objects=new Object[]{
				new WspOrdermst(),
				new EcEordermst(),
				new WspShops()
			};
			list.add(objects);
			objects=null;
		}
		return list;
		*/
		return wspOrdermstDao.findByTraceno(traceno);
	}

	public IWspOrdermstDAO getWspOrdermstDao() {
		return wspOrdermstDao;
	}

	public void setWspOrdermstDao(IWspOrdermstDAO wspOrdermstDao) {
		this.wspOrdermstDao = wspOrdermstDao;
	}

	@Override
	public List<Object[]> getShopStat() {
		// TODO Auto-generated method stub
		return wspOrdermstDao.findShopStat();
	}

}
