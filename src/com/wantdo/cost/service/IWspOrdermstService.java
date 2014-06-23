package com.wantdo.cost.service;

import java.util.List;

import com.wantdo.cost.wsp.WspOrdermst;

public interface IWspOrdermstService {
	
	public abstract void addWspOrdermst(WspOrdermst transientInstance);

	public abstract WspOrdermst getWspOrdermstById(java.lang.String id);

	public abstract WspOrdermst getWspOrdermstByRelid(Object relid);

	public abstract List<Object[]> getWspOrdermstByTraceno(Object traceno);
	
	public abstract List<Object[]> getShopStat();

}
