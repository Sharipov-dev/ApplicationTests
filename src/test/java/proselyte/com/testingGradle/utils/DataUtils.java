package proselyte.com.testingGradle.utils;


import proselyte.com.testingGradle.entity.DeveloperEntity;
import proselyte.com.testingGradle.entity.Status;

import java.util.List;

public class DataUtils {

    public static List<DeveloperEntity> getAllTransient(){
        return List.of(
                getJohnDoeTransient(),
                getFrankJonesTransient(),
                getMikeSmithTransient());
    }

    public static DeveloperEntity getJohnDoeTransient(){
        return DeveloperEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .speciality("Java")
                .email("john.doe@gmail.com")
                .status(Status.ACTIVE)
                .build();
    }

    public static DeveloperEntity getMikeSmithTransient(){
        return DeveloperEntity.builder()
                .firstName("Mike")
                .lastName("Smith")
                .speciality("Java")
                .email("mike.smith@gmail.com")
                .status(Status.ACTIVE)
                .build();
    }
    public static DeveloperEntity getFrankJonesTransient(){
        return DeveloperEntity.builder()
                .firstName("Frank")
                .lastName("Jones")
                .speciality("Java")
                .email("frank.jones@gmail.com")
                .status(Status.DELETED)
                .build();
    }

    public static DeveloperEntity getJohnDoePersisted(){
        return DeveloperEntity.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .speciality("Java")
                .email("john.doe@gmail.com")
                .status(Status.ACTIVE)
                .build();
    }
    public static DeveloperEntity getMikeSmithPersisted(){
        return DeveloperEntity.builder()
                .id(2)
                .firstName("Mike")
                .lastName("Smith")
                .speciality("Java")
                .email("mike.smith@gmail.com")
                .status(Status.ACTIVE)
                .build();
    }
    public static DeveloperEntity getFrankJonesPersisted(){
        return DeveloperEntity.builder()
                .id(3)
                .firstName("Frank")
                .lastName("Jones")
                .speciality("Java")
                .email("frank.jones@gmail.com")
                .status(Status.DELETED)
                .build();
    }
}
