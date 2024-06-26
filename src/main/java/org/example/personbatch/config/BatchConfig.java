package org.example.personbatch.config;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.personbatch.entity.PersonEntity;
import org.example.personbatch.batch.PersonSexProcessor;
import org.example.personbatch.batch.PersonUsernameProcessor;
import org.example.personbatch.batch.PersonWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@SuppressFBWarnings(value = {"EI_EXPOSE_REP2"}, justification = "This is spring DI")
@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BatchConfig {

  PersonWriter personWriter;

  @Bean
  public Job personJob(JobRepository jobRepository, PlatformTransactionManager ptm) {
    return new JobBuilder("personJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(persoinJobChunkStep(jobRepository, ptm))
        .build();
  }


  @Bean
  public Step persoinJobChunkStep(JobRepository jobRepository, PlatformTransactionManager ptm) {
    return new StepBuilder("personJobChunkStep", jobRepository)
        .<PersonEntity, PersonEntity>chunk(50, ptm)
        .reader(personReader())
        .processor(personProcessor())
        .writer(personWriter)
        .build();
  }



  @Bean
  public ItemProcessor<PersonEntity, PersonEntity> personProcessor() {
    CompositeItemProcessor<PersonEntity, PersonEntity> cip = new CompositeItemProcessor<>();
    cip.setDelegates(List.of(new PersonUsernameProcessor(), new PersonSexProcessor()));
    return cip;
  }



  @Bean
  @StepScope
  public FlatFileItemReader<? extends PersonEntity> personReader() {
    return new FlatFileItemReaderBuilder<PersonEntity>()
        .name("personReader")
        .resource(new ClassPathResource("persons.csv"))
        .delimited()
        .names(new String[]{"username", "firstName", "lastName", "sex"})
        .linesToSkip(1)
        .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
          {
            setTargetType(PersonEntity.class);
          }
        })
        .build();
  }


}
