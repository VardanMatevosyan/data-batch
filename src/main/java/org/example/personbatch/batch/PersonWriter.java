package org.example.personbatch.batch;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.personbatch.entity.PersonEntity;
import org.example.personbatch.repository.PersonEntityRepository;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@StepScope
@Component
public class PersonWriter implements ItemWriter<PersonEntity> {

  PersonEntityRepository personEntityRepository;

  @Override
  public void write(Chunk<? extends PersonEntity> chunk) {
    log.info("Writing person entities to the database ");
    personEntityRepository.saveAll(chunk.getItems());
  }

}
