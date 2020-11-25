package liubin.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liubin.store.domain.Product;
import liubin.store.service.ProductService;
import liubin.store.service.serviceImp.ProductServiceImp;
import liubin.store.web.base.BaseServlet;


public class IndexServlet extends BaseServlet {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//调用业务层功能:获取全部分类信息,返回集合
		//CategoryService CategoryService=new CategoryServiceImp();
		//List<Category> list=CategoryService.getAllCats();
		//将返回的集合放入request
		//request.setAttribute("allCats", list);
		
		//调用业务层查询最新商品,查询最热商品,返回2个集合
		ProductService ProductService=new ProductServiceImp();
		List<Product> list01=ProductService.findHots();
		List<Product> list02=ProductService.findNews();
		//将2个集合放入到request
		request.setAttribute("hots", list01);
		request.setAttribute("news", list02);
		//转发到真实的首页
		return "/jsp/index.jsp";
	}
}
