package com.FirstBatchApplication.BatchApplication.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	//we have to trigger the joblauncher which inturn trigger the job
	@Autowired
	JobLauncher joblauncher;
	
	@Autowired
	Job job;
	
	@GetMapping(value = "/loadbatch")
	public void  load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> parameters=new HashMap<>();
		parameters.put("time",new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters=new JobParameters(parameters);
		JobExecution jobexceution=(JobExecution) joblauncher.run(job, jobParameters);
		System.out.println("job execution "+jobexceution.getStatus());
	}
	
	
}
