package pl.pollub.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "movie")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Movie implements Serializable {

    @XmlElement
    @JsonProperty(value = "Title")
    private String title;

    @XmlElement
    @JsonProperty(value = "Year")
    private String year;

    @XmlElement
    @JsonProperty(value = "Rated")
    private String rated;

    @XmlElement
    @JsonProperty(value = "Released")
    private String released;

    @XmlElement
    @JsonProperty(value = "Runtime")
    private String runtime;

    @XmlElement
    @JsonProperty(value = "Genre")
    private String genre;

    @XmlElement
    @JsonProperty(value = "Director")
    private String director;

    @Column(length = 100500)
    @XmlElement
    @JsonProperty(value = "Writer")
    private String writer;

    @XmlElement
    @JsonProperty(value = "Actors")
    private String actors;

    @Column(length = 100500)
    @XmlElement
    @JsonProperty(value = "Plot")
    private String plot;

    @XmlElement
    @JsonProperty(value = "Language")
    private String language;

    @XmlElement
    @JsonProperty(value = "Country")
    private String county;

    @XmlElement
    @JsonProperty(value = "Awards")
    private String awards;

    @XmlElement
    @JsonProperty(value = "Poster")
    private String poster;

    @XmlElement
    @JsonProperty(value = "Metascore")
    private String metascore;

    @XmlElement
    @JsonProperty(value = "imdbRating")
    private String imdbRating;

    @XmlElement
    @JsonProperty(value = "imdbVotes")
    private String imdbVotes;

    @Id
    @XmlAttribute
    @JsonProperty(value = "imdbID")
    private String imdbId;

    @XmlElement
    @JsonProperty(value = "Type")
    private String type;

    @XmlElement
    @JsonProperty(value = "Response")
    private boolean response;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", county='" + county + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", metascore=" + metascore +
                ", imdbRating=" + imdbRating +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", type='" + type + '\'' +
                ", response=" + response +
                '}';
    }
}
