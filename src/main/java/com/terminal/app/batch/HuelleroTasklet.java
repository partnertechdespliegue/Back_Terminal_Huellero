package com.terminal.app.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.terminal.app.HuelleroTerminal;
import com.terminal.app.HuelleroTerminalApplication;

public class HuelleroTasklet implements Tasklet, StepExecutionListener{

	@Autowired
	HuelleroTerminal application;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try {
			application.gestionarHuellero();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		return RepeatStatus.FINISHED;
	}	

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

}
