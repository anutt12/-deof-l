package com.odeofil.odeofil.service;

import com.odeofil.odeofil.exception.InformationExistException;
import com.odeofil.odeofil.exception.InformationNotFoundException;
import com.odeofil.odeofil.model.Album;
import com.odeofil.odeofil.model.Artist;
import com.odeofil.odeofil.model.Library;
import com.odeofil.odeofil.repository.AlbumRepository;
import com.odeofil.odeofil.repository.ArtistRepository;
import com.odeofil.odeofil.repository.LibraryRepository;
import com.odeofil.odeofil.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private LibraryRepository libraryRepository;
    private ArtistRepository artistRepository;
    private AlbumRepository albumRepository;

    @Autowired
    public void setLibraryRepository(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

//    @Autowired
//    public void setAlbumRepository(AlbumRepository albumRepository) {this.albumRepository = albumRepository;}

    public List<Library> getLibraries(){
        System.out.println("service calling getLibraries ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Library> libraryList = libraryRepository.findByUserId(userDetails.getUser().getId());
        if (libraryList.isEmpty()) {
            throw new InformationNotFoundException("no libraries found for user id " + userDetails.getUser().getId());
        } else {
            return libraryList;
        }
    }

    public Library getLibrary(Long libraryId) {
        System.out.println("service calling getLibraries");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("no libraries found for " + userDetails.getUser().getId());
        } else {
            return library;
        }
    }

    public Library createLibrary(Library libraryObject) {
        System.out.println("calling createLibrary()");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        Library library = libraryRepository.findByUserIdAndName(userDetails.getUser().getId(), libraryObject.getName());
        if (library != null) {
            throw new InformationExistException("library with the id " + libraryObject.getId() + " already exists");
        } else {
            libraryObject.setId(userDetails.getUser().getId());
            return libraryRepository.save(libraryObject);
        }
    }

    public Library updateLibrary(Long libraryId, Library libraryObject) {
        System.out.println("service calling updateLibrary()");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("library with id " + libraryId + " not found");
        } else {
            library.setUser(userDetails.getUser());
            library.setDescription(libraryObject.getDescription());
            library.setName(libraryObject.getName());
            return libraryRepository.save(library);
        }
    }

    public String deleteLibrary(Long libraryId) {
        System.out.println("service calling delete Library");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if(library == null){
            throw new InformationNotFoundException("library with the id " + libraryId + " not found");
        } else {
            libraryRepository.deleteById(libraryId);
            return "Library with the id " + libraryId + " has been successfully deleted";
        }
    }

    //*****Service for Artist Class*****//

    public Artist createLibraryArtist(Long libraryId, Artist artistObject) {
        System.out.println("service calling createLibraryArtist ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException(
                    "library with id " + libraryId + " does not belong to this user or library does not exist");
        }
        Artist artist = artistRepository.findByNameAndUserId(artistObject.getName(), userDetails.getUser().getId());
        if (artist != null) {
            throw new InformationExistException("artist with name " + artist.getName() + " already exists");
        }
        artistObject.setUser(userDetails.getUser());
        artistObject.setLibrary(library);
        return artistRepository.save(artistObject);
    }

    public List<Artist> getLibraryArtists(Long libraryId){
        System.out.println("Calling the service for the view");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null){
            throw new InformationNotFoundException("library with id " + libraryId + " " +
                    "does not belong to this user or library does not exist");
        }
        return library.getArtistList();
    }

    public Artist updateLibraryArtists(Long libraryId, Long artistId, Artist artistObject) {
        System.out.println("invoking the Services Method updateLibraryArtists");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("library with id " + libraryId +
                    " does not belong to this user or library does not exist");
        }
        Optional<Artist> artist = artistRepository.findByLibraryId(
                libraryId).stream().filter(p -> p.getId().equals(libraryId)).findFirst();
        if (!artist.isPresent()) {
            throw new InformationNotFoundException("artist with id " + artistId +
                    " does not belong to this user or artist does not exist");
        }
        Artist oldArtist = artistRepository.findByNameAndUserIdAndIdIsNot(
                artistObject.getName(), userDetails.getUser().getId(), artistId);
        if (oldArtist != null) {
            throw new InformationExistException("artist with name " + oldArtist.getName() + " already exists");
        }
        artist.get().setName(artistObject.getName());
        artist.get().setDescription(artistObject.getDescription());
        return artistRepository.save(artist.get());
    }

    public void deleteLibraryArtist(Long libraryId, Long artistId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("library with id " + libraryId +
                    " doest not belong to this user or library does not exist");
        }
        Optional<Artist> artist = artistRepository.findByLibraryId(
                libraryId).stream().filter(p -> p.getId().equals(artistId)).findFirst();
        if (!artist.isPresent()) {
            throw new InformationNotFoundException("artist with id " + artistId +
                    " does not belong to this user or artist does not exist");
        }
        artistRepository.deleteById(artist.get().getId());
    }

    public Artist getLibraryArtist(Long libraryId, Long artistId) {
        System.out.println("service calling getLibraryArtist");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("library with id " + libraryId +
                    " doest not belong to this user or library does not exist");
        }
        Optional<Artist> artist = artistRepository.findByLibraryId(
                libraryId).stream().filter(p -> p.getId().equals(artistId)).findFirst();
        if (!artist.isPresent()) {
            throw new InformationNotFoundException("artist with id " + artistId +
                    " does not belong to this user or artist does not exist");
        }
        return artist.get();
    }

    //*****Service for Album Class*****//

    public Album createLibraryAlbum(Long libraryId, Album albumObject) {
        System.out.println("service calling createLibraryAlbum ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException(
                    "library with id " + libraryId + " does not belong to this user or library does not exist");
        }
        Album album = albumRepository.findByNameAndUserId(albumObject.getName(), userDetails.getUser().getId());
        if (album != null) {
            throw new InformationExistException("album with name " + album.getName() + " already exists");
        }
        albumObject.setUser(userDetails.getUser());
        albumObject.setLibrary(library);
        return albumRepository.save(albumObject);
    }

    public List<Album> getLibraryAlbums(Long libraryId){
        System.out.println("Calling the service for the view");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null){
            throw new InformationNotFoundException("library with id " + libraryId + " " +
                    "does not belong to this user or library does not exist");
        }
        return library.getAlbumList();
    }

    public Album getLibraryAlbum(Long libraryId, Long albumId) {
        System.out.println("service calling getLibraryAlbum");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("library with id " + libraryId +
                    " doest not belong to this user or library does not exist");
        }
        Optional<Album> album = albumRepository.findByLibraryId(
                libraryId).stream().filter(p -> p.getId().equals(albumId)).findFirst();
        if (!album.isPresent()) {
            throw new InformationNotFoundException("album with id " + albumId +
                    " does not belong to this user or album does not exist");
        }
        return album.get();
    }

    public Album updateLibraryAlbums(Long libraryId, Long albumId, Album albumObject) {
        System.out.println("invoking the Services Method updateLibraryAlbums");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("library with id " + libraryId +
                    " does not belong to this user or library does not exist");
        }
        Optional<Album> album = albumRepository.findByLibraryId(
                libraryId).stream().filter(p -> p.getId().equals(libraryId)).findFirst();
        if (!album.isPresent()) {
            throw new InformationNotFoundException("album with id " + albumId +
                    " does not belong to this user or album does not exist");
        }
        Album oldAlbum = albumRepository.findByNameAndUserIdAndIdIsNot(
                albumObject.getName(), userDetails.getUser().getId(), albumId);
        if (oldAlbum != null) {
            throw new InformationExistException("album with name " + oldAlbum.getName() + " already exists");
        }
        album.get().setName(albumObject.getName());
        album.get().setDescription(albumObject.getDescription());
        return albumRepository.save(album.get());
    }

    public void deleteLibraryAlbum(Long libraryId, Long albumId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Library library = libraryRepository.findByLibraryIdAndUserId(libraryId, userDetails.getUser().getId());
        if (library == null) {
            throw new InformationNotFoundException("library with id " + libraryId +
                    " doest not belong to this user or library does not exist");
        }
        Optional<Album> album = albumRepository.findByLibraryId(
                libraryId).stream().filter(p -> p.getId().equals(albumId)).findFirst();
        if (!album.isPresent()) {
            throw new InformationNotFoundException("album with id " + albumId +
                    " does not belong to this user or album does not exist");
        }
        albumRepository.deleteById(album.get().getId());
    }

}
