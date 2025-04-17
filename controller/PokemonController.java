package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pokemon;
import util.PokeAPI;

public class PokemonController {

    @FXML private TextField searchField;
    @FXML private Label nameLabel;
    @FXML private Label typeLabel;
    @FXML private Label heightLabel;
    @FXML private Label weightLabel;

    @FXML
    public void onSearch() {
        String name = searchField.getText().trim().toLowerCase();
        if (name.isEmpty()) return;

        Pokemon pokemon = PokeAPI.getPokemon(name);
        if (pokemon != null) {
            nameLabel.setText("Name: " + pokemon.getName());
            typeLabel.setText("Type: " + pokemon.getType());
            heightLabel.setText("Height: " + pokemon.getHeight());
            weightLabel.setText("Weight: " + pokemon.getWeight());
        } else {
            nameLabel.setText("Pokémon not found.");
            typeLabel.setText("");
            heightLabel.setText("");
            weightLabel.setText("");
        }
    }
}
