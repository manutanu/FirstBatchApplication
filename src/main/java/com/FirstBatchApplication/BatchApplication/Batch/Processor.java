package com.FirstBatchApplication.BatchApplication.Batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.FirstBatchApplication.BatchApplication.Model.User;

@Component
public class Processor implements ItemProcessor<User,User>{

	private static final Map<String,String> DEPT_NAMES=new HashMap<>();
	
	public Processor() {
		DEPT_NAMES.put("001","Mathematics");
		DEPT_NAMES.put("002","Science");
		DEPT_NAMES.put("003","Physics");
	}
	
	@Override
	public User process(User item) throws Exception {
		
		String dept=DEPT_NAMES.get(item.getDept());
		item.setDept(dept);
		return item;
	}
	
	
}
