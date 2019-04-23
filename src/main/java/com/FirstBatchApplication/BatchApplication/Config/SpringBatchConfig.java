package com.FirstBatchApplication.BatchApplication.Config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.FirstBatchApplication.BatchApplication.Model.User;

@Configuration
public class SpringBatchConfig {
 
	//first to call a job from rest api we need to create a job
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,ItemReader<User> reader,
			ItemProcessor<User,User> processor,ItemWriter<User> writer) {
		
		Step step=stepBuilderFactory.get("ETL-Load").<User,User>chunk(100)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build() ;
		
		return jobBuilderFactory.get("ETL-Job").incrementer(new RunIdIncrementer()).start(step).build();
	}
	
	@Bean
	public FlatFileItemReader<User> fileItemReader(@Value("${input}")Resource resource){
		FlatFileItemReader<User> flatfilereader=new FlatFileItemReader<>();
		flatfilereader.setResource(resource);
		flatfilereader.setName("CSV-Loader");
		flatfilereader.setLinesToSkip(1);
		flatfilereader.setLineMapper(lineMapper());
		return flatfilereader;
	}
	
	@Bean
	public LineMapper<User> lineMapper(){
		
		DefaultLineMapper<User> linemapper=new DefaultLineMapper<User>();
		DelimitedLineTokenizer linetokenizer =new DelimitedLineTokenizer(",");
		linetokenizer.setNames(new String[] {"id","name","dept","salary"});
		linetokenizer.setStrict(false);
		
		linemapper.setLineTokenizer(linetokenizer);
		BeanWrapperFieldSetMapper<User> beanwrapper=new BeanWrapperFieldSetMapper<User>();
		beanwrapper.setTargetType(User.class);
		linemapper.setFieldSetMapper(beanwrapper);
		
		return linemapper;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
