package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class HelloController {
    @FXML
    private Label label;

    @FXML private TableView<Movie> table1;
    @FXML private TableColumn<Movie, String> table1_title;
    @FXML private TableColumn<Movie, Number> table1_length;
    @FXML private TableColumn<Movie, Number> table1_year;
    @FXML private TableColumn<Movie, String> table1_link;

    @FXML private TableView<Genre> table2;
    @FXML private TableColumn<Genre, String> table2_genre;

    private ObservableList<Movie> movieData = FXCollections.observableArrayList();
    private ObservableList<Genre> genreData = FXCollections.observableArrayList();

    private final String DB_URL = "jdbc:mysql://localhost/movies";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    private void getMovies() {
        movieData.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM movie");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                movieData.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("length"),
                        resultSet.getInt("year"),
                        resultSet.getString("link")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void getMovieGenres(Movie selectedMovie) {
        genreData.clear();
        String sql = "SELECT genre.id, genre.name FROM genre " +
                "JOIN movie_genres ON genre.id = movie_genres.genre_id " +
                "WHERE movie_genres.movie_id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, selectedMovie.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                genreData.add(new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }

            label.setText("Movie " + selectedMovie.getTitle() + " in " + genreData.size() + " genres");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        table1.setItems(movieData);
        table1_title.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        table1_length.setCellValueFactory(cellData -> cellData.getValue().lengthProperty());
        table1_year.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        table1_link.setCellValueFactory(cellData -> cellData.getValue().linkProperty());

        table2.setItems(genreData);
        table2_genre.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        getMovies();

        table1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getMovieGenres(newValue);
            }
        });
    }
}