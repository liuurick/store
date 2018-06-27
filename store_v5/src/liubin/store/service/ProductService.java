package liubin.store.service;

import java.util.List;

import liubin.store.domain.PageModel;
import liubin.store.domain.Product;

public interface ProductService {

	List<Product> findHots()throws Exception;

	List<Product> findNews()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception;

	PageModel findAllProductsWithPage(int curNum)throws Exception;

}
