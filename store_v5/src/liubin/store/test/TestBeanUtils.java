package liubin.store.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.junit.Test;

import liubin.store.domain.User;

public class TestBeanUtils {

	@Test
	public void test01() throws Exception{
		//模拟request.getParameterMap();
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("username", new String[]{"tom"});
		map.put("password", new String[]{"1234"});
		
		//创建用户对象
		User user=new User();
		//利用BeanUtils自动填充数据
		BeanUtils.populate(user, map);
		//打印user对象
		System.out.println(user);
		
	}
	
	@Test
	public void test02() throws Exception{
		//模拟request.getParameterMap();
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("username", new String[]{"tom"});
		map.put("password", new String[]{"1234"});
		map.put("birthday", new String[]{"1992-3-3"});
		
		User user=new User();
		
		
		//BeanUtils找到User.class文件上有setBirthday这个方法,要执行,将"1992-3-3"转换为时间日期类型
		//BeanUtils不知道这个字符串的时间格式是什么?以下三行代码设置时间转换格式
		// 1_创建时间类型的转换器
		DateConverter dt = new DateConverter();
		// 2_设置转换的格式
		dt.setPattern("yyyy-MM-dd");
		// 3_注册转换器
		ConvertUtils.register(dt, java.util.Date.class);
		
		BeanUtils.populate(user, map);
		System.out.println(user);
		
	}
}
