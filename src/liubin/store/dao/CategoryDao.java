package liubin.store.dao;

import java.util.List;

import liubin.store.domain.Category;

public interface CategoryDao {

	List<Category> getAllCats()throws Exception;

	void addCategory(Category c)throws Exception;

}
