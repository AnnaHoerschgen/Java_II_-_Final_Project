import java.util.Scanner;

public class PokemonSearcher {
    enum UserAction {
        SEARCH_AGAIN,
        EXIT
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAction action;

        do {
            System.out.print("Enter Pokémon name or National Dex number: ");
            String input = scanner.nextLine().trim().toLowerCase();

            try {
                PokemonAPI.displayPokemonInfo(input);
            } catch (Exception e) {
                System.out.println("Could not find Pokémon with input: " + input);
            }

            System.out.print("\nDo you want to search again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            action = response.startsWith("y") ? UserAction.SEARCH_AGAIN : UserAction.EXIT;

        } while (action == UserAction.SEARCH_AGAIN);

        System.out.println("Goodbye!");
        scanner.close();
    }
}
