package pl.pollub.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.pollub.service.ApplicationMoviesListener;
import pl.pollub.service.ex.MovieNotFoundException;
import pl.pollub.service.model.Movie;
import pl.pollub.service.model.MovieList;
import pl.pollub.service.repository.EMovieSpecifications;
import pl.pollub.service.repository.MovieRepository;

import java.util.List;

import static pl.pollub.service.repository.SpecificationResolver.resolve;

@ControllerAdvice
@RestController
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ApplicationMoviesListener listener;

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public Movie movie(@PathVariable("id") String id) throws MovieNotFoundException {
        Movie movie = repository.findOne(id);
        if (movie == null)
            throw new MovieNotFoundException(id);
        return movie;
    }

    @RequestMapping("/movies/init")
    public void initMovies() {
        listener.init();
    }

    @RequestMapping("/movies/drop")
    public void dropMovies() {
        repository.deleteAll();
    }

    @RequestMapping("/movies")
    public MovieList moviesListSelect(@RequestParam(required = false, defaultValue = "ALL") EMovieSpecifications filter,
                                      @RequestParam(required = false) String value,
                                      @RequestParam(required = false) Sort.Direction direction,
                                      @RequestParam(required = false) String order,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {

        if (isSortable(direction, order) && isPageable(page, size)) {
            Sort sort = new Sort(direction, order);
            Pageable pageable = new PageRequest(page, size, sort);
            List<Movie> movies = repository.findAll(resolve(filter, value), pageable).getContent();
            return new MovieList(movies);
        }

        if (isSortable(direction, order)) {
            Sort sort = new Sort(direction, order);
            List<Movie> movies = repository.findAll(resolve(filter, value), sort);
            return new MovieList(movies);
        }

        if (isPageable(page, size)) {
            Pageable pageable = new PageRequest(page, size);
            List<Movie> movies = repository.findAll(resolve(filter, value), pageable).getContent();
            return new MovieList(movies);
        }

        return new MovieList(repository.findAll(resolve(filter, value)));
    }

    private boolean isSortable(Sort.Direction direction, String order) {
        return direction != null && order != null;
    }

    private boolean isPageable(Integer page, Integer size) {
        return page != null && size != null && page >= 0 && size > 0;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.DELETE)
    public void moviesListDelete() {
        repository.deleteAll();
    }

    @RequestMapping("/movies/count")
    public long moviesCount() {
        return repository.count();
    }
}