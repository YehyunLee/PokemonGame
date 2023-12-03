package use_case;
import view.BattleViewInterface;
import entity.PlayerorAiPokemons;
import entity.GameState;
import entity.Pokemon;

import java.util.List;
import java.util.Random;

/**
 * This class represents the game logic to run a Pokémon battle.
 */
public class RunGame implements RunGameOutput {
    private static GameState gameState;
    private static BattleViewInterface battleView;

    /**
     * Constructs a RunGame instance initializing the game state and battle view.
     *
     * @param player   The player's Pokémon.
     * @param aiPlayer The AI player's Pokémon.
     */
    public RunGame(PlayerorAiPokemons player, PlayerorAiPokemons aiPlayer, BattleViewInterface battleView) {
        gameState = new GameState();
        gameState.initializeGameState(player, aiPlayer);
        RunGame.battleView = battleView;
        RunGame.battleView.setGameOutput(this);

        battleView.updateFrontGif(player.getActivePokemon().getBackSprite());
        battleView.updateBackGif(aiPlayer.getActivePokemon().getFrontSprite());
        battleView.displayBattleView();
        battleView.printToConsole("Prepare for Pokemon Battle!");
        battleView.printToConsole("Player: " + gameState.player.getActivePokemon().getName() + " I choose you!");
        battleView.printToConsole("PlayerAi: " + gameState.aiPlayer.getActivePokemon().getName() + " I choose you!");
        battleView.printToConsole("----------------------------------------------------------------------");
    }

    /**
     * Switches to a random Pokémon for the AI player.
     */
    public void SwitchRandomPokemon() {
        Random random = new Random();

        if (gameState.aiPlayer.isAllDead()) {
            System.out.println("All of PlayerAi's Pokemon are unable to battle, Player wins!");
            gameState.PlayerWin = "True";
            return;
        }

        List<Integer> aliveIndices = gameState.aiPlayer.getAlivePokemonIndices();
        if (aliveIndices.isEmpty()) {
            System.out.println("No alive Pokemon to switch to.");
            return;
        }

        // Choose a random index from the list of alive Pokémon.
        int newIndex = aliveIndices.get(random.nextInt(aliveIndices.size()));

        gameState.aiPlayer.SwapActivePokemon(newIndex);
        battleView.printToConsole("AiPlayer Switched to Pokémon " + gameState.aiPlayer.getActivePokemon().getName());
        battleView.updateBackGif(gameState.aiPlayer.getActivePokemon().getFrontSprite());
        updateEnemyHealthBar();
        updatePlayerHealthBar();
        printHealthStatus(gameState.player, gameState.aiPlayer);
    }


    /**
     * Switches the player's current Pokémon based on the provided move index.
     *
     * @param move The index of the Pokémon to switch to.
     */
    public void SwitchPlayerPokemon(String move) {
        printBothStatuses();
        if (gameState.player.isAllDead()) {
            gameState.PlayerWin = "False";
        } else if (Integer.parseInt(move) == gameState.player.getCurrentPokemonIndex()) { // If Index is the same
            battleView.printToConsole("Move Failed!");
        } else {
            gameState.player.SwapActivePokemon(Integer.parseInt(move));
            battleView.updateFrontGif(gameState.player.getActivePokemon().getBackSprite());
            updatePlayerHealthBar();
            updateEnemyHealthBar();
            battleView.printToConsole("Player Switched to Pokémon " + gameState.player.getActivePokemon().getName());
        }
    }

    /**
     * Returns the winner of the game
     *
     */
    public String getWinnerOfGame() {
        if (gameState.PlayerWin.equals("True")) {
            return "Player";
        } else if (gameState.PlayerWin.equals("False")) {
            return "AIPlayer";
        } else {
            return "";
        }
    }

    /**
     * Executes a healing action on the player's current Pokémon.
     *
     * @param move The type of healing move to be used.
     */
    public void useHealOnSelf(String move) {
        useHeal(gameState.player, move);
        updatePlayerHealthBar();
        updateEnemyHealthBar();
    }

    /**
     * Executes a defense action for the player's current Pokémon.
     *
     * @param move The type of defense move to be used.
     */
    public void useDefenseOnSelf(String move) {
        useDefense(gameState.player, move);
    }

    /**
     * Executes a random move for the AI player, which could be an attack, heal, or defense.
     */
    public void playRandomMove() {
        Random random = new Random();
        int moveType = random.nextInt(3); // 0: Attack, 1: Heal, 2: Defense

        switch (moveType) {
            case 0: // Attack
                String[] attackTypes = {"Light Attack", "Heavy Attack", "True Attack"};
                String selectedAttack = attackTypes[random.nextInt(attackTypes.length)];
                battleView.printToConsole("AiPlayer: " + gameState.aiPlayer.getActivePokemon().getName() + " Use " + selectedAttack + "!");
                useAttackOnPlayer(selectedAttack);
                updateEnemyHealthBar();
                updatePlayerHealthBar();
                break;

            case 1: // Heal
                String[] healTypes = {"Light Heal", "Heavy Heal"};
                String selectedHeal = healTypes[random.nextInt(healTypes.length)];
                battleView.printToConsole("AiPlayer: " + gameState.aiPlayer.getActivePokemon().getName() + " Use " + selectedHeal + "!");
                useHeal(gameState.aiPlayer, selectedHeal);
                updateEnemyHealthBar();
                updatePlayerHealthBar();
                break;

            case 2: // Defense
                String[] defenseTypes = {"Light Defense", "Heavy Defense"};
                String selectedDefense = defenseTypes[random.nextInt(defenseTypes.length)];
                battleView.printToConsole("AiPlayer: " + gameState.aiPlayer.getActivePokemon().getName() + " Use " + selectedDefense + "!");
                useDefense(gameState.aiPlayer, selectedDefense);
                updateEnemyHealthBar();
                updatePlayerHealthBar();
                break;
        }
    }

    /**
     * Updates the health bar of the enemy Pokémon in the battle view.
     */
    public static void updateEnemyHealthBar() {
        double hp = gameState.aiPlayer.getActivePokemon().getHealth();
        double totalHp = gameState.aiPlayer.getActivePokemon().getTotalHealth();
        battleView.updateEnemyHealth((int) ((hp / totalHp) * 100));
    }

    /**
     * Updates the health bar of the player's Pokémon in the battle view.
     */
    public static void updatePlayerHealthBar() {
        double playerHp = gameState.player.getActivePokemon().getHealth();
        double totalPlayerHp = gameState.player.getActivePokemon().getTotalHealth();
        System.out.println((int) ((playerHp / totalPlayerHp) * 100));
        battleView.updatePlayerHealth((int) ((playerHp / totalPlayerHp) * 100));
    }

    /**
     * Executes an attack from one Pokémon against another.
     *
     * @param attackType The type of attack to be used.
     */
    public void useAttackOnOpponent(String attackType) {
        double result = gameState.player.getActivePokemon().getMoves().get("Attack").get(attackType);

        battleView.printToConsole(gameState.player.getActivePokemon().getName() + " Used " + attackType + "!");
        battleView.printToConsole(gameState.aiPlayer.getActivePokemon().getName() + " Took " + result + " Damage");

        // Take Damage
        gameState.aiPlayer.getActivePokemon().takeDammage(result);

        if (gameState.aiPlayer.getActivePokemon().getHealth() <= 0) {
            gameState.aiPlayer.getActivePokemon().setHealth(0);
            updatePlayerHealthBar();
            updateEnemyHealthBar();
            handleOpponentFainting();
        }

        updatePlayerHealthBar();
        updateEnemyHealthBar();
        printHealthStatus(gameState.player, gameState.aiPlayer);
    }

    /**
     * Executes an attack from one Pokémon against another.
     *
     * @param attackType The type of attack to be used.
     */
    public void useAttackOnPlayer(String attackType) {
        double result = gameState.aiPlayer.getActivePokemon().getMoves().get("Attack").get(attackType);

        battleView.printToConsole(gameState.aiPlayer.getActivePokemon().getName() + " Used " + attackType + "!");
        battleView.printToConsole(gameState.player.getActivePokemon().getName() + " Took " + (int) result + " Damage");

        // Takes Damage
        gameState.player.getActivePokemon().takeDammage(result);

        if (gameState.player.getActivePokemon().getHealth() <= 0) {
            gameState.player.getActivePokemon().setHealth(0);
            updatePlayerHealthBar();
            updateEnemyHealthBar();
            handlePlayerFainting();
        }

        updatePlayerHealthBar();
        updateEnemyHealthBar();
        printHealthStatus(gameState.player, gameState.aiPlayer);

    }

    /**
     * Handles the scenario when an opponent's Pokémon faints.
     */
    private void handleOpponentFainting() {
        battleView.printToConsole("----------------------------------------------------------------------");
        battleView.printToConsole(gameState.aiPlayer.getActivePokemon().getName() + " Fainted!");
        gameState.aiPlayer.getActivePokemon().die();
        gameState.aiPlayer.getActivePokemon().setHealth(0);

        SwitchRandomPokemon();
    }

    /**
     * Handles the scenario when an players Pokémon faints.
     */
    private void handlePlayerFainting() {
        battleView.printToConsole("----------------------------------------------------------------------");
        battleView.printToConsole(gameState.player.getActivePokemon().getName() + " Fainted!");
        gameState.player.getActivePokemon().die();
        gameState.player.getActivePokemon().setHealth(0);

        // Let player swap Pokemon
        battleView.setIsFaintedObserver("True");
        updatePlayerHealthBar();
        updateEnemyHealthBar();
    }

    /**
     * Executes a heal move for the player's Pokémon.
     *
     * @param playerOrAi Player or Ai PlayerOrAiPokemons Object
     * @param healType The type of heal to be used.
     * @return The result of the heal action.
     */
    private double useHeal(PlayerorAiPokemons playerOrAi, String healType) {
        double result;

        if (playerOrAi.getType().equals("Player")) {
            result = gameState.player.getActivePokemon().getMoves().get("Heal").get(healType);
        } else {
            result = gameState.aiPlayer.getActivePokemon().getMoves().get("Heal").get(healType);
        }

        battleView.printToConsole(playerOrAi.getActivePokemon().getName() + " Used " + healType + "!");
        battleView.printToConsole("Healed " + playerOrAi.getActivePokemon().getName() + " for " + (int) result);
        updateEnemyHealthBar();
        updatePlayerHealthBar();

        playerOrAi.getActivePokemon().doHealing(result);
        playerOrAi.getActivePokemon().doTotalHealing(result);

        printHealthStatus(gameState.player, gameState.aiPlayer);
        return result;
    }

    /**
     * Executes a defense move for the player's Pokémon.
     *
     * @param playerOrAi  Player or Ai PlayerOrAiPokemons Object
     * @param defenseType  The type of defense to be used.
     */
    private void useDefense(PlayerorAiPokemons playerOrAi, String defenseType) {
        double result;

        if (playerOrAi.getType().equals("Player")) {
            result = gameState.player.getActivePokemon().getMoves().get("Defense").get(defenseType);
        } else {
            result = gameState.aiPlayer.getActivePokemon().getMoves().get("Defense").get(defenseType);
        }

        playerOrAi.getActivePokemon().increaseDefence(result);

        battleView.printToConsole(playerOrAi.getActivePokemon().getName() + " Used " + defenseType + "!");
        battleView.printToConsole("Increased " + playerOrAi.getActivePokemon().getName() + "'s Defense by " + (int) result);
        battleView.printToConsole("----------------------------------------------------------------------");
    }

    /**
     * Prints the current health status of both player's and opponent's Pokémon.
     *
     * @param player   The player's Pokémon.
     * @param opponent The opponent's Pokémon.
     */
    private static void printHealthStatus(PlayerorAiPokemons player, PlayerorAiPokemons opponent) {
        battleView.printToConsole("----------------------------------------------------------------------");
        battleView.printToConsole("Player's " + player.getActivePokemon().getName() + "' HP: " + player.getActivePokemon().getHealth());
        battleView.printToConsole("Random Ai's " + opponent.getActivePokemon().getName() + "'s HP: " + opponent.getActivePokemon().getHealth());
        battleView.printToConsole("----------------------------------------------------------------------");
    }

    /**
     * Prints the statuses of all Pokémon in a player's team.
     *
     * @param playerOrAi The player or AI whose Pokémon statuses are to be printed.
     */
    private static void printAllPokemonStatuses(PlayerorAiPokemons playerOrAi) {
        for (int i = 0; i < playerOrAi.pokemons.length; i++) {
            Pokemon pokemon = playerOrAi.pokemons[i];
            String status = pokemon.getIsAlive() ? "Alive" : "Fainted!";
            battleView.printToConsole(i + ": " + pokemon.getName() + " | HP:" + pokemon.getHealth() + " | Status: " + status);
        }
    }

    /**
     * Prints the statuses of all Pokémon for both the player and the AI.
     */
    private static void printBothStatuses() {
        updatePlayerHealthBar();
        updateEnemyHealthBar();
        battleView.printToConsole("PLAYER POKEMON STATUS");
        printAllPokemonStatuses(gameState.player);
        battleView.printToConsole("----------------------------------------------------------------------");
        battleView.printToConsole("AI POKEMON STATUS");
        printAllPokemonStatuses(gameState.aiPlayer);
        battleView.printToConsole("----------------------------------------------------------------------");
    }

}