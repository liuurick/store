package liubin.store.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import liubin.store.domain.OrderItem;
import liubin.store.domain.Product;
import liubin.store.utils.JDBCUtils;

public class TestMapListHandler {
	
	@Test
	public void testForeachMap(){
		Map<String,String> map=new HashMap<String,String>();
		map.put("111", "aaaa");
		map.put("222", "bbbb");
		map.put("333", "ccccc");
		
		//增强的for循环遍历map
		for(Map.Entry<String, String> entry :map.entrySet()){
			System.out.println(entry.getKey() +"   "+entry.getValue());
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	///根据订单id查询每笔订单下的订单项以及订单项对应的商品信息
	
	//SQL分析过程
	//SELECT * FROM orderitem , product   //笛卡尔积
	//SELECT * FROM orderitem o, product p WHERE  o.pid=p.pid  //笛卡尔积基础上筛选有意义的数据
	//最终的结果
	//SELECT * FROM orderitem o, product p WHERE  o.pid=p.pid AND oid='090330CE64CC4083AB8426502CCD5D74'
	@Test
	public void test00() throws Exception{
		List<OrderItem> list01=new ArrayList<OrderItem>();
		
		String sql="SELECT * FROM orderitem o, product p WHERE  o.pid=p.pid AND oid='090330CE64CC4083AB8426502CCD5D74'";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		//由于返回的数据是来自多个表,多行数据 MapListHandler封装返回的数据
		//返回结果:List,list中的每个元素是Map
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
		//遍历List
		for (Map<String, Object> map : list) {
			//输出map中的内容
			//增强的for循环输出map内容
			//for(Map.Entry<String, Object> entry:map.entrySet()){
			//System.out.print(entry.getKey()+":"+entry.getValue()+"  ");
			//}
			//System.out.println();
			
			//total:4199.0  pid:33  itemid:34CDD987F4E649CDA373EFFB5A3BEC69  cid:2  pdate:2015-11-02  shop_price:4199.0  market_price:4399.0  oid:090330CE64CC4083AB8426502CCD5D74  pflag:0  pname:联想（ThinkPad） 轻薄系列E450C(20EH0001CD)  pimage:products/1/c_0033.jpg  is_hot:0  quantity:1  pdesc:联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)  
			//total:2298.0  pid:11  itemid:EEEA9BA4E1244BD5BB01AD082D197AD4  cid:1  pdate:2015-11-02  shop_price:2298.0  market_price:2399.0  oid:090330CE64CC4083AB8426502CCD5D74  pflag:0  pname:vivo X5Pro  pimage:products/1/c_0014.jpg  is_hot:1  quantity:1  pdesc:移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术  
			OrderItem orderItem=new OrderItem();
			Product product=new Product();
			
			// 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格式
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换器
			ConvertUtils.register(dt, java.util.Date.class);
			
			//将map中属于orderItem的数据自动填充到orderItem对象上
			BeanUtils.populate(orderItem, map);
			//将map中属于product的数据自动填充到product对象上
			BeanUtils.populate(product, map);
			
			//让orderItem和product发生关联关系
			orderItem.setProduct(product);
			//
			list01.add(orderItem);
		}
		
	}
}
