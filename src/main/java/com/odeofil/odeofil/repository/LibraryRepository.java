package com.odeofil.odeofil.repository;

import com.odeofil.odeofil.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Library findByName(String libraryName);

    List<Library> findByUserId(Long userId);

    Library findByLibraryIdAndUserId(Long libraryId, Long userId);

    Library findByUserIdAndName(Long userId, String libraryName);

    Library findByIdAndUserId(Long id, Long userId);

}


