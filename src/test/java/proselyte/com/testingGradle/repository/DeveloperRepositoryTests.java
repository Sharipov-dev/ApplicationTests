package proselyte.com.testingGradle.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.CollectionUtils;
import proselyte.com.testingGradle.entity.DeveloperEntity;
import proselyte.com.testingGradle.entity.Status;
import proselyte.com.testingGradle.utils.DataUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DeveloperRepositoryTests {

    @Autowired
    private DeveloperRepository developerRepository;

    @BeforeEach
    public void setUp(){
        developerRepository.deleteAll();
    }

    @Test
    @DisplayName("Test save developer functionality")
    public void givenDeveloperObject_whenSave_thenDeveloperIsCreated(){
        //given
        DeveloperEntity developerToSave = DataUtils.getJohnDoeTransient();

        //when
        DeveloperEntity savedDeveloper = developerRepository.save(developerToSave);
        //then
        assertThat(savedDeveloper).isNotNull();
        assertThat(savedDeveloper.getId()).isNotNull();

    }


    @Test
    @DisplayName("Test update developer functionality")
    public void givenDeveloperToUpdate_whenSave_thenEmailIsChanged(){

        //given
        DeveloperEntity developerToCreate = DataUtils.getJohnDoeTransient();
        String updatedEmail = "upadted@gmail.com";
        developerRepository.save(developerToCreate);

        //when
        DeveloperEntity developerToUpdate = developerRepository.findById(developerToCreate.getId())
                .orElse(null);
        developerToUpdate.setEmail(updatedEmail);
        DeveloperEntity updatedDeveloper = developerRepository.save(developerToCreate);

        //then
        assertThat(updatedDeveloper).isNotNull();
        assertThat(updatedDeveloper.getEmail()).isEqualTo(updatedEmail);

    }

    @Test
    @DisplayName("Test get developer by Id")
    public void givenDeveloperIsCreated_whenGetById_thenDeveloperIsReturned(){

        //given
        DeveloperEntity developerToCreate = DataUtils.getJohnDoeTransient();
        developerRepository.save(developerToCreate);

        //when
        DeveloperEntity foundDeveloper = developerRepository.findById(developerToCreate.getId())
                .orElse(null);

        //then
        assertThat(foundDeveloper).isNotNull();
        assertThat(foundDeveloper.getEmail()).isEqualTo(developerToCreate.getEmail());
    }

    @Test
    @DisplayName("Test developer not found functionality")
    public void givenDeveloperIsNotCreated_whenGetById_thenOptionalIsEmpty(){

        //given


        //when
        DeveloperEntity foundDeveloper = developerRepository.findById(1)
                .orElse(null);


        //then
        assertThat(foundDeveloper).isNull();

    }

    @Test
    @DisplayName("Test get all developers functionality+")
    public void givenDevelopers_whenGetAllById_thenDevelopersAreReturned(){

        //given
        developerRepository.saveAll(DataUtils.getAllTransient());

        //when
        List<DeveloperEntity> foundDevelopers = developerRepository.findAll();

        //then
        assertThat(CollectionUtils.isEmpty(foundDevelopers)).isFalse();

    }

    @Test
    @DisplayName("Test get developer by email functionality")
    public void givenDeveloperIsSaved_whenGetByEmail_thenDeveloperIsReturned(){

        //given
        DeveloperEntity developerToSave = DataUtils.getJohnDoeTransient();
        developerRepository.save(developerToSave);

        //when
        DeveloperEntity foundDeveloper = developerRepository.findByEmail(developerToSave.getEmail())
                .orElse(null);


        //then
        assertThat(foundDeveloper).isNotNull();
        assertThat(foundDeveloper.getEmail()).isEqualTo(developerToSave.getEmail());

    }

    @Test
    @DisplayName("Test get all active developers by speciality")
    public void givenThreeDevelopersAndTwoAreActive_whenGetAllActiveBySpeciality_thenReturnOnlyTwoDevelopers(){

        //given
        developerRepository.saveAll(DataUtils.getAllTransient());

        //when
        List<DeveloperEntity> foundList = developerRepository.findAllBySpeciality("Java");

        //then
        assertThat(CollectionUtils.isEmpty(foundList)).isFalse();
        assertThat(foundList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test Delete Developer by id functionality")
    public void givenDeveloperIsSaved_whenDeleteById_thenDeveloperIsDeleted(){

        //given
        DeveloperEntity developerToSave = DataUtils.getJohnDoeTransient();
        developerRepository.save(developerToSave);
        //when
        developerRepository.deleteById(developerToSave.getId());
        //then
        assertThat(developerRepository.findById(developerToSave.getId()).orElse(null)).isNull();
    }
}
