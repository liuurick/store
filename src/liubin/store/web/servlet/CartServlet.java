package liubin.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liubin.store.domain.Cart;
import liubin.store.domain.CartItem;
import liubin.store.domain.Product;
import liubin.store.service.ProductService;
import liubin.store.service.serviceImp.ProductServiceImp;
import liubin.store.web.base.BaseServlet;

public class CartServlet extends BaseServlet {
	
	//添加购物项到购物车
	public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//从session获取购物车
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		if(null==cart){
		  //如果获取不到,创建购物车对象,放在session中
			cart=new Cart();
			req.getSession().setAttribute("cart", cart);
		}
   		//如果获取到,使用即可
		//获取到商品id,数量
		String pid=req.getParameter("pid");
		int num=Integer.parseInt(req.getParameter("quantity"));
		//通过商品id查询都商品对象
		ProductService ProductService=new ProductServiceImp();
		Product product=ProductService.findProductByPid(pid);	
		//获取到待购买的购物项
		CartItem cartItem=new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);

		//调用购物车上的方法
		cart.addCartItemToCar(cartItem);
		
		//重定向到/jsp/cart.jsp
		resp.sendRedirect("/store_v5/jsp/cart.jsp");
		//return "/jsp/cart.jsp";
		return  null;
	}
	
	//removeCartItem
	public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取待删除商品pid
		String pid=req.getParameter("id");
		//获取到购物车
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		//调用购物车删除购物项方法
		cart.removeCartItem(pid);
		//重定向到/jsp/cart.jsp
		resp.sendRedirect("/store_v5/jsp/cart.jsp");
		return null;
	}
	//clearCart
	public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取购物车
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		//调用购物车上的清空购物车方法
		cart.clearCart();
		//重新定向到/jsp/cart.jsp
		resp.sendRedirect("/store_v5/jsp/cart.jsp");
		return null;
	}
}




