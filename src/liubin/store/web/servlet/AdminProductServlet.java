package liubin.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liubin.store.domain.PageModel;
import liubin.store.service.ProductService;
import liubin.store.service.serviceImp.ProductServiceImp;
import liubin.store.web.base.BaseServlet;

public class AdminProductServlet extends BaseServlet {
	//findAllProductsWithPage
	public String findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取当前页
		int curNum=Integer.parseInt(req.getParameter("num"));
		//调用业务层查全部商品信息返回PageModel
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findAllProductsWithPage(curNum);
		//将PageModel放入request
		req.setAttribute("page", pm);
		//转发到/admin/product/list.jsp
		return "/admin/product/list.jsp";
	}
}
