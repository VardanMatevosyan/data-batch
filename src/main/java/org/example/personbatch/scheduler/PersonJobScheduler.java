package org.example.personbatch.scheduler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersonJobScheduler {

  JobLauncher jobLauncher;
  Job job;

  @Scheduled(fixedDelay = 2000, initialDelay = 2000)
  public void runPersonJob() throws Exception {
    log.info("Start running person job");
    jobLauncher.run(job, new JobParameters());
    log.info("Finished running person job");
  }

}
