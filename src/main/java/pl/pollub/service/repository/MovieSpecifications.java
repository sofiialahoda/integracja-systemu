package pl.pollub.service.repository;

import org.springframework.data.jpa.domain.Specification;
import pl.pollub.service.model.Movie;

class MovieSpecifications {

    static Specification<Movie> hasAwards() {
        return (root, query, builder) -> builder.notEqual(root.get("awards"), "N/A");
    }

    static Specification<Movie> hasPoster() {
        return (root, query, builder) -> builder.notEqual(root.get("poster"), "N/A");
    }

    static Specification<Movie> hasGenre(String value) {
        return (root, query, builder) -> builder.like(root.get("genre"), value);
    }

    static Specification<Movie> hasDirector(String value) {
        return (root, query, builder) -> builder.like(root.get("director"), value);
    }

    static Specification<Movie> hasWriter(String value) {
        return (root, query, builder) -> builder.like(root.get("writer"), value);
    }

    static Specification<Movie> hasLanguage(String value) {
        return (root, query, builder) -> builder.like(root.get("language"), value);
    }

    static Specification<Movie> hasCountry(String value) {
        return (root, query, builder) -> builder.like(root.get("county"), value);
    }

    static Specification<Movie> hasYear(String value) {
        return (root, query, builder) -> builder.like(root.get("year"), value);
    }

    static Specification<Movie> hasTitle(String value) {
        return (root, query, builder) -> builder.like(root.get("title"), value);
    }

    static Specification<Movie> hasActors(String value) {
        return (root, query, builder) -> builder.like(root.get("actors"), value);
    }

    static Specification<Movie> all() {
        return (root, query, builder) -> builder.notEqual(root.get("imdbId"), "EMPTY");
    }
}
