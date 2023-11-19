package use_case;

import entity.*;

import java.util.ArrayList;
import java.util.List;

public class MovesFactory {

    public static List<String> createMoves(List<Pokemon> pokemonList) {
        List<String> movesList = new ArrayList<>();

        for (Pokemon pokemon : pokemonList) {
            String pokemonName = pokemon.getName();
            int attackStat = pokemon.getAttack();
            int defenseStat = pokemon.getDefense();
            int healthStat = pokemon.getHealth();

            StringBuilder moveDetails = new StringBuilder();
            moveDetails.append(pokemonName).append(": ");

            // Create moves based on the Pokemon's stats
            moveDetails.append("Attack[Light Attack: ").append(createLightAttack(pokemonName, attackStat).getLightAttack());
            moveDetails.append(", Heavy Attack: ").append(createHeavyAttack(pokemonName, attackStat).getHeavyAttack());
            moveDetails.append(", True Attack: ").append(createTrueAttack(pokemonName, attackStat).getTrueAttack()).append("], ");

            moveDetails.append("Heal[Heal Amount: ").append(createHealMove(pokemonName, healthStat).getHeal()).append("], ");
            moveDetails.append("SpecialMove[").append(createSpecialMove(pokemonName).getName()).append("], ");
            moveDetails.append("Defense[Defense Amount: ").append(createDefenseMove(pokemonName, defenseStat).getDefense()).append("]");

            // Add the move details to the list
            movesList.add(moveDetails.toString());
        }

        return movesList;
    }

    private static Attack createLightAttack(String pokemonName, int attackStat) {
        float lightAttackDamage = attackStat * 0.2f;
        return new Attack(pokemonName + "'s Light Attack", lightAttackDamage, 0, 0, 0);
    }

    private static Attack createHeavyAttack(String pokemonName, int attackStat) {
        float heavyAttackDamage = attackStat * 0.5f;
        return new Attack(pokemonName + "'s Heavy Attack", 0, 0, 0, heavyAttackDamage);
    }

    private static Attack createTrueAttack(String pokemonName, int attackStat) {
        return new Attack(pokemonName + "'s True Attack", 0, 0, (float) attackStat, 0);
    }

    private static Heal createHealMove(String pokemonName, int healthStat) {
        float healAmount = healthStat * 0.3f;
        return new Heal(pokemonName + "'s Heal Move", healAmount);
    }

    private static Defense createDefenseMove(String pokemonName, int defenseStat) {
        return new Defense(pokemonName + "'s Defense Move", (float) defenseStat);
    }

    private static SpecialMoves createSpecialMove(String pokemonName) {
        return new SpecialMoves(pokemonName + "'s Special move");
    }
}
