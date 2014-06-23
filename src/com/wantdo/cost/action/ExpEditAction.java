package com.wantdo.cost.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.opensymphony.xwork2.ActionSupport;
import com.wantdo.cost.express.Proname;
import com.wantdo.cost.express.Sto;
import com.wantdo.cost.service.IPronameService;
import com.wantdo.cost.service.IStoService;

public class ExpEditAction extends ActionSupport {
	
	private String edit;
	private IPronameService pronameService;
	private IStoService stoService;
	private Sto sto;
	private List<Sto> updateList;
	private List<Proname> proList;
	private String msg;
	private String retentionTemp;
	private static final Logger log = LoggerFactory.getLogger(ExpEditAction.class);
	
	public ExpEditAction() {
		super();
		updateList = new ArrayList<Sto>();
		proList = new ArrayList<Proname>();
	}
	@Override
	public String execute() throws Exception {
		System.out.println("-------------->ExpEditAction");
		System.out.println(edit);
		proList = pronameService.findAll();
		if(edit == null){
			return "login";
		}
		if(edit.equals("update")){
			edit = null;
			sto = stoService.findById(sto.getId());
			if(sto.getRetention() == 1){
				retentionTemp = "��������";
			}else{
				retentionTemp = "����ȡ��";
			}
			msg = sto.getProname().getProvince()+",���ع��"+sto.getFirstWeight()+",���ؼ۸�"+sto.getFirstPrice()+",���ؼ۸�"+sto.getSecPrice()+",����������"+retentionTemp;
			System.out.println(msg);
			updateList.clear();
			updateList.add(getSto());
			return "update";
		}
		if(edit.equals("updateimpl") && sto != null){
			edit = null;
			stoService.update(sto);
			if(sto.getRetention() == 1){
				retentionTemp = "��������";
			}else{
				retentionTemp = "����ȡ��";
			}
			msg += "-->�޸�Ϊ:"+sto.getProname().getProvince()+",���ع��"+sto.getFirstWeight()+",���ؼ۸�"+sto.getFirstPrice()+",���ؼ۸�"+sto.getSecPrice()+",����������"+retentionTemp;
			System.out.println(msg);
			log.info(msg);
		}
		return "wait";
	}
//------------------------------------------------------------
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public IPronameService getPronameService() {
		return pronameService;
	}
	public void setPronameService(IPronameService pronameService) {
		this.pronameService = pronameService;
	}
	public IStoService getStoService() {
		return stoService;
	}
	public void setStoService(IStoService stoService) {
		this.stoService = stoService;
	}
	public Sto getSto() {
		return sto;
	}
	public void setSto(Sto sto) {
		this.sto = sto;
	}
	public List<Sto> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<Sto> updateList) {
		this.updateList = updateList;
	}
	public List<Proname> getProList() {
		return proList;
	}
	public void setProList(List<Proname> proList) {
		this.proList = proList;
	}
	
}
