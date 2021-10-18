package com.odeofil.odeofil.repository;

import com.odeofil.odeofil.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Album findAlbumByName(String albumName);

    List<Album> findByLibraryId(Long albumId);

    Album findByNameAndUserIdAndIdIsNot(String name, Long id, Long albumId);

    Album findByNameAndUserId(String name, Long userId);


}
