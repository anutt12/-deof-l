package com.odeofil.odeofil.repository;

import com.odeofil.odeofil.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Library findById(Long id);
    List<Library> findByUserProfileId(Long userId);
    Library findLibraryIdAndUserId(Long libraryId, Long userId);

}
