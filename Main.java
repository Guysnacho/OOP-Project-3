//CS 2365
//Authors - Samuel Adetunji, Isaac Ojigho, Mushfique Khan
//Project 3
//March 28 2020

import java.util.*;
import java.lang.Math;

//This file will be our gameSim class. Repl won't run it unless
// its named Main though SO DON'T CHANGE LINE 11 until we're ready to submit the assignment
public class Main{

    public static void startGame(int expanVersion, Scanner input){
        System.out.println("### BANG! - The Dice Game###");

        //The whole game will just loop through an arraylist of players over and over until a winner is found.
        //Easiest way to run it imo
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> characters = loadCharacters(expanVersion);

        System.out.println("How many CPUs would you like to play against? (2-7)");

        //Add all needed players to the list
        int selection = input.nextInt();
        for(int count = 0; count <= selection; count++){
            //Add a random character
            players.add(characters.get((int)(Math.random() * characters.size())));
        }

        setRoles(players);

        //Identify the sheriff
        for(Player item: players){
            if(item.getRole().equals("Sheriff")){
                System.out.println("I, " + item.getName() + ", am the Sheriff 'round these here parts! *Takes a long drag from a cigar*\nY'all outlaws got some bounties in intend to collect.");
                break;
            }
        }
        System.out.println("You are " + players.get(0).getName() + ", you have " + players.get(0).getHealth() + " health points, and you are a " + players.get(0).getRole() + " Good luck!");

        //while(Players are still alive/Outlaws haven't killed the sheriff/Renegade hasn't killed everyone yet)
        //usersim(players);
        play(expanVersion, players, input);
    }



    //Setup methods
    //Gives a number from 0 to 15 (Since there are 16 characters)
    public static int rollCharacter(){
        return (int)(Math.random() * 15.0);
    }

    //Loads up all the possible characters
    public static ArrayList<Player> loadCharacters(int expanVersion){
        //Makes a list of standard characters depending on the expansion
        ArrayList<Player> characters = new ArrayList<>();
        if(expanVersion == 1) {
            //Old Saloon possible characters
            characters.add(new Player(9,"Paul Regret"));
            characters.add(new Player(8,"Pedro Ramirez"));
            characters.add(new Player(9,"Vulture Sam"));
            characters.add(new Player(8,"Willy The Kid"));
            characters.add(new Player(8,"Sid Ketchum"));
            characters.add(new Player(8,"Black Jack"));
            characters.add(new Player(8,"Calamity Janet"));
            characters.add(new Player(9,"Jesse Jones"));
            characters.add(new Player(7,"Jourdonnais"));
            characters.add(new Player(9,"Apache Kid"));
            characters.add(new Player(7,"Jose Delgado"));

        } else if(expanVersion == 2) {
            //Undead or Alive possible characters
            characters.add(new Player(8,"Sid Ketchum"));
            characters.add(new Player(8,"Black Jack"));
            characters.add(new Player(8,"Calamity Janet"));
            characters.add(new Player(7,"El Gringo"));
            characters.add(new Player(9,"Jesse Jones"));
            characters.add(new Player(7,"Jourdonnais"));
            characters.add(new Player(7,"Kit Carlson"));
            characters.add(new Player(8,"Lucky Duke"));
            characters.add(new Player(8,"Belle Star"));
            characters.add(new Player(7,"Greg Digger"));

        } else {
            //Vanilla Bang possible characters
            characters.add(new Player(8,"Sid Ketchum"));
            characters.add(new Player(8,"Black Jack"));
            characters.add(new Player(8,"Calamity Janet"));
            characters.add(new Player(9,"Jesse Jones"));
            characters.add(new Player(7,"Jourdonnais"));
            characters.add(new Player(8,"Lucky Duke"));
            characters.add(new Player(9,"Paul Regret"));
            characters.add(new Player(8,"Pedro Ramirez"));
            characters.add(new Player(9,"Vulture Sam"));
            characters.add(new Player(8,"Willy The Kid"));
        }
        return characters;
    }

    //Sets the roles for each of the players in the ArrayList
    public static void setRoles(ArrayList<Player> players){
        //Add player Roles
        if(players.size()==3){
            players.get(2).setRole("Deputy");
            players.get(0).setRole("Outlaw");
            players.get(1).setRole("Renegade");
        } else if(players.size()>=4 && players.size()<8){
            //Pick a sheriff and a renegade
            int current = (int)(Math.random() * (players.size()));
            players.get(current).setRole("Sheriff");

            current = (int)(Math.random() * (players.size()));
            while(players.get(current).getRole().equals("Sheriff")){
                current = (int)(Math.random() * (players.size()));
            }
            players.get(current).setRole("Renegade");

            //Set outlaws and deputies for each case
            if(players.size() == 4){
                //We need 2 outlaws
                current = (int)(Math.random() * (players.size()));
                for(Player item: players){
                    if(item.getRole().equals(" "))
                        item.setRole("Outlaw");
                }
                return;
            } else if(players.size() == 5){
                //We need 2 outlaws
                current = (int)(Math.random() * (players.size()));
                for(int count = 0; count < 2; count ++){
                    while(players.get(current).getRole().equals("Sheriff") || players.get(current).getRole().equals("Renegade") || players.get(current).getRole().equals("Outlaw")){
                        current = (int)(Math.random() * (players.size()));
                    }
                    players.get(current).setRole("Outlaw");
                }
                //We need 1 Deputy
                for(Player item: players){
                    if(item.getRole().equals(" "))
                        item.setRole("Deputy");
                    break;
                }
                return;
            } else if(players.size() == 6){
                //We need 3 outlaws
                current = (int)(Math.random() * (players.size()));
                for(int count = 0; count < 3; count ++){
                    while(players.get(current).getRole().equals("Sheriff") || players.get(current).getRole().equals("Renegade") || players.get(current).getRole().equals("Outlaw")){
                        current = (int)(Math.random() * (players.size()));
                    }
                    players.get(current).setRole("Outlaw");
                }
                //We need 1 Deputy
                for(Player item: players){
                    if(item.getRole().equals(""))
                        item.setRole("Deputy");
                    break;
                }
                return;
            } else if(players.size() == 7){
                //We need 3 outlaws
                current = (int)(Math.random() * (players.size()));
                for(int count = 0; count < 3; count ++){
                    while(players.get(current).getRole().equals("Sheriff") || players.get(current).getRole().equals("Renegade") || players.get(current).getRole().equals("Outlaw")){
                        current = (int)(Math.random() * (players.size()));
                    }
                    players.get(current).setRole("Outlaw");
                }
                //We need 2 Deputies
                for(Player item: players){
                    if(item.getRole().equals(""))
                        item.setRole("Deputy");
                }
                return;
            }
            //8 Players
        } else if(players.size() == 8){
            //Pick a sheriff and 2 renegades
            int current = (int)(Math.random() * (players.size()));
            players.get(current).setRole("Sheriff");

            //2 renegades
            for(int count = 0; count <2; count++){
                while(players.get(current).getRole().equals("Sheriff") || players.get(current).getRole().equals("Renegade")){
                    current = (int)(Math.random() * (players.size()));
                }
                players.get(current).setRole("Renegade");
            }
            //We need 3 outlaws
            current = (int)(Math.random() * (players.size()));
            for(int count = 0; count < 3; count ++){
                while(players.get(current).getRole().equals("Sheriff") || players.get(current).getRole().equals("Renegade") || players.get(current).getRole().equals("Outlaw")){
                    current = (int)(Math.random() * (players.size()));
                }
                players.get(current).setRole("Outlaw");
            }
            //We need 2 Deputies
            for(Player item: players){
                if(item.getRole().equals(""))
                    item.setRole("Deputy");
            }
            return;
        }
    }





    //Dice methods
    //Method that handles rolling of all dice so its not sitting in the play method
    public static void rollAllDice(Dice[] dice){
        for(Dice current: dice){
            current.roll();
        }
    }

    //Method that handles rerolling AND Black Jack's special ability
    public static void reroll(Dice[] dice, Player currPlayer){
        int blJack = 0;
        for(Dice current: dice){
            //Checks if its dynamite
            if(!currPlayer.getName().equals("Black Jack")){
                if(current.getFace().equals("dynamite")){
                    System.out.println("Dynamite cannot be rerolled, hehehe");
                    currPlayer.dynamiteRolls++;
                    continue;
                }
            }

            //Handles blackjack's dice
            if(current.getFace().equals("dynamite")){
                blJack++;
                System.out.println("Dynamite can be rerolled, for Black Jack");
                if(blJack >=3){
                    currPlayer.dynamiteRolls = 3;
                }
            }
            current.roll();
        }
    }

    //Display - Handles showing us the dice outside of the play function
    public static void showDice(Dice[] dice){
        for(Dice current: dice)
            System.out.print(current.getFace() + " ");

        System.out.println();
    }






    //Dice interactions - Bulls Eye 1 and 2
    public static void bullsEyeOne(ArrayList<Player> players, Scanner input){
        System.out.println("You've got a free shot, to the left or right? (l/r)- ");
        if(input.next().charAt(0) == 'l'){
            //Go backwards until a live player is found
            for(int c = players.size()-1; c > 0; c--){
                if(players.get(c).getHealth() > 0){
                    System.out.println("You shot " + players.get(c).getName() + "!");
                    players.get(c).takeHit();
                    if(players.get(c).getName().equals("Pedro Ramirez")){
                        if(players.get(c).arrows > 0){
                            System.out.println("Pedro dropped an arrow!");
                            players.get(c).arrows--;
                            players.get(c).arrowPile++;
                        }
                    }
                    if(checkPlayer(players.get(c))){
                        players.get(c).revealRole();
                        vultureTime(players);
                    }
                    break;
                }
            }
        } else {
            //Go forward until a live player is found
            for(int c = 1; c < players.size(); c++){
                if(players.get(c).getHealth() > 0){
                    System.out.println("You shot " + players.get(c).getName() + "!");
                    players.get(c).takeHit();
                    if(players.get(c).getName().equals("Pedro Ramirez")){
                        if(players.get(c).arrows > 0){
                            System.out.println("Pedro dropped an arrow!");
                            players.get(c).arrows--;
                            players.get(c).arrowPile++;
                        }
                    }
                    if(checkPlayer(players.get(c))){
                        players.get(c).revealRole();
                        vultureTime(players);
                    }
                    break;
                }
            }
        }
    }

    public static void bullsEyeTwo(ArrayList<Player> players, int livingPlayers, Scanner input){
        System.out.println("You've got a free shot, to the left or right? (l/r)- ");

        //Check if there are enough players
        if(livingPlayers > 4){
            if(input.next().charAt(0) == 'l'){
                //Go backwards until a live player is found, twice. Hence 2 for loops
                for(int c = players.size()-1; c > 0; c--){
                    if(players.get(c).getHealth() > 0){
                        for(int d = c; c> 0; c--){
                            if(players.get(d).getHealth() > 0){
                                players.get(d).takeHit();
                                if(players.get(d).getName().equals("Pedro Ramirez")){
                                    if(players.get(d).arrows > 0){
                                        System.out.println("Pedro dropped an arrow!");
                                        players.get(d).arrows--;
                                        players.get(d).arrowPile++;
                                    }
                                }
                                if(checkPlayer(players.get(d))){
                                    players.get(d).revealRole();
                                    vultureTime(players);
                                }
                                c = 100;
                                System.out.println("You shot " + players.get(d).getName() + "!");
                                break;
                            }
                        }
                    }
                }
            } else {
                //Go forward until a live player is found, twice. Hence 2 for loops
                for(int c = 1; c < players.size(); c++){
                    if(players.get(c).getHealth() > 0){
                        for(int d = c; c < players.size(); c++){
                            if(players.get(d).getHealth() > 0){
                                players.get(d).takeHit();
                                if(players.get(d).getName().equals("Pedro Ramirez")){
                                    if(players.get(d).arrows > 0){
                                        System.out.println("Pedro dropped an arrow!");
                                        players.get(d).arrows--;
                                        players.get(d).arrowPile++;
                                    }
                                }
                                if(checkPlayer(players.get(d))){
                                    players.get(d).revealRole();
                                    vultureTime(players);
                                }
                                c = 100;
                                System.out.println("You shot " + players.get(d).getName() + "!");
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            //If there aren't enough players, run Bulls Eye 1
            bullsEyeOne(players, input);
        }

    }

    //CPU versions of the same methods, pass it the cpu player along with the arraylist
    public static void bullsEyeOne(ArrayList<Player> players, Scanner input, Player cpu){
        System.out.print("You've got a free shot, to the left or right? - ");

        //Find where this player is in the list
        int location;
        for(location = 0; location < players.size(); location++){
            if(players.get(location) == cpu)
                break;
        }


        //Decide which direction randomly
        if((int)(Math.random() * 11) < 6){
            System.out.println(" Left ");
            //Go backwards from the current player until a live player is found

            for(int c = location-1; c >= 0; c--){
                if(players.get(c).getHealth() > 0){
                    System.out.println("You shot " + players.get(c).getName() + "!");
                    players.get(c).takeHit();
                    if(players.get(c).getName().equals("Pedro Ramirez")){
                        if(players.get(c).arrows > 0){
                            System.out.println("Pedro dropped an arrow!");
                            players.get(c).arrows--;
                            players.get(c).arrowPile++;
                        }
                    }
                    if(checkPlayer(players.get(c))){
                        players.get(c).revealRole();
                        vultureTime(players);
                    }
                    return;
                }
            }
            //Loop around the other side if we hit the end of the list
            for(int c = players.size() -1; c > location; c--){
                if(players.get(c).getHealth() > 0){
                    System.out.println("You shot " + players.get(c).getName() + "!");
                    players.get(c).takeHit();
                    if(players.get(c).getName().equals("Pedro Ramirez")){
                        if(players.get(c).arrows > 0){
                            System.out.println("Pedro dropped an arrow!");
                            players.get(c).arrows--;
                            players.get(c).arrowPile++;
                        }
                    }
                    if(checkPlayer(players.get(c))){
                        players.get(c).revealRole();
                        vultureTime(players);
                    }
                    return;
                }
            }
        } else {
            //Go backwards from the current player until a live player is found
            System.out.println(" Right ");

            for(int c = location; c < players.size(); c++){
                //If the player is at the end of the list, skip to the next loop
                if(location == players.size()-1)
                    break;
                if(players.get(c).getHealth() > 0){
                    System.out.println("You shot " + players.get(c).getName() + "!");
                    players.get(c).takeHit();
                    if(players.get(c).getName().equals("Pedro Ramirez")){
                        if(players.get(c).arrows > 0){
                            System.out.println("Pedro dropped an arrow!");
                            players.get(c).arrows--;
                            players.get(c).arrowPile++;
                        }
                    }
                    if(checkPlayer(players.get(c))){
                        players.get(c).revealRole();
                        vultureTime(players);
                    }
                    return;
                }
            }
            //Loop around the other side if we hit the end of the list
            for(int c = 0; c < location; c++){
                if(players.get(c).getHealth() > 0){
                    System.out.println("You shot " + players.get(c).getName() + "!");
                    players.get(c).takeHit();
                    if(players.get(c).getName().equals("Pedro Ramirez")){
                        if(players.get(c).arrows > 0){
                            System.out.println("Pedro dropped an arrow!");
                            players.get(c).arrows--;
                            players.get(c).arrowPile++;
                        }
                    }
                    if(checkPlayer(players.get(c))){
                        players.get(c).revealRole();
                        vultureTime(players);
                    }
                    break;
                }
            }
        }
    }

    public static void bullsEyeTwo(ArrayList<Player> players, int livingPlayers, Scanner input, Player cpu){
        System.out.print("You've got a free shot, to the left or right? - ");

        //Find where this player is in the list
        int location;
        for(location = 0; location < players.size(); location++){
            if(players.get(location) == cpu)
                break;
        }

        //Check if there are enough players
        int skipped = 0;
        if(livingPlayers > 4){
            if((int)(Math.random() * 11) < 6){
                System.out.println(" Left ");
                //Go backwards until a live player is found, twice. Hence 2 for loops
                for(int c = location-1; c >= 0; c--){
                    if(players.get(c).getHealth() > 0){
                        skipped++;
                        for(int d = c; d>=0; d--){
                            if(players.get(d).getHealth() > 0){
                                System.out.println("You shot " + players.get(c).getName() + "!");
                                players.get(c).takeHit();
                                if(players.get(c).getName().equals("Pedro Ramirez")){
                                    if(players.get(c).arrows > 0){
                                        System.out.println("Pedro dropped an arrow!");
                                        players.get(c).arrows--;
                                        players.get(c).arrowPile++;
                                    }
                                }
                                if(checkPlayer(players.get(c))){
                                    players.get(c).revealRole();
                                    vultureTime(players);
                                }
                                return;
                            }
                        }
                        //Assuming we haven't found a second person to shoot, we loop again from the end
                        for(int d = players.size(); d > location; d--){
                            if(players.get(d).getHealth() > 0){
                                skipped++;
                                if(skipped >=2){
                                    System.out.println("You shot " + players.get(d).getName() + "!");
                                    players.get(d).takeHit();
                                    if(players.get(d).getName().equals("Pedro Ramirez")){
                                        if(players.get(d).arrows > 0){
                                            System.out.println("Pedro dropped an arrow!");
                                            players.get(d).arrows--;
                                            players.get(d).arrowPile++;
                                        }
                                    }
                                    if(checkPlayer(players.get(d))){
                                        players.get(d).revealRole();
                                        vultureTime(players);
                                    }
                                    return;
                                }
                            }
                            //Final case when both are on the second loop around
                            for(int e = d; e>=0; e--){
                                if(players.get(e).getHealth() > 0){
                                    System.out.println("You shot " + players.get(e).getName() + "!");
                                    players.get(e).takeHit();
                                    if(players.get(e).getName().equals("Pedro Ramirez")){
                                        if(players.get(e).arrows > 0){
                                            System.out.println("Pedro dropped an arrow!");
                                            players.get(e).arrows--;
                                            players.get(e).arrowPile++;
                                        }
                                    }
                                    if(checkPlayer(players.get(e))){
                                        players.get(e).revealRole();
                                        vultureTime(players);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            } else {
                //Go forward until a live player is found, twice. Hence 2 for loops
                for(int c = location; c < players.size(); c++){
                    if(players.get(c).getHealth() > 0){
                        skipped++;
                        for(int d = c; d <= players.size(); d++){
                            if(players.get(d).getHealth() > 0){
                                System.out.println("You shot " + players.get(c).getName() + "!");
                                players.get(c).takeHit();
                                if(players.get(c).getName().equals("Pedro Ramirez")){
                                    if(players.get(c).arrows > 0){
                                        System.out.println("Pedro dropped an arrow!");
                                        players.get(c).arrows--;
                                        players.get(c).arrowPile++;
                                    }
                                }
                                if(checkPlayer(players.get(c))){
                                    players.get(c).revealRole();
                                    vultureTime(players);
                                }
                                return;
                            }
                        }
                        //Assuming we haven't found a second person to shoot, we loop again from the end
                        for(int d = 0; d < location; d++){
                            if(players.get(d).getHealth() > 0){
                                skipped++;
                                if(skipped >=2){
                                    System.out.println("You shot " + players.get(d).getName() + "!");
                                    players.get(d).takeHit();
                                    if(players.get(d).getName().equals("Pedro Ramirez")){
                                        if(players.get(d).arrows > 0){
                                            System.out.println("Pedro dropped an arrow!");
                                            players.get(d).arrows--;
                                            players.get(d).arrowPile++;
                                        }
                                    }
                                    if(checkPlayer(players.get(d))){
                                        players.get(d).revealRole();
                                        vultureTime(players);
                                    }
                                    return;
                                }
                            }
                            //Final case when both are on the second loop around
                            for(int e = d; e <= 0; e++){
                                if(players.get(e).getHealth() > 0){
                                    System.out.println("You shot " + players.get(e).getName() + "!");
                                    players.get(e).takeHit();
                                    if(players.get(e).getName().equals("Pedro Ramirez")){
                                        if(players.get(e).arrows > 0){
                                            System.out.println("Pedro dropped an arrow!");
                                            players.get(e).arrows--;
                                            players.get(e).arrowPile++;
                                        }
                                    }
                                    if(checkPlayer(players.get(e))){
                                        players.get(e).revealRole();
                                        vultureTime(players);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            //If there aren't enough players, run Bulls Eye 1
            bullsEyeOne(players, input, cpu);
        }

    }

    //Gatling gun
    public static void runTheGat(ArrayList<Player> players, Player currPlayer, Scanner input){
        System.out.println("Everyone gets the heat!");

        for(Player attackedPlayer:players){
            if(attackedPlayer == currPlayer || checkPlayer(attackedPlayer) || attackedPlayer.getName().equals("Paul Regret"))
                continue;
            attackedPlayer.takeHit();
            if(attackedPlayer.getName().equals("Pedro Ramirez")){
                if(attackedPlayer.arrows > 0){
                    System.out.println("Pedro dropped an arrow!");
                    attackedPlayer.arrows--;
                    attackedPlayer.arrowPile++;
                }
            }
            System.out.println(attackedPlayer.getName() + " # Minus 1 HP #");
            //Check if they're alive
            if(checkPlayer(attackedPlayer)){
                attackedPlayer.revealRole();
                vultureTime(players);
            }
        }
    }



    //Player interactions
    //Checks if there are any arrows since they must be resolved immediately
    public static void checkArrows(Dice[] dice, Player currPlayer, ArrayList<Player> players){
        for(Dice current: dice)
            if(current.getFace().equals("arrow")){
                if(currPlayer.getPile() == 0){

                    //Display - Launch indian attack
                    System.out.println("## Look out! INDIAN ATTACK!! ##\n");
                    for(Player attackedPlayer:players){
                        if(checkPlayer(attackedPlayer))
                            continue;
                        //If theyre dead, we skip them. If not, we attack and check their health again
                        //Jourdonnais Ability
                        if(attackedPlayer.getName().equals("Jourdonnais")){
                            attackedPlayer.jourdArrows();
                            System.out.println(attackedPlayer.getName() + " took 1 damage");
                            if(checkPlayer(attackedPlayer)){
                                attackedPlayer.revealRole();
                                vultureTime(players);
                                continue;
                            }
                            continue;
                        }

                        attackedPlayer.dropArrow();
                        System.out.println(attackedPlayer.getName() + " took 1 damage");
                        if(checkPlayer(attackedPlayer)){
                            attackedPlayer.revealRole();
                            vultureTime(players);
                            continue;
                        }
                    }
                } else {
                    System.out.println(currPlayer.getName() + " got an arrow!");
                    currPlayer.takeArrow();
                }
            }
    }

    //Checks if the current player is still alive, if they are dead, it returns true. else false VERY IMPORTANT, DONT CHANGE
    public static boolean checkPlayer(Player currPlayer){
        if(currPlayer.health <= 0){
            //Return if they're dead
            return true;
        }
        return false;
    }


    //Special player abilities
    //For human sid player
    public static void sidTurn(ArrayList<Player> players, Player player, Scanner input){
        System.out.print("Pick a player to gain health - (0 - " + (players.size()-1) + ")");

        players.get(input.nextInt()).health++;
    }

    //For CPU sid player
    public static void sidTurn(ArrayList<Player> players, Player cpu){
        if(cpu.getHealth() < 3){
            System.out.println(cpu.getName() + " chose to heal himself.");
            cpu.drinkUp();
        } else {
            for(int c = players.size()-1; c >= 0; c--){
                if(!checkPlayer(players.get(c))){
                    players.get(c).health++;
                    System.out.println(cpu.getName() + " chose to heal " + players.get(c).getName() + ".");
                    break;
                }
            }
        }
    }

    //Vulture Sam Special Ability
    public static void vultureTime(ArrayList<Player> players){
        for(Player currPlayer:players){
            if(currPlayer.getName().equals("Vulture Sam")){
                if(currPlayer.getHealth() <= 7){
                    currPlayer.drinkUp();
                    currPlayer.drinkUp();
                    System.out.println("Vulture Sam steals two life points like the creep he is!");
                } else if(currPlayer.getHealth() == 8){
                    currPlayer.drinkUp();
                    System.out.println("Vulture Sam steals one life points like the creep he is!");
                } else {
                    System.out.println("Vulture Sam trys to steal life points but he's maxed out.");
                }
            }
        }
    }

    //Another iteration of the gamesim method
    public static void play(int expanVersion, ArrayList<Player> players, Scanner input){

        int currPlayer = 0;
        //old Saloon expansion
        if(expanVersion==1){

        } else if(expanVersion == 2){
            //Then Undead or Alive
        } else {
            //Finally the only other option is vanilla Bang!

            //There are 5 standard dice
            Dice[] dice = {new Dice(0), new Dice(0), new Dice(0), new Dice(0), new Dice(0)};
            boolean finished = false;



            //Actual game logic
            while(!finished){
                //Check if the game is over
                if(players.get(0).sheriffs <= 0 && players.get(0).outlaws == 0){
                    System.out.println("Looks like the sheriff has died! All outlaws win!");
                    finished = true;
                    continue;
                } else if(players.get(0).sheriffs > 0 && players.get(0).outlaws <= 0 && players.get(0).renegades <= 0){
                    System.out.println("Looks like the outlaws and renegades have died! The Sheriff wins this time!");
                    finished = true;
                    continue;
                } else if(players.get(0).sheriffs <= 0 && players.get(0).outlaws <= 0 && players.get(0).deputies <= 0 && players.get(0).renegades == 1){
                    System.out.println("The renegade is the last one standing! We've got ourselves a winner!");
                    finished = true;
                    continue;
                } else if(players.get(0).sheriffs <= 0 && players.get(0).outlaws <= 0 && players.get(0).deputies <= 0 && players.get(0).renegades > 1){
                    System.out.println("Since there's more than 1 renegade left standing, the outlaws win!");
                    finished = true;
                    continue;
                }
                //Gonna use this for Bulls eye dice
                int livingPlayers = players.size();
                for(Player item: players){
                    if(checkPlayer(item)){
                        livingPlayers--;
                    } else {
                        System.out.println(item.getName() + " is still alive.");
                    }
                }
                System.out.println("\n===============================\n");

                //Final check if all
                for(Player item: players){
                    if(checkPlayer(item))
                        livingPlayers--;
                }

                //Check if the main player is alive
                if(checkPlayer(players.get(0))){
                    System.out.println("Whoops, looks like you're already dunzo partner!\nOnto the next!");
                } else {
                    if(players.get(0).getName().equals("Sid Ketchum"))
                        sidTurn(players, players.get(0), input);

                    //Button interactions will be noted with next().charAt(0);
                    System.out.println("Roll the dice!");
                    input.next().charAt(0);
                    rollAllDice(dice);

                    //Display dice and check arrows
                    System.out.print("These are your current dice - ");
                    showDice(dice);
                    checkArrows(dice, players.get(currPlayer), players);

                    //Handle rerolling
                    System.out.println("Would you like to reroll? (y/n) - ");
                    if(input.next().charAt(0) == 'y'){
                        //Lucky Duke special
                        if(players.get(0).getName().equals("Lucky Duke")){
                            for(int c = 0; c<4;c++){
                                System.out.println("Reroll the dice!");
                                input.next().charAt(0);
                                reroll(dice, players.get(0));
                                showDice(dice);
                                checkArrows(dice, players.get(currPlayer), players);

                                //Check if they have 3 dynamites and if they died
                                if(players.get(currPlayer).dynamiteRolls >= 3){
                                    System.out.println("Sorry, too many dynamites! ## KABLAM ##");
                                    System.out.println("~ Minus 1 HP");
                                    players.get(currPlayer).takeHit();
                                    if(players.get(currPlayer).getName().equals("Pedro Ramirez")){
                                        if(players.get(currPlayer).arrows > 0){
                                            System.out.println("Pedro dropped an arrow!");
                                            players.get(currPlayer).arrows--;
                                            players.get(currPlayer).arrowPile++;
                                        }
                                    }

                                    if(checkPlayer(players.get(currPlayer))){
                                        livingPlayers--;
                                        players.get(currPlayer).revealRole();
                                        vultureTime(players);
                                    }
                                    input.next().charAt(0);
                                    break;
                                } else {
                                    System.out.println("Would you like to reroll again? (y/n) - ");
                                    if(input.next().charAt(0) == 'n')
                                        break;
                                }
                            }
                        } else {
                            for(int c = 0; c<3;c++){
                                System.out.println("Reroll the dice!");
                                input.next().charAt(0);
                                reroll(dice, players.get(0));
                                showDice(dice);
                                checkArrows(dice, players.get(currPlayer), players);

                                //Check if they have 3 dynamites and if they died
                                if(players.get(currPlayer).dynamiteRolls >= 3){
                                    System.out.println("Sorry, too many dynamites! ## KABLAM ##");
                                    System.out.println("~ Minus 1 HP");
                                    players.get(currPlayer).takeHit();
                                    if(players.get(currPlayer).getName().equals("Pedro Ramirez")){
                                        if(players.get(currPlayer).arrows > 0){
                                            System.out.println("Pedro dropped an arrow!");
                                            players.get(currPlayer).arrows--;
                                            players.get(currPlayer).arrowPile++;
                                        }
                                    }

                                    if(checkPlayer(players.get(currPlayer))){
                                        livingPlayers--;
                                        players.get(currPlayer).revealRole();
                                        vultureTime(players);
                                    }
                                    input.next().charAt(0);
                                    break;
                                } else {
                                    System.out.println("Would you like to reroll again? (y/n) - ");
                                    if(input.next().charAt(0) == 'n')
                                        break;
                                }
                            }
                        }

                    }

                    //End of player interaction, now we resolve dice
                    int gatCount = 0;
                    for(Dice current: dice){
                        //Handle Bull's eye 1
                        if(current.getFace().equals("be 1")){
                            //Calamity Janet - Ability
                            if(players.get(0).getName().equals("Calamity Janet")){
                                System.out.println("You get to choose which bulls eye! (1/2)- ");
                                if(input.nextInt() == 1){
                                    bullsEyeOne(players, input);
                                    continue;
                                } else {
                                    bullsEyeTwo(players, livingPlayers, input);
                                    continue;
                                }
                            }

                            //Normal Bulls Eye
                            bullsEyeOne(players, input);
                        }
                        //Handle Bull's eye 2
                        if(current.getFace().equals("be 2")){
                            //Calamity Janet - Ability
                            if(players.get(0).getName().equals("Calamity Janet")){
                                System.out.println("You get to choose which bulls eye! (1/2)- ");
                                if(input.nextInt() == 1){
                                    bullsEyeOne(players, input);
                                    continue;
                                } else {
                                    bullsEyeTwo(players, livingPlayers, input);
                                    continue;
                                }
                            }

                            //Normal Bulls Eye
                            bullsEyeTwo(players, livingPlayers, input);
                        }
                        //Handle beer
                        if(current.getFace().equals("beer")){
                            //Jesse Jones - Ability
                            if(players.get(0).getName().equals("Jesse Jones") && players.get(0).getHealth() <= 4){
                                players.get(0).drinkUp();
                                players.get(0).drinkUp();
                                System.out.println("You got a beer as Jesse Jones, a chronic alcoholic! # Plus 2 HP #");
                            } else if(players.get(0).getHealth() + 1 > players.get(0).initHealth){
                                System.out.println("You got a beer! But you already have max health.");
                            } else {
                                players.get(0).drinkUp();
                                System.out.println("You got a beer! # Plus 1 HP #");
                            }
                        }
                        //Handle gatiling guns
                        if(current.getFace().equals("gatling")){
                            gatCount++;
                            if(players.get(0).getName().equals("Willy the Kid") && gatCount >= 2){
                                runTheGat(players, players.get(0), input);
                            } else if(gatCount >= 3){
                                runTheGat(players, players.get(0), input);
                            }
                        }
                    }

                    //Reset dynamite rolls
                    players.get(currPlayer).dynamiteRolls = 0;
                }

                //Run CPU simulation X times for each other player
                for(Player cpu: players){
                    //skip the non cpu
                    if(cpu == players.get(0))
                        continue;
                    cpuSim(expanVersion, players, input, cpu);
                }
            }
        }
    }



    //Method to handle CPU interactions, only one turn.
    public static void cpuSim(int expanVersion, ArrayList<Player> players, Scanner input, Player cpu){
        //Check if they are alive
        if(checkPlayer(cpu)){
            System.out.println("Looks like " + cpu.getName() + " has already been merced!\nOn to the next!");
            input.next().charAt(0);
            return;
        }
        System.out.println("Its now " + cpu.getName() + "'s turn!");

            //old Saloon expansion
            if(expanVersion==1){

            } else if(expanVersion == 2){
                //Then Undead or Alive
            } else {
                //Finally the only other option is vanilla Bang!

                //There are 5 standard dice
                Dice[] dice = {new Dice(0), new Dice(0), new Dice(0), new Dice(0), new Dice(0)};

                //Gonna use this for Bulls eye dice
                int livingPlayers = players.size();
                for(Player item: players)
                    if(checkPlayer(item))
                        livingPlayers--;

                //Checks if its sid's turn
                if(cpu.getName().equals("Sid Ketchum"))
                    sidTurn(players, cpu);

                //Button interactions will be noted with next().charAt(0);
                System.out.println("Roll the dice!");
                input.next().charAt(0);
                rollAllDice(dice);

                //Display dice and check arrows
                System.out.print("These are the current dice - ");
                showDice(dice);
                checkArrows(dice, cpu, players);
                if(checkPlayer(cpu)){
                    System.out.println("Since " + cpu.getName() + "has been eliminated, we'll be ending their turn here.");
                    return;
                }

                //Handle rerolling for CPU, randomly
                System.out.print("Would you like to reroll? - ");
                if((int)(Math.random() * 11) < 4){
                    System.out.println("Oh yeah partner!");
                    //Lucky Duke Special
                    if(players.get(0).getName().equals("Lucky Duke")){
                        for(int c = 0; c<4;c++){
                            System.out.println("Reroll the dice!");
                            input.next().charAt(0);
                            reroll(dice, cpu);
                            showDice(dice);
                            checkArrows(dice, cpu, players);

                            //Check if they have 3 dynamites and if they died
                            if(cpu.dynamiteRolls >= 3){
                                System.out.println("Sorry, too many dynamites! ## KABLAM ##");
                                System.out.println("~ Minus 1 HP");
                                cpu.takeHit();
                                if(cpu.getName().equals("Pedro Ramirez")){
                                    if(cpu.arrows > 0){
                                        System.out.println("Pedro dropped an arrow!");
                                        cpu.arrows--;
                                        cpu.arrowPile++;
                                    }
                                }

                                if(checkPlayer(cpu)){
                                    livingPlayers--;
                                    cpu.revealRole();
                                    vultureTime(players);
                                }
                                input.next().charAt(0);
                                break;
                            } else {
                                System.out.print("Would you like to reroll again? - ");
                                if((int)(Math.random() * 99) > 65){
                                    System.out.println("No siree!");
                                    break;
                                }
                            }
                        }
                    } else{
                        for(int c = 0; c<3;c++){
                            System.out.println("Reroll the dice!");
                            input.next().charAt(0);
                            reroll(dice, cpu);
                            showDice(dice);
                            checkArrows(dice, cpu, players);

                            //Check if they have 3 dynamites and if they died
                            if(cpu.dynamiteRolls >= 3){
                                System.out.println("Sorry, too many dynamites! ## KABLAM ##");
                                System.out.println("~ Minus 1 HP");
                                cpu.takeHit();
                                if(cpu.getName().equals("Pedro Ramirez")){
                                    if(cpu.arrows > 0){
                                        System.out.println("Pedro dropped an arrow!");
                                        cpu.arrows--;
                                        cpu.arrowPile++;
                                    }
                                }

                                if(checkPlayer(cpu)){
                                    livingPlayers--;
                                    cpu.revealRole();
                                    vultureTime(players);
                                }
                                input.next().charAt(0);
                                break;
                            } else {
                                System.out.print("Would you like to reroll again? - ");
                                if((int)(Math.random() * 99) > 65){
                                    System.out.println("No siree!");
                                    break;
                                }
                            }
                        }
                    }

                } else {
                    System.out.println("No thanks");
                }

                //End of player interaction, now we resolve dice
                int gatCount = 0;
                for(Dice current: dice){
                    //Handle Bull's eye 1
                    if(current.getFace().equals("be 1")){
                        //Calamity Janet ability CPU version
                        if(players.get(0).getName().equals("Calamity Janet")){
                            System.out.print("You get to choose which bulls eye! (1/2)- ");
                            if((int)Math.random() * 2 == 0){
                                System.out.println("1");
                                bullsEyeOne(players, input, cpu);
                                continue;
                            } else {
                                bullsEyeTwo(players, livingPlayers, input,cpu);
                                continue;
                            }
                        }
                        //Normal bulls eye one
                        bullsEyeOne(players, input, cpu);
                    }
                    //Handle Bull's eye 2
                    if(current.getFace().equals("be 2")){
                        //Calamity Janet ability CPU version
                        if(players.get(0).getName().equals("Calamity Janet")){
                            System.out.print("You get to choose which bulls eye! (1/2)- ");
                            if((int)Math.random() * 2 == 0){
                                System.out.println("1");
                                bullsEyeOne(players, input, cpu);
                                continue;
                            } else {
                                bullsEyeTwo(players, livingPlayers, input,cpu);
                                continue;
                            }
                        }
                        //Normal bulls eye one
                        bullsEyeTwo(players, livingPlayers, input, cpu);
                    }
                    //Handle beer
                    if(current.getFace().equals("beer")){
                        if(cpu.getName().equals("Jesse Jones") && cpu.getHealth() <= 4){
                            cpu.drinkUp();
                            cpu.drinkUp();
                            System.out.println("You got a beer as Jesse Jones, a chronic alcoholic! # Plus 2 HP #");
                        } else if(cpu.getHealth() + 1 > cpu.initHealth){
                            System.out.println("You got a beer! But you already have max health.");
                        } else {
                            cpu.drinkUp();
                            System.out.println("You got a beer! # Plus 1 HP #");
                        }
                    }
                    //Handle gatiling guns
                    if(current.getFace().equals("gatling")){
                        gatCount++;
                        if(cpu.getName().equals("Willy the Kid") && gatCount >= 2){
                            runTheGat(players, cpu, input);
                        } else if(gatCount >= 3){
                            runTheGat(players, cpu, input);
                        }
                    }
                }

                //Reset dynamite rolls
                cpu.dynamiteRolls = 0;
            }
    }

    public static void main(String[] args){
        System.out.println("Welcome to Bang! now with expansions! Choose your experience - 0 (Vanilla Bang!) 1 (Old Saloon) 2 (Undead or Alive) ==> ");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();
        switch(selection){
            case 0: startGame(0, input);
                break;
            case 1: startGame(1, input);
                break;
            case 2: startGame(2, input);
                break;
            default : startGame(0, input);
        }
        input.close();
    }
}
