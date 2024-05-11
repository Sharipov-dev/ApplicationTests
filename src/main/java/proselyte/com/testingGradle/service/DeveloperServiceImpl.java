package proselyte.com.testingGradle.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import proselyte.com.testingGradle.entity.DeveloperEntity;
import proselyte.com.testingGradle.repository.DeveloperRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements IDeveloperService{

    private final DeveloperRepository developerRepository;
    @Override
    public DeveloperEntity saveDeveloper(DeveloperEntity developer) {
        return null;
    }

    @Override
    public DeveloperEntity updateDeveloper(DeveloperEntity developer) {
        return null;
    }

    @Override
    public DeveloperEntity getDeveloperById(Integer id) {
        return null;
    }

    @Override
    public DeveloperEntity getDeveloperByEmail(String email) {
        return null;
    }

    @Override
    public List<DeveloperEntity> getAllDevelopers() {
        return null;
    }

    @Override
    public List<DeveloperEntity> getAllActiveBySpeciality(String speciality) {
        return null;
    }

    @Override
    public void softDeleteById(Integer id) {

    }

    @Override
    public void hardDeleteById(Integer id) {

    }
}
