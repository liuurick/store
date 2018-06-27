package liubin.store.test;

import java.util.ArrayList;
import java.util.List;

public class TestList {
	public static void main(String[] args) {
		Stu s1=new Stu("tom1","18");
		Stu s2=new Stu("tom2","18");
		Stu s3=new Stu("tom3","18");
		Stu s4=new Stu("tom4","18");
		Stu s5=new Stu("tom5","18");
		Stu s6=new Stu("tom6","18");
		
		List<Stu> list=new ArrayList<Stu>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s6);
		
		System.out.println(list.size());
		//list.remove(s1);
		//System.out.println(list.size());
		
		
		for (Stu s : list) {
			if(s.getName().equals("tom1")){
				list.remove(s);
			}
		}
		System.out.println(list.size());
		
	}
}
