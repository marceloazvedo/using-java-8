package br.com.marceloazvedo;

import br.com.marceloazvedo.adapter.GenreMovies;
import br.com.marceloazvedo.model.Movie;
import br.com.marceloazvedo.function.MoviesSelectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestSelection {

    private static final String ADVENTURE_GENDER = "Adventure";
    private static final String CHILDREN_GENDER = "Children";
    private static final String ANIMATION_GENDER = "Animation";
    private static final String COMEDY_GENDER = "Comedy";
    private static final String FANTASY_GENDER = "Fantasy";

    private static final String MOVIE_SEPARATOR = "|";

    private List<Movie> movies;


    @BeforeEach
    public void setup() {
        movies = new ArrayList<>();

        Movie advetureAndChildren = new Movie(1L,
                "Andveture and Children movie",
                ADVENTURE_GENDER + MOVIE_SEPARATOR + CHILDREN_GENDER);
        movies.add(advetureAndChildren);

        Movie justAdventure = new Movie(2L,
                "Adventure movie",
                ADVENTURE_GENDER);
        movies.add(justAdventure);

        Movie justChildren = new Movie(3L,
                "Children movie",
                CHILDREN_GENDER);
        movies.add(justChildren);

        Movie adventureAndAnothers = new Movie(4L,
                "Adventure and others genres movie",
                ANIMATION_GENDER + MOVIE_SEPARATOR + FANTASY_GENDER + MOVIE_SEPARATOR + ADVENTURE_GENDER);
        movies.add(adventureAndAnothers);

        Movie childrenAndAnothers = new Movie(5L,
                "Children and others genres movie",
                ANIMATION_GENDER + MOVIE_SEPARATOR + FANTASY_GENDER + MOVIE_SEPARATOR + CHILDREN_GENDER);
        movies.add(childrenAndAnothers);

        Movie comedyAndAnimation = new Movie(6L,
                "Animation movie",
                COMEDY_GENDER);
        movies.add(comedyAndAnimation);
    }

    private static boolean hasIdsInFiltered(List<Movie> filteredMovies, Long... ids) {
        List<Long> expectedIds = Arrays.asList(ids);
        return filteredMovies.stream().allMatch(movie -> expectedIds.contains(movie.getMovieId()));
    }

    @Test
    public void testSelectionAdventureMovie() {
        List<Movie> filteredMovies = movies.stream()
                .filter(MoviesSelectors.selectMovieHasGenre(ADVENTURE_GENDER))
                .collect(Collectors.toList());
        Assertions.assertEquals(filteredMovies.size(), 3);
        // id 1, 2, 4 (Movies with this ids has Adventure in genre)
        Assertions.assertTrue(hasIdsInFiltered(filteredMovies, 1L, 2L, 4L));
    }

    @Test
    public void testSelectionChildrenMovie() {
        List<Movie> filteredMovies = movies.stream()
                .filter(MoviesSelectors.selectMovieHasGenre(CHILDREN_GENDER))
                .collect(Collectors.toList());
        // id 1, 2, 4; (Movies with this ids has Children in genre)
        Assertions.assertTrue(hasIdsInFiltered(filteredMovies, 1L, 3L, 5L));
    }

    @Test
    public void testSelectAndSeparateAdventureAndChildrenMovie() {
        List<GenreMovies> moviesSeparatedByGenre = movies.stream()
                .reduce(new ArrayList<>(), MoviesSelectors
                        .selectAndSeparate(ADVENTURE_GENDER, CHILDREN_GENDER), MoviesSelectors.matchList());
        // Expected GenreMovies to Adventure and Children
        Assertions.assertEquals(2, moviesSeparatedByGenre.size());

        GenreMovies adventureMovies = MoviesSelectors
                .getGenreMoviesInListByGenre(ADVENTURE_GENDER, moviesSeparatedByGenre);
        // id 1, 2, 4 (Movies with this ids has Adventure in genre)
        Assertions.assertTrue(hasIdsInFiltered(adventureMovies.getMovies(), 1L, 2L, 4L));

        GenreMovies childrenMovies = MoviesSelectors
                .getGenreMoviesInListByGenre(CHILDREN_GENDER, moviesSeparatedByGenre);
        // id 1, 2, 4; (Movies with this ids has Children in genre.
        Assertions.assertTrue(hasIdsInFiltered(childrenMovies.getMovies(), 1L, 3L, 5L));
    }

}
