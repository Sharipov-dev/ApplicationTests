package proselyte.com.testingGradle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proselyte.com.testingGradle.entity.DeveloperEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Integer> {

    Optional<DeveloperEntity> findByEmail(String email);

    @Query("SELECT d FROM DeveloperEntity d WHERE d.status = 'ACTIVE' AND d.speciality=?1")
    List<DeveloperEntity> findAllBySpeciality(String speciality);
}
