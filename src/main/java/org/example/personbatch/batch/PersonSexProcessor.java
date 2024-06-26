package org.example.personbatch.batch;

import lombok.extern.slf4j.Slf4j;
import org.example.personbatch.entity.PersonEntity;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class PersonSexProcessor implements ItemProcessor<PersonEntity, PersonEntity> {

  @Override
  public PersonEntity process(PersonEntity item) throws Exception {
    log.info("Start processing person sex {}", item);
    item.setSex(item.getSex().toUpperCase());
    log.info("Finished processing person sex {}", item);
    return item;
  }
}
