package org.example.personbatch.service.impl;

import java.util.List;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.Assertions;
import org.example.personbatch.entity.PersonEntity;
import org.example.personbatch.repository.PersonEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

  @Mock
  PersonEntityRepository personEntityRepository;

  @InjectMocks
  PersonServiceImpl personService;

  @Test
  void getAllPersons() {
    List<PersonEntity> expectedUsers = getUsers();

    Mockito
        .when(personEntityRepository.findAll())
        .thenReturn(expectedUsers);

    List<PersonEntity> users = personService.findAll();

    Assertions
        .assertThat(users.get(0).getUsername())
        .isEqualTo(expectedUsers.get(0).getUsername());
  }

  private static List<PersonEntity> getUsers() {
    return List.of(PersonEntity.builder().username(RandomString.make(12)).build());
  }
}
