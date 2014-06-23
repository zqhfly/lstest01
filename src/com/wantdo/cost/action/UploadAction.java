package com.wantdo.cost.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wantdo.cost.ec.EcEordermst;
import com.wantdo.cost.province.Province;
import com.wantdo.cost.service.IWspOrdermstService;
import com.wantdo.cost.shops.WspShops;
import com.wantdo.cost.utils.CostCalcUtil;
import com.wantdo.cost.utils.ExcelUtil;
import com.wantdo.cost.wsp.WspOrdermst;

public class UploadAction extends ActionSupport {
	
	public static final String[] format={"��ݵ���","��ݹ�˾","��ݷ���","�ջ���ַ","��������","�������",
		"�û�id","�µ�ʱ��","ɨ����Ա","ɨ��ʱ��"};
	public static final String[] errFormat={"��ݵ���"};
	
	private File upload;
	private IWspOrdermstService service;
	private ServletContext context;
	private String excelName;
	private String errExcelName;
	private String uploadFileName;
	private String uploadContentType;
	private File temp;
	List<String[]> list;
	List<String[]> err;
	Double total;
	int handleNum;
	int totalNum;

	public UploadAction() {
		super();
		// TODO Auto-generated constructor stub
		context=ServletActionContext.getServletContext();
		list=new ArrayList<String[]>();
		err=new ArrayList<String[]>();
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		InputStream in=null;
		OutputStream out=null;
		try {
			String uploadDir=context.getRealPath("upload");
			if (!(new File(uploadDir).isDirectory())) {
				new File(uploadDir).mkdirs();
				uploadDir=context.getRealPath("upload");
			}
			temp=new File(uploadDir+File.separator+uploadFileName);
			if (!temp.exists()) {
				temp.createNewFile();
			}
			in=new BufferedInputStream(new FileInputStream(upload));
			out=new FileOutputStream(temp);
			byte[] b=new byte[1024];
			int len=0;
			while ((len=in.read(b))!=-1) {
				out.write(b,0,len);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (out!=null) {
					out.close();
				}
				if (in!=null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ExcelUtil excelUtil=null;
		try {
			excelUtil = new ExcelUtil(new BufferedInputStream(new FileInputStream(temp)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] col=excelUtil.getColumnData(0, 0);
		boolean isWeight=false;
		String[] weightCol=null;
		if (excelUtil.getColumnNum(0)>1) {
			isWeight=true;
			weightCol=excelUtil.getColumnData(0, 1);
		}
		total=0.0;
		totalNum=col.length;
		for(int i=0;i<col.length;++i){
			handleNum=i+1;
			List<Object[]> listObject=service.getWspOrdermstByTraceno(col[i]);
			if (listObject==null) {
				return ERROR;
			}
			if (listObject.isEmpty()) {
				String[] rowErr=new String[]{
						col[i]
				};
				err.add(rowErr);
				String dateErr=new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
				errExcelName=dateErr+"_error.xls";
				//excelName=date+".xls";
				String errDir=context.getRealPath("error");
				if (!(new File(errDir)).isDirectory()) {
					new File(errDir).mkdirs();
					errDir=context.getRealPath("error");
				}
				File errFile=new File(errDir+File.separator+errExcelName);
				try {
					if (!errFile.exists()) {
						errFile.createNewFile();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				excelUtil.writeExcel(err, errFormat, errFile);
				continue;
			}
			WspOrdermst wspOrdermst=(WspOrdermst)listObject.get(0)[0];
			EcEordermst ecEordermst=(EcEordermst)listObject.get(0)[1];
			WspShops wspShops=(WspShops)listObject.get(0)[2];
			Province province=(Province)listObject.get(0)[3];
			CostCalcUtil costCalcUtil=new CostCalcUtil();
			Double cost=0.0;
			if (isWeight) {
				if (weightCol[i]==null||weightCol[i]=="") {
					weightCol[i]="1.0";
				}
				cost=costCalcUtil.getCostByProno(province.getPrvcname(),
						Double.parseDouble(weightCol[i]));
			}else {
				cost=costCalcUtil.getCostByProno(province.getPrvcname());
			}
			total+=cost;
			System.out.println(cost);
			String costDetail=null;
			if (cost==0.0) {
				costDetail="null";
			}else {
				costDetail=String.valueOf(cost);
			}
			String memberIdDetail="";
			String memberId=wspOrdermst.getMemberid();
			if (memberId==null||memberId.equals("")) {
				memberIdDetail="null";
			}else {
				memberIdDetail=memberId;
			}
			String[] row=new String[]{
					col[i],"��ͨ",costDetail,                                                                //��ݵ��ţ���ݹ�˾����ݷ���
					String.valueOf(ecEordermst.getDeliveraddr()),                           //�ջ���ַ
					String.valueOf(wspShops.getShopname()),                                 //��������
					String.valueOf(wspOrdermst.getRelid()),                                    //�������
					memberIdDetail,                           //�û�id
					String.valueOf(wspOrdermst.getOrderdate()),                           //�µ�ʱ��
					String.valueOf(ecEordermst.getEprintmanname()),                    //ɨ����Ա
					String.valueOf(ecEordermst.getEprintdate())                              //ɨ��ʱ��
					};	
			list.add(row);
		}
		String date=new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
		excelName=date+"_shentong.xls";
		//excelName=date+".xls";
		String downloadDir=context.getRealPath("download");
		if (!(new File(downloadDir)).isDirectory()) {
			new File(downloadDir).mkdirs();
			downloadDir=context.getRealPath("download");
		}
		File excelFile=new File(downloadDir+File.separator+excelName);
		try {
			if (!excelFile.exists()) {
				excelFile.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		excelUtil.writeExcel(list, format, excelFile,total);
		return SUCCESS;
	}
	

	public IWspOrdermstService getService() {
		return service;
	}

	public void setService(IWspOrdermstService service) {
		this.service = service;
	}

	public List<String[]> getList() {
		return list;
	}

	public void setList(List<String[]> list) {
		this.list = list;
	}

	public static String[] getFormat() {
		return format;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}



	public File getTemp() {
		return temp;
	}

	public void setTemp(File temp) {
		this.temp = temp;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getErrExcelName() {
		return errExcelName;
	}

	public void setErrExcelName(String errExcelName) {
		this.errExcelName = errExcelName;
	}

	public List<String[]> getErr() {
		return err;
	}

	public void setErr(List<String[]> err) {
		this.err = err;
	}

	public static String[] getErrformat() {
		return errFormat;
	}

	public int getHandleNum() {
		return handleNum;
	}

	public void setHandleNum(int handleNum) {
		this.handleNum = handleNum;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	
	
	
	
}
