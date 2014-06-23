package com.wantdo.cost.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.wantdo.cost.express.EditLog;
import com.wantdo.cost.service.IEditLogService;
import com.wantdo.cost.utils.PageBean;

public class EditLogAction extends ActionSupport{
	private List<EditLog> proList;
	private IEditLogService editLogService;
	private int page = 3;//�ڼ�ҳ 
	private PageBean pageBean;//�����ֲ���Ϣ��bean 
	public EditLogAction(){
		super();
		proList = new ArrayList<EditLog>();
	}
	@Override
	public String execute() throws Exception {
		//proList = editLogService.findAll();
		this.pageBean = editLogService.queryForPage(3, page);//��ҳ��pageBean,����pageSize��ʾÿҳ��ʾ��¼��,pageΪ��ǰҳ 
		return SUCCESS;
		
	}
	public List<EditLog> getProList() {
		return proList;
	}
	public void setProList(List<EditLog> proList) {
		this.proList = proList;
	}
	public IEditLogService getEditLogService() {
		return editLogService;
	}
	public void setEditLogService(IEditLogService editLogService) {
		this.editLogService = editLogService;
	}
	

	public int getPage() { 
	return page; 
	} 

	public void setPage(int page) {//��URL���޴˲���,��Ĭ��Ϊ��1ҳ 
	this.page = page; 
	} 

	public PageBean getPageBean() { 
	return pageBean; 
	} 

	public void setPageBean(PageBean pageBean) { 
	this.pageBean = pageBean; 
	}
}
