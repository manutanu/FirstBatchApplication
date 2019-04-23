package com.FirstBatchApplication.BatchApplication.Batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.FirstBatchApplication.BatchApplication.Model.User;
import com.FirstBatchApplication.BatchApplication.Repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<User>{

	@Autowired
	UserRepository userrepo;
	
	@Override
	public void write(List<? extends User> items) throws Exception {
		System.out.println("item saved "+ items);
		userrepo.save(items);
	}

}
