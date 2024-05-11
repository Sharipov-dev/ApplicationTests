package proselyte.com.testingGradle.service;

import proselyte.com.testingGradle.entity.DeveloperEntity;

import java.util.List;

public interface IDeveloperService {
    DeveloperEntity saveDeveloper(DeveloperEntity developer);
    DeveloperEntity updateDeveloper(DeveloperEntity developer);
    DeveloperEntity getDeveloperById(Integer id);
    DeveloperEntity getDeveloperByEmail(String email);
    List<DeveloperEntity> getAllDevelopers();
    List<DeveloperEntity> getAllActiveBySpeciality(String speciality);

    void softDeleteById(Integer id);
    void hardDeleteById(Integer id);

}
