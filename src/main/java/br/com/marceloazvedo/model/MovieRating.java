package br.com.marceloazvedo.model;

public class MovieRating {

    private Long userId;
    private String movieId;
    private Float rating;
    private Long timestamp;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MovieRating{" +
                "userId=" + userId +
                ", movieId='" + movieId + '\'' +
                ", rating=" + rating +
                ", madeIn=" + timestamp +
                '}';
    }
}
