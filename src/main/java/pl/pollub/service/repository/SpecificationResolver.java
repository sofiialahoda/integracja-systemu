package pl.pollub.service.repository;

import org.springframework.data.jpa.domain.Specification;
import pl.pollub.service.model.Movie;

public class SpecificationResolver {
    public static Specification<Movie> resolve(EMovieSpecifications specification, String value) {
        switch (specification) {
            case HAS_ACTORS:
                return MovieSpecifications.hasActors(value);
            case HAS_AWARDS:
                return MovieSpecifications.hasAwards();
            case HAS_COUNTRY:
                return MovieSpecifications.hasCountry(value);
            case HAS_DIRECTOR:
                return MovieSpecifications.hasDirector(value);
            case HAS_GENRE:
                return MovieSpecifications.hasGenre(value);
            case HAS_LANGUAGE:
                return MovieSpecifications.hasLanguage(value);
            case HAS_POSTER:
                return MovieSpecifications.hasPoster();
            case HAS_TITLE:
                return MovieSpecifications.hasTitle(value);
            case HAS_WRITER:
                return MovieSpecifications.hasWriter(value);
            case HAS_YEAR:
                return MovieSpecifications.hasYear(value);
            case ALL:
                return MovieSpecifications.all();
        }
        throw new IllegalStateException("Invalid specification type.");
    }
}
