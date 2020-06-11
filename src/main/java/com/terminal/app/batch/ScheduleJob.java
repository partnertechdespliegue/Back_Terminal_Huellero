package com.terminal.app.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.terminal.app.exception.ExceptionResponse;

@Component
public class ScheduleJob {
	
	@Autowired
    private JobLauncher launcher;
     
    @Autowired
    private Job HuelleroJob;
    
    private JobExecution execution;
    
    // Aca modifico la hora de ejecución del bachero
 	// "0 0 * * * *" = the top of every hour of every day.
 	// "*/10 * * * * *" = every ten seconds.
 	// "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
 	// "0 0 8,10 * * *" = 8 and 10 o'clock of every day.
 	// "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
 	// "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
 	// "0 0 0 25 12 ?" = every Christmas Day at midnight
    
    @Scheduled(cron="0 0 * * * *")
    public void runSchedule() {
		try {
			execution = launcher.run(HuelleroJob,
					new JobParametersBuilder().addLong("timestamp", System.currentTimeMillis()).toJobParameters());
			System.out.println("Execution status: " + execution.getStatus());
		} catch (JobExecutionAlreadyRunningException e) {
			new ExceptionResponse(new Date(), e.getMessage(), this.getClass().getSimpleName()
					+ " Error al ejecutar el Job Asistencia, ERROR: " + e.getStackTrace()[0].getClassName(), e);
		} catch (JobRestartException e) {
			new ExceptionResponse(new Date(), e.getMessage(), this.getClass().getSimpleName()
					+ " Error al ejecutar el Job Asistencia, ERROR: " + e.getStackTrace()[0].getClassName(), e);
		} catch (JobInstanceAlreadyCompleteException e) {
			new ExceptionResponse(new Date(), e.getMessage(), this.getClass().getSimpleName()
					+ " Error al ejecutar el Job Asistencia, ERROR: " + e.getStackTrace()[0].getClassName(), e);
		} catch (JobParametersInvalidException e) {
			new ExceptionResponse(new Date(), e.getMessage(), this.getClass().getSimpleName()
					+ " Error al ejecutar el Job Asistencia, ERROR: " + e.getStackTrace()[0].getClassName(), e);
		}
	}

}
