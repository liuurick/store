package liubin.store.service.serviceImp;

import java.util.List;

import liubin.store.dao.CategoryDao;
import liubin.store.dao.daoImp.CategoryDaoImp;
import liubin.store.domain.Category;
import liubin.store.service.CategoryService;
import liubin.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;

public class CategoryServiceImp implements CategoryService {

	CategoryDao CategoryDao=new CategoryDaoImp();
	@Override
	public List<Category> getAllCats() throws Exception {
		return CategoryDao.getAllCats();
	}

	@Override
	public void addCategory(Category c) throws Exception {
		//本质是向MYSQL插入一条数据
		CategoryDao.addCategory(c);
		//更新redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}

}
