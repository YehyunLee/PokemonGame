package use_case;

import entity.PlayerorAiPokemons;
import entity.GameState;
import entity.Pokemon;

import java.util.Scanner;
import java.util.Random;

public class ViewRunGame {
    private static GameState gameState;
    private final Scanner scanner = new Scanner(System.in);

    public ViewRunGame(PlayerorAiPokemons player, PlayerorAiPokemons aiPlayer) {
        gameState = new GameState(); // Initialize gameState in the constructor
        gameState.initializeGameState(player, aiPlayer);


        System.out.println("Prepare for Pokemon Battle!");
        System.out.println("Player: " + gameState.player.getActivePokemon().getName() + " I choose you!");
        System.out.println("PlayerAi: " + gameState.aiPlayer.getActivePokemon().getName() + " I choose you!");


        while (!gameState.GameOver) {
            if (gameState.isPlayerTurn) {
                playMove(gameState.player, gameState.aiPlayer);
            } else {
                playRandomMove(gameState.player, gameState.aiPlayer);
            }
        }
    }

    public void SwitchRandomPokemon(PlayerorAiPokemons playerorAiPokemons) {
        Random random = new Random();
        int newIndex;

        do {
            // Generate a random index for the Pokémon to switch to
            newIndex = random.nextInt(6);

            if (playerorAiPokemons.isAllDead()) {
                gameState.GameOver = true;
                System.out.println("All of PlayerAi's Pokemon are unable to battle, Player wins!");
                return;
            } else if
            (newIndex != playerorAiPokemons.getCurrentPokemonIndex()) {
                if (!playerorAiPokemons.isSwapPokemonAlive(newIndex)) {
                    newIndex = -1; // Reset newIndex to generate another random index
                }
            } else {
                newIndex = -1; // Reset newIndex to generate another random index
            }
        } while (newIndex == -1);

        // Change the currentPokemonIndex to the selected random index
        playerorAiPokemons.SwapActivePokemon(newIndex);
        System.out.println("AiPlayer Switched to Pokémon " + playerorAiPokemons.getActivePokemon().getName());
        printHealthStatus(gameState.player, gameState.aiPlayer);
    }


    public static void SwitchPokemon(PlayerorAiPokemons playerorAiPokemons) {
        Scanner scanner = new Scanner(System.in);

        int newIndex;

        do {
            // Ask the user to enter the index of the Pokémon they want to switch to
            System.out.println("Enter the index of the Pokémon you want to switch to:");
            printAllPokemonStatuses(playerorAiPokemons);


            newIndex = scanner.nextInt();


            if (playerorAiPokemons.isAllDead()) {
                gameState.GameOver = true;
                System.out.println("All of Player's Pokemon are unable to battle, PlayerAi wins!");
            }
            // Check if the selected Pokémon is alive and not the currently active Pokémon
            else if (newIndex >= 0 && newIndex < 6 && newIndex != playerorAiPokemons.getCurrentPokemonIndex()) {
                if (!playerorAiPokemons.isSwapPokemonAlive(newIndex)) {
                    System.out.println("Selected Pokémon is unable to battle.");
                    newIndex = -1; // Reset newIndex to ask again
                }
            } else if (newIndex == playerorAiPokemons.getCurrentPokemonIndex()) {
                System.out.println("You cannot switch to the currently active Pokémon.");
                newIndex = -1; // Reset newIndex to ask again
            } else {
                System.out.println("Invalid Pokémon index.");
                newIndex = -1; // Reset newIndex to ask again
            }
        } while (newIndex == -1);

        // Change the currentPokemonIndex to the selected index
        playerorAiPokemons.SwapActivePokemon(newIndex);
        System.out.println("Switched to Pokémon " + playerorAiPokemons.getActivePokemon().getName());
    }


    public void playMove(PlayerorAiPokemons player, PlayerorAiPokemons opponent) {
        boolean continueMove = true; // Flag to control the loop

        while (continueMove) {
            System.out.println("Player's " +
                    player.getActivePokemon().getName() + " is making the move:");
            System.out.println("Choose Move to make:");
            System.out.println("1: Attack");
            System.out.println("2: Heal");
            System.out.println("3: Defense");
            System.out.println("4: Swap Pokemon");
            System.out.println("Enter Any other number to view Pokemon Statuses");

            int moveType = scanner.nextInt();

            switch (moveType) {
                case 1:
                    System.out.println("Choose Attack type:");
                    System.out.println("1: Light Attack");
                    System.out.println("2: Heavy Attack");
                    System.out.println("3: True Attack");
                    int attackType = scanner.nextInt();

                    switch (attackType) {
                        case 1:
                            System.out.println("Player:");
                            useAttack(player, opponent, "Light Attack");
                            continueMove = false; // End the move after using attack
                            break;
                        case 2:
                            System.out.println("Player:");
                            useAttack(player, opponent, "Heavy Attack");;
                            continueMove = false; // End the move after using attack
                            break;
                        case 3:
                            System.out.println("Player:");
                            useAttack(player, opponent, "True Attack");
                            continueMove = false; // End the move after using attack
                            break;
                        default:
                            System.out.println("Invalid Attack type. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Choose Heal type:");
                    System.out.println("1: Light Heal");
                    System.out.println("2: Heavy Heal");
                    int healType = scanner.nextInt();

                    switch (healType) {
                        case 1:
                            System.out.println("Player:");
                            useHeal(player, "Light Heal");
                            continueMove = false; // End the move after using heal
                            break;
                        case 2:
                            System.out.println("Player:");
                            useHeal(player, "Heavy Heal");
                            continueMove = false; // End the move after using heal
                            break;
                        default:
                            System.out.println("Invalid Heal type. Please try again.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Choose Defense type:");
                    System.out.println("1: Light Defense");
                    System.out.println("2: Heavy Defense");
                    int defenseType = scanner.nextInt();

                    switch (defenseType) {
                        case 1:
                            System.out.println("Player:");
                            useDefense(player, "Light Defense");
                            continueMove = false; // End the move after using defense
                            break;
                        case 2:
                            System.out.println("Player:");
                            useDefense(player, "Heavy Defense");
                            continueMove = false; // End the move after using defense
                            break;
                        default:
                            System.out.println("Invalid Defense type. Please try again.");
                            break;
                    }
                    break;

                case 4:
                    System.out.println("Player:");
                    SwitchPokemon(player);
                    return;

                default:
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("PLAYER STATS:");
                    printAllPokemonStatuses(gameState.player);
                    System.out.println("RANDOM AI STATS:");
                    printAllPokemonStatuses(gameState.aiPlayer);
                    System.out.println("----------------------------------------------------------------------");
                    break;
            }
        }
        gameState.reverseIsPlayerTurn();
    }




    public void playRandomMove(PlayerorAiPokemons player, PlayerorAiPokemons opponent) {
        Random random = new Random();
        double result = 0.0;

        int moveType = random.nextInt(4) + 1; // Randomly select move type (1: Attack, 2: Heal, 3: Defense, 4: Swap Pokemon)

        switch (moveType) {
            case 1: // Attack
                int attackType = random.nextInt(3) + 1; // Randomly select attack type

                switch (attackType) {
                    case 1:
                        System.out.println("AiPlayer:");
                        result = useAttack(opponent, player, "Light Attack");
                        break;
                    case 2:
                        System.out.println("AiPlayer:");
                        result = useAttack(opponent, player, "Heavy Attack");
                        break;
                    case 3:
                        System.out.println("AiPlayer:");
                        result = useAttack(opponent, player, "True Attack");
                        break;
                }
                break;

            case 2: // Heal
                int healType = random.nextInt(2) + 1; // Randomly select heal type

                switch (healType) {
                    case 1:
                        System.out.println("AiPlayer:");
                        result = useHeal(opponent, "Light Heal");
                        break;
                    case 2:
                        System.out.println("AiPlayer:");
                        result = useHeal(opponent, "Heavy Heal");
                        break;
                }
                break;

            case 3: // Defense
                int defenseType = random.nextInt(2) + 1; // Randomly select defense type

                switch (defenseType) {

                    case 1:
                        System.out.println("AiPlayer:");
                        useDefense(opponent, "Light Defense");
                        break;
                    case 2:
                        System.out.println("AiPlayer:");
                        useDefense(opponent, "Heavy Defense");
                        break;
                }
                break;
        }
        gameState.reverseIsPlayerTurn();
    }

    private double getMovePower(String moveCategory, String moveName) {
        return gameState.player.getActivePokemon().getMoves().get(moveCategory).get(moveName);
    }

    private double useAttack(PlayerorAiPokemons player, PlayerorAiPokemons opponent, String attackType) {
        double result = getMovePower("Attack", attackType);
        System.out.println(player.getActivePokemon().getName() + " Used " + attackType + "!");
        System.out.println(opponent.getActivePokemon().getName() + " Took " + result + " Damage");

        opponent.getActivePokemon().takeDammage(result);

        if (opponent.getActivePokemon().getHealth() <= 0) {
            System.out.println(opponent.getActivePokemon().getName() + " Fainted!");
            opponent.getActivePokemon().die();
            opponent.getActivePokemon().setHealth(0);
            if (player.getType().equals("AI Player") ) {
                SwitchPokemon(opponent);
            } else {
                SwitchRandomPokemon(opponent);
            }
            return result;
        }

        printHealthStatus(gameState.player, gameState.aiPlayer);
        return result;
    }

    private double useHeal(PlayerorAiPokemons player, String healType) {
        double result = getMovePower("Heal", healType);
        System.out.println(player.getActivePokemon().getName() + " Used " + healType + "!");
        System.out.println("Healed " + player.getActivePokemon().getName() + " for " + result);

        player.getActivePokemon().doHealing(result);

        printHealthStatus(gameState.player, gameState.aiPlayer);
        return result;
    }

    private void useDefense(PlayerorAiPokemons player, String defenseType) {
        double result = getMovePower("Defense", defenseType);
        System.out.println(player.getActivePokemon().getName() + " Used " + defenseType + "!");
        player.getActivePokemon().increaseDefence(result);
        System.out.println("Increased " + player.getActivePokemon().getName() + "'s Defense by " + result);
    }

    private void printHealthStatus(PlayerorAiPokemons player, PlayerorAiPokemons opponent) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println( "Player's " +
                player.getActivePokemon().getName() + "' HP: " + player.getActivePokemon().getHealth());
        System.out.println(  "Random Ai's " +
                opponent.getActivePokemon().getName() + "'s HP: " + opponent.getActivePokemon().getHealth());
        System.out.println("----------------------------------------------------------------------");
    }

    private static void printAllPokemonStatuses(PlayerorAiPokemons playerorAi) {
        for (int i = 0; i < playerorAi.pokemons.length; i++)  {
            Pokemon pokemon = playerorAi.pokemons[i];
            if (pokemon.getIsAlive()) {
                System.out.println(i + ": " + pokemon.getName() + " | HP:" + pokemon.getHealth() + " | Status: " + "Alive");
            } else {
                System.out.println(i + ": " + pokemon.getName() + " | HP:0 | Status: " + "Fainted!");
            }
        }
    }
}
