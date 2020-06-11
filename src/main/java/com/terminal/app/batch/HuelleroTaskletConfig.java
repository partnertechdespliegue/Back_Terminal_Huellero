package com.terminal.app.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableBatchProcessing
public class HuelleroTaskletConfig {
	
	@Autowired
	public JobBuilderFactory jobbuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepbuilderFactory;
	
	@Bean
	@Primary
	public Tasklet crearHulleroTasklet() {
		return new HuelleroTasklet();
	}
	
	@Bean
	public Step HulleroTaskletStep() {
		return stepbuilderFactory
				.get("HulleroTaskletStep")
				.tasklet(crearHulleroTasklet())
				.build();
	}
	
	@Bean
	public Job HulleroJob() {
		return jobbuilderFactory
				.get("HulleroJob")
				.start(this.HulleroTaskletStep())
				.build();
	}

}
