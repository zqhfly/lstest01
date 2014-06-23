package com.wantdo.cost.service.impl;

import java.util.List;

import com.wantdo.cost.dao.IPronameDAO;
import com.wantdo.cost.express.Proname;
import com.wantdo.cost.express.Sto;
import com.wantdo.cost.service.IPronameService;

public class PronameService implements IPronameService {
	private IPronameDAO pronameDAO;
	@Override
	public List findAll() {
		System.out.println("--------------------service--findAll");
		return pronameDAO.findAll();
	}
	

	public IPronameDAO getPronameDAO() {
		return pronameDAO;
	}
	public void setPronameDAO(IPronameDAO pronameDAO) {
		this.pronameDAO = pronameDAO;
	}
}
