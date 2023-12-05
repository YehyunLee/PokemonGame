![Pokemon Logo](logo.png)
# Pokémon Battle Simulator 
This Pokémon Battle Simulator allows players to engage in exciting turn-based battles using various Pokémon. The simulator is designed to provide a realistic and engaging Pokémon battle experience, complete with different moves, strategies, and outcomes.

## Architecture 
This application adopts the [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) principles for a maintainable and scalable design. At its core are the business entities, surrounded by use cases that form the application's business rules. Interface adapters translate between these use cases and external components like databases and UIs. This layered approach ensures that changes in external frameworks have minimal impact on business logic, keeping the codebase robust and flexible.
 
The architecture is structured into layers, with the most central one being the Entities layer, which contains the business objects of the application. Surrounding this are the Use Cases, which encapsulate and implement all of the business rules for the application. External to the Use Cases is the Interface Adapters layer, which converts data from the format most convenient for the use cases and entities, to the format most convenient for some external agency such as the Database or the Web. Finally, at the outermost layer, we have the Frameworks and Drivers, which are generally composed of frameworks and tools such as the Database, the Web Framework, or other high-level tools.

Following these guidelines, our application ensures that the business rules are at the core of the system's design, thus protecting them from external changes and making the system robust and testable. More information about how we used clean architecture can be found [here](https://docs.google.com/presentation/d/1PDqGYWZ6Y_l8xoNbyR7SroA73VyJT5V9e_9OKkr4S9A/edit?usp=sharing.)   

## Gameplay Features
- Turn-based combat system that mimics the official Pokémon games
- A selection of Pokémon each with unique stats and moves
- Randomized AI opponent behavior for varied gameplay
- Graphical interface showcasing the battle progress
- Background music and sound effects for an immersive experience 
![Gameplay Screenshot](GameplayScreenshot1.png)

## Installation

To set up and run this Java project on your local machine, follow these steps:

1. **Clone the Repository**
    ```sh
    git clone https://github.com/YehyunLee/PokemonGame
    cd PokemonGame
    ```

2. **Check for Java Installation**
    Ensure you have Java installed on your system. You can check this by running:
    ```sh
    java -version
    ```
    If Java is not installed, download and install it from [Oracle's Java website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

3. **Build the Project**
 Run the **main.java** inside **src/main**

6. **Enjoy the Game**
    The game should now start. Follow the on-screen instructions to play.

Note: These steps assume you have Git and Java correctly installed and configured on your system.