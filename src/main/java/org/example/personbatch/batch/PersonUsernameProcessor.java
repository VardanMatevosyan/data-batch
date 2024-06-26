package org.example.personbatch.batch;

import lombok.extern.slf4j.Slf4j;
import org.example.personbatch.entity.PersonEntity;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class PersonUsernameProcessor implements ItemProcessor<PersonEntity, PersonEntity> {

  @Override
  public PersonEntity process(PersonEntity item) throws Exception {
    log.info("Start processing person username {}", item);
    item.setUsername(getModifiedUsername(item));
    log.info("Finished processing person username {}", item);
    return item;
  }

  private static String getModifiedUsername(PersonEntity item) {
    String firstLetter = item.getUsername().substring(0, 1).toUpperCase();
    String remainingLetters = item.getUsername().substring(1);
    return firstLetter + remainingLetters;
  }
}
