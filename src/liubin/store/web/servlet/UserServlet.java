package liubin.store.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liubin.store.domain.Category;
import liubin.store.domain.User;
import liubin.store.service.CategoryService;
import liubin.store.service.UserService;
import liubin.store.service.serviceImp.CategoryServiceImp;
import liubin.store.service.serviceImp.UserServiceImp;
import liubin.store.utils.MailUtils;
import liubin.store.utils.MyBeanUtils;
import liubin.store.utils.UUIDUtils;
import liubin.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {

	public String registUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "/jsp/login.jsp";
	}

	// userRegist
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 接收表单参数
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		MyBeanUtils.populate(user, map);
		// 为用户的其他属性赋值
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		System.out.println(user);

		/*
		 * //遍历map中的数据 Set<String> keySet = map.keySet(); Iterator<String>
		 * iterator = keySet.iterator(); while(iterator.hasNext()){ String
		 * str=iterator.next(); System.out.println(str); String[] strs =
		 * map.get(str); for (String string : strs) {
		 * System.out.println(string); } System.out.println(); }
		 */

		// 调用业务层注册功能
		UserService UserService = new UserServiceImp();
		try {
			UserService.userRegist(user);
			// 注册成功,向用户邮箱发送信息,跳转到提示页面
			// 发送邮件
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "用户注册成功,请激活!");

		} catch (Exception e) {
			// 注册失败,跳转到提示页面
			request.setAttribute("msg", "用户注册失败,请重新注册!");

		}
		return "/jsp/info.jsp";
	}

	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取激活码
		String code=request.getParameter("code");
		//调用业务层激活功能
		UserService UserService=new UserServiceImp();
		boolean flag=UserService.userActive(code);
		
		//进行激活信息提示
		if(flag==true){
			//用户激活成功,向request放入提示信息,转发到登录页面
			request.setAttribute("msg", "用户激活成功,请登录!");
			return "/jsp/login.jsp";
		}else{
			//用户激活失败,向request放入提示信息,转发到提示页面
			request.setAttribute("msg", "用户激活失败,请重新激活!");
			return  "/jsp/info.jsp";
		}
	}
	
	//userLogin
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取用户数据(账户/密码)
		User user=new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		
		//调用业务层登录功能
		UserService UserService=new UserServiceImp();
		User user02=null;
		try {
			//select * from user where username=?  and password=?
			user02=UserService.userLogin(user);
			//用户登录成功,将用户信息放入session中
			request.getSession().setAttribute("loginUser", user02);
			response.sendRedirect("/store_v5/index.jsp");
			return null;
		} catch (Exception e) {
			//用户登录失败
			String msg=e.getMessage();
			System.out.println(msg);
			//向request放入失败的信息
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}
	
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  //清除session
			request.getSession().invalidate();
		  //重新定向到首页
		  response.sendRedirect("/store_v5/index.jsp");
		  return null;	
	}
}
