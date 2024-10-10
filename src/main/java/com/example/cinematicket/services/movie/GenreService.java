package com.example.cinematicket.services.movie;

import com.example.cinematicket.dtos.requests.GenreRequest;
import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.entities.Genre;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.GenreMapper;
import com.example.cinematicket.repositories.GenreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreService implements IGenreService {
    GenreRepository genreRepository;
    GenreMapper genreMapper;
    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public GenreResponse createGenre(GenreRequest request) {
        if(genreRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.GENRE_EXISTED);

        Genre genre = genreMapper.toRoomType(request);
        return genreMapper.toRoomTypeResponse(genreRepository.save(genre));
    }

    @Override
    public GenreResponse findById(Long id) {
        Genre genre = genreRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.GENRE_NOT_EXISTED));

        return genreMapper.toRoomTypeResponse(genre);
    }

    @Override
    public Page<GenreResponse> getAllGenre(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return genreRepository.findAll(pageable).map(genreMapper::toRoomTypeResponse);
    }

    @Override
    public Page<GenreResponse> searchGenre(String search, int page, int limit) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public GenreResponse updateGenre(Long id, GenreRequest request) {
        Genre genre = genreRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.GENRE_NOT_EXISTED));

        genreMapper.updateGere(genre, request);
        return genreMapper.toRoomTypeResponse(genreRepository.save(genre));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
