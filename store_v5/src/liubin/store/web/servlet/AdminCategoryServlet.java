package liubin.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liubin.store.domain.Category;
import liubin.store.service.CategoryService;
import liubin.store.service.serviceImp.CategoryServiceImp;
import liubin.store.utils.UUIDUtils;
import liubin.store.web.base.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	//findAllCats
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取全部分类信息
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list=CategoryService.getAllCats();
		//全部分类信息放入request
		req.setAttribute("allCats", list);
		//转发到/admin/category/list.jsp
		return "/admin/category/list.jsp";
	}
	
	//addCategoryUI
	public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "/admin/category/add.jsp";
	}
	//addCategory
	public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取分类名称
		String cname=req.getParameter("cname");
		//创建分类ID
		String id=UUIDUtils.getId();
		Category c=new Category();
		c.setCid(id);
		c.setCname(cname);
		//调用业务层添加分类功能
		CategoryService CategoryService=new CategoryServiceImp();
		CategoryService.addCategory(c);
		//重定向到查询全部分类信息
		resp.sendRedirect("/store_v5/AdminCategoryServlet?method=findAllCats");
		return null;
	}
}
