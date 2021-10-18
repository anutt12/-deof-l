package com.odeofil.odeofil.repository;

import com.odeofil.odeofil.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findById(Long id);

    Artist findByNameAndUserIdAndIdIsNot(String name, Long id, Long artistId);

    Artist findByNameAndUserId(String name, Long userId);

//    Artist findByNameAndIdIsNot(String artistName, Long artistId);
//
//    Artist findByName(String artistName);

//    Artist findByNameAndArtistId(String name, Long artistId);

//    Artist findByUserIdAndName(Long userId, String artistName);


}
