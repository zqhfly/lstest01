package com.wantdo.interceptor;

import java.util.Map;

import org.apache.log4j.MDC;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wantdo.cost.action.LoginAction;

public class CheckLoginInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 2092930262572782343L;
	@Override  
    public String intercept(ActionInvocation actionInvocation) throws Exception {  
        System.out.println("begin check login interceptor!");  
        // ��LoginAction������������  
        Object action = actionInvocation.getAction();  
        if (action instanceof LoginAction) {  
            System.out.println("exit check login, because this is login action.");  
            //UserInfoAction userinfoAction = (UserInfoAction)action;  
            return actionInvocation.invoke();  
        }  
        // ȷ��Session���Ƿ����Person  
        Map<String,Object> session = actionInvocation.getInvocationContext().getSession();  
        //Person person = (Person) session.get("username");  
        if (session.get("username") != null) {  
        	MDC.put("username",session.get("username"));
        	System.out.println(MDC.get("username"));
            // ���ڵ�����½��к���������  
            System.out.println("already login!");  
            return actionInvocation.invoke();  
        } else {  
            // ������ֹ��������������LOGIN  
            System.out.println("no login, forward login page!");  
            return "login";  
        }  
    }  
}
