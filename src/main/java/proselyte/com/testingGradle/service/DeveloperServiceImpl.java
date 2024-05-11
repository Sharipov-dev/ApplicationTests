package proselyte.com.testingGradle.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import proselyte.com.testingGradle.entity.DeveloperEntity;
import proselyte.com.testingGradle.entity.Status;
import proselyte.com.testingGradle.exception.DeveloperNotFoundException;
import proselyte.com.testingGradle.exception.DeveloperWithDuplicateEmailException;
import proselyte.com.testingGradle.repository.DeveloperRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements IDeveloperService{

    private final DeveloperRepository developerRepository;
    @Override
    public DeveloperEntity saveDeveloper(DeveloperEntity developer) {
        Optional<DeveloperEntity> duplicateFoundDeveloper = developerRepository.findByEmail(developer.getEmail());
        if(duplicateFoundDeveloper.isPresent()){
            throw new DeveloperWithDuplicateEmailException(String.format("Developer with such email aready exists: %s",developer.getEmail()));
        } else {
            return developerRepository.save(duplicateFoundDeveloper.get());
        }
    }

    @Override
    public DeveloperEntity updateDeveloper(DeveloperEntity developer) {
        boolean isExists = developerRepository.existsById(developer.getId());

        if(isExists){
            DeveloperEntity foundDeveloper = developerRepository.findByEmail(developer.getEmail())
                    .orElseThrow(
                            () -> new DeveloperNotFoundException("")
                    );
            foundDeveloper.setEmail(developer.getEmail());
            foundDeveloper.setSpeciality(developer.getSpeciality());
            foundDeveloper.setFirstName(developer.getFirstName());
            foundDeveloper.setLastName(developer.getLastName());
            foundDeveloper.setStatus(developer.getStatus());
            return developerRepository.save(foundDeveloper);
        } else {
            throw new DeveloperNotFoundException("");
        }


    }

    @Override
    public DeveloperEntity getDeveloperById(Integer id) {
        Optional<DeveloperEntity> foundDeveloper = developerRepository.findById(id);
        if (foundDeveloper.isEmpty()){
            throw new DeveloperNotFoundException("");
        } else{
            return foundDeveloper.get();
        }
    }

    @Override
    public DeveloperEntity getDeveloperByEmail(String email) {
        Optional<DeveloperEntity> foundDeveloper = developerRepository.findByEmail(email);
        if (foundDeveloper.isEmpty()){
            throw new DeveloperNotFoundException("");
        } else{
            return foundDeveloper.get();
        }
    }

    @Override
    public List<DeveloperEntity> getAllDevelopers() {
        return developerRepository
                .findAll()
                .stream()
                .filter((d) -> {
                    return d.getStatus().equals(Status.ACTIVE);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<DeveloperEntity> getAllActiveBySpeciality(String speciality) {
        return developerRepository.findAllBySpeciality(speciality);
    }

    @Override
    public void softDeleteById(Integer id) {
        DeveloperEntity foundDeveloper = developerRepository.findById(id)
                .orElseThrow(() -> new DeveloperNotFoundException(""));
        foundDeveloper.setStatus(Status.DELETED);
        developerRepository.save(foundDeveloper);
    }

    @Override
    public void hardDeleteById(Integer id) {
        DeveloperEntity foundDeveloper = developerRepository.findById(id)
                .orElseThrow(() -> new DeveloperNotFoundException(""));
        developerRepository.deleteById(foundDeveloper.getId());
    }
}
