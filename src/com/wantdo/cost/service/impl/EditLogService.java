package com.wantdo.cost.service.impl;

import java.util.List;

import com.wantdo.cost.dao.IEditLogDAO;
import com.wantdo.cost.express.EditLog;
import com.wantdo.cost.service.IEditLogService;
import com.wantdo.cost.utils.PageBean;


public class EditLogService implements IEditLogService {
	private IEditLogDAO editLogDAO;
	@Override
	public List findAll() {
		return editLogDAO.findAll();
	}


	public IEditLogDAO getEditLogDAO() {
		return editLogDAO;
	}


	public void setEditLogDAO(IEditLogDAO editLogDAO) {
		this.editLogDAO = editLogDAO;
	}


	/** *//** 
	* ��ҳ��ѯ 
	* @param currentPage ��ǰ�ڼ�ҳ 
	* @param pageSize ÿҳ��С 
	* @return ����˷�ҳ��Ϣ(������¼��list)��Bean 
	*/ 
	@Override
	public PageBean queryForPage(int pageSize,int page){ 

	final String hql = "from EditLog";//��ѯ��� 
	int allRow = editLogDAO.getAllRowCount(hql);//�ܼ�¼�� 
	int totalPage = PageBean.countTotalPage(pageSize, allRow);//��ҳ�� 
	final int offset = PageBean.countOffset(pageSize, page);//��ǰҳ��ʼ��¼ 
	final int length = pageSize;//ÿҳ��¼�� 
	final int currentPage = PageBean.countCurrentPage(page); 
	List<EditLog> list = editLogDAO.queryForPage(hql,offset, length);//"һҳ"�ļ�¼ 

	//�ѷ�ҳ��Ϣ���浽Bean�� 
	PageBean pageBean = new PageBean(); 
	pageBean.setPageSize(pageSize);
	pageBean.setCurrentPage(currentPage); 
	pageBean.setAllRow(allRow); 
	pageBean.setTotalPage(totalPage); 
	pageBean.setList(list); 
	pageBean.init(); 
	return pageBean; 
	}
}
