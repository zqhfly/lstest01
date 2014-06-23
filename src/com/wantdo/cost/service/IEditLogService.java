package com.wantdo.cost.service;

import java.util.List;

import com.wantdo.cost.express.EditLog;
import com.wantdo.cost.utils.PageBean;

public interface IEditLogService {
	public List findAll() ;
	/** 
	* ��ҳ��ѯ 
	* @param currentPage ��ǰ�ڼ�ҳ 
	* @param pageSize ÿҳ��С 
	* @return ����˷�ҳ��Ϣ(������¼��list)��Bean 
	*/ 
	public PageBean queryForPage(int pageSize,int currentPage); 
}
