//CS 2365
//Authors - Samuel Adetunji, Isaac Ojigho, Mushfique Khan
//Project 3
//March 28 2020

import java.util.*;
import java.lang.Math;

//This file will be our gameSim class. Repl won't run it unless
// its named Main though SO DON'T CHANGE LINE 11 until we're ready to submit the assignment
public class Main{

    public static void startGame(int expanVersion){
        System.out.println("### BANG! - The Dice Game###");

        //The whole game will just loop through an arraylist of players over and over until a winner is found.
        //Easiest way to run it imo
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> characters = loadCharacters(expanVersion);
        Scanner input = new Scanner(System.in);

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
        input.close();
        //while(Players are still alive/Outlaws haven't killed the sheriff/Renegade hasn't killed everyone yet)
        //usersim(players);
        play(expanVersion, players);
    }

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
            characters.add(new Player(9,"Rose Doolan"));
            characters.add(new Player(8,"Sid Ketchum"));
            characters.add(new Player(8,"Slab The Killer"));
            characters.add(new Player(8,"Suzy Lafayette"));
            characters.add(new Player(9,"Vulture Sam"));
            characters.add(new Player(8,"Willy The Kid"));
            characters.add(new Player(9,"Apache Kid"));
            characters.add(new Player(7,"Elena Fuente"));
        } else if(expanVersion == 2) {
            //Undead or Alive possible characters
            characters.add(new Player(8,"Bart Cassidy"));
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
            characters.add(new Player(8,"Bart Cassidy"));
            characters.add(new Player(8,"Black Jack"));
            characters.add(new Player(8,"Calamity Janet"));
            characters.add(new Player(7,"El Gringo"));
            characters.add(new Player(9,"Jesse Jones"));
            characters.add(new Player(7,"Jourdonnais"));
            characters.add(new Player(7,"Kit Carlson"));
            characters.add(new Player(8,"Lucky Duke"));
            characters.add(new Player(9,"Paul Regret"));
            characters.add(new Player(8,"Pedro Ramirez"));
            characters.add(new Player(9,"Rose Doolan"));
            characters.add(new Player(8,"Sid Ketchum"));
            characters.add(new Player(8,"Slab The Killer"));
            characters.add(new Player(8,"Suzy Lafayette"));
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

    // this is the user simulation it allows someone to play for player zero
    public static void usersim(ArrayList<Player> players){
        Scanner input = new Scanner(System.in);
        int YB;
        // these are the attributes
        int reroll = 0;
        int counter = 0;
        int option, dynamite, gatiling;
        dynamite = 0;
        gatiling = 0;
        int rice;
        int answer;
        int t;
        System.out.println("\n You are player Zero(0)");
        // this is the loop for the players turn
        for(int i = 0; i <= selection; i++){

            System.out.println("it is player " + i + "`s turn");
            counter = 0;
            // Loop for checking if a players health is depleted before going into the next loop
            for(t = 0; t <= selection; t++){
                if (players.get(t).health <= 0) {
                    counter = 4;
                    System.out.println("The " + players.get(t).getRole() + " is dead");
                }//else if(i == selection){i = 0;} if you want to simulate the enitre game uncomment this line
            }
            // this is the loop for the dice
            do{
                rice = rollDice();
                System.out.println("\n you rolled a " + rice);
                switch(rice){
                    case 1: {
                        players.get(i).takeArrow();
                        if (Player.getPile() == 0) {
                            System.out.println("you picked the last arrow. Everyone drops their arrows.");
                            for (int j = 0; j <= selection; j++) {
                                players.get(j).dropArrow();
                                if (players.get(j).getHealth() == 0) {
                                    players.get(j).revealRole(j);
                                }
                            }
                        }
                        System.out.println("you rolled an arrow would you like to reroll? 1 for yes and 2 for no ");
                        if(i == 0){
                            answer = YB = input.nextInt();
                        } else {answer = random();}
                        if (answer == 1 && reroll != 2) {
                            System.out.println("This player picked reroll");
                            reroll++;
                            break;
                        } else {
                            System.out.println(" you have added an arrow to your pile ");
                            System.out.println("you now have " + players.get(i).health + " lives");
                            if (players.get(i).getHealth() <= 0) {
                                counter = 4;
                                break;
                            }
                            counter++;
                            break;
                        }
                    }
                    case 2: {
                        System.out.println("you rolled a BEER would you like to reroll? 1 for yes and 2 for no ");
                        if(i == 0){
                            answer = YB = input.nextInt();
                        } else {answer = random();}
                        if (answer == 1 && reroll != 2) {
                            System.out.println("This player picked reroll");
                            reroll++;
                            break;
                        } else {
                            System.out.println(" you drank a beer and gained a life point" );
                            players.get(i).drinkUp();
                            System.out.println("you now have " + players.get(i).health + " lives");
                            if (players.get(i).getHealth() <= 0) {
                                counter = 4;
                            }
                            counter++;
                            break;
                        }
                    }
                    case 3: {
                        System.out.println("you rolled a dynamite you cannot reroll this dice ");
                        dynamite++;
                        if (dynamite == 3) {
                            System.out.println("you have rolled 3 dynamites so you lose a life point ");
                            dynamite = 0;
                            players.get(i).takeHit();
                            System.out.println("you now have " + players.get(i).health + " lives");
                            if (players.get(i).getHealth() <= 0) {
                                players.get(i).revealRole(i);
                                counter = 4;
                            }
                            counter++;
                            break;
                        } else{
                            counter++;
                            break;}
                    }
                    case 4: {
                        System.out.println("you rolled a oneshot, would you like to reroll? 1 for yes and 2 for no ");
                        if(i == 0){
                            answer = YB = input.nextInt();
                        } else {answer = random();}
                        if (answer == 1 && reroll != 2) {
                            System.out.println("This player picked reroll");
                            reroll++;
                            break;
                        } else {
                            System.out.println(" which direction do you want to shoot 1 for left and 2 for right ");
                            if(i == 0){
                                answer = YB = input.nextInt();
                            } else {answer = random();}
                            if (answer == 2) {
                                System.out.println("this player picked right");
                                if (i == selection){
                                    i = 0;
                                    players.get(i).takeHit();
                                    System.out.println("that player now has " + players.get(i).health + " lives");
                                    if (players.get(i).health <= 0) {
                                        counter = 4;
                                    } i = selection; counter++; break;
                                }else{ i++;
                                    players.get(i).takeHit();
                                    //counter++;
                                    System.out.println("that player now has " + players.get(i).health + " lives");
                                }if (players.get(i).health <= 0) {
                                    counter = 4;
                                }
                                i--; counter++;
                                break;
                            } else {
                                System.out.println("this player picked left");
                                if(i == 0){
                                    i = selection;
                                    players.get(i).takeHit();
                                    //counter++;
                                    System.out.println("that player now has " + players.get(i).health + " lives");
                                    if (players.get(i).health <= 0) {
                                        counter = 4;
                                    }
                                    i = 0;counter++;
                                    break;
                                }else{
                                    i--;
                                    players.get(i).takeHit();
                                    //counter++;
                                    System.out.println("that player now has " + players.get(i).health + " lives");
                                    if (players.get(i).health <= 0) {
                                        counter = 4;
                                    }
                                    i++;counter++;
                                    break;
                                }
                            }
                        }
                    }
                    case 5: {
                        System.out.println("you rolled a doubleshot, would you like to reroll? 1 for yes and 2 for no ");
                        if(i == 0){
                            answer = YB = input.nextInt();
                        } else {answer = random();}
                        if (answer == 1 && reroll != 2) {
                            System.out.println("This player picked reroll");
                            reroll++;
                            break;
                        }else {
                            System.out.println(" which direction do you want to shoot 1 for left and 2 for right ");
                            if(i == 0){
                                answer = YB = input.nextInt();
                            } else {answer = random();}
                            if (answer == 2) {
                                System.out.println("this player picked right");
                                if (i == selection){
                                    i = 0;
                                    players.get(i).takeHit();
                                    players.get(i).takeHit();
                                    System.out.println("that player now has " + players.get(i).health + " lives");
                                    if (players.get(i).health <= 0) {
                                        counter = 4;
                                    } i = selection; counter++; break;
                                }else{ i++;
                                    players.get(i).takeHit();
                                    players.get(i).takeHit();
                                    //counter++;
                                    System.out.println("that player now has " + players.get(i).health + " lives");
                                }if (players.get(i).health <= 0) {
                                    counter = 4;
                                }
                                i--; counter++;
                                break;
                            } else {
                                System.out.println("this player picked left");
                                if(i == 0){
                                    i = selection;
                                    players.get(i).takeHit();
                                    players.get(i).takeHit();
                                    //counter++;
                                    System.out.println("that player now has " + players.get(i).health + " lives");
                                    if (players.get(i).health <= 0) {
                                        counter = 4;
                                    }
                                    i = 0; counter++;
                                    break;
                                }else{
                                    i--;
                                    players.get(i).takeHit();
                                    players.get(i).takeHit();
                                    //counter++;
                                    System.out.println("that player now has " + players.get(i).health + " lives");
                                    if (players.get(i).health <= 0) {
                                        counter = 4;
                                    }
                                    i++; counter++;
                                    break;
                                }
                            }
                        }
                    }
                    case 6: {
                        System.out.println("you rolled an gatiling gun, would you like to reroll? 1 for yes and 2 for no  ");
                        if(i == 0){
                            answer = YB = input.nextInt();
                        } else {answer = random();}

                        if (answer == 1 && reroll != 2) {
                            System.out.println("This player picked reroll");
                            reroll++;
                            break;
                        }
                        gatiling++;
                        if (gatiling == 3) {
                            System.out.println("you have rolled 3 gatilings so everyone but you loses a life point ");
                            for(t = 0; t <= selection; t++){
                                if(t == i){
                                    System.out.println(players.get(i).getName() + " is doing the shooting");
                                }
                                players.get(t).takeHit();
                            }
                            // counter++;
                            gatiling = 0;
                            for(t = 0; t <= selection; t++){
                                if (players.get(t).health <= 0) {
                                    counter = 4;
                                } }
                            counter++;
                            break;
                        } else {
                            counter++;
                            break;
                        }
                    }
                }
            } while(counter <= 4);//while (counter != 5);
        }
    }

    //Method that handles rolling of all dice so its not sitting in the play method
    public static void rollAllDice(Dice[] dice){
        for(Dice current: dice){
            current.roll();
        }
    }

    //Method that handles rerolling
    public static void reroll(Dice[] dice, Player currPlayer){
        for(Dice current: dice){
            //Checks if its dynamite
            if(current.getFace().equals("dynamite")){
                System.out.println("Dynamite cannot be rerolled, hehehe");
                player.dynamiteRolls++;
                continue;
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
    public static void bullsEyeOne(ArrayList<Player> players){
        System.out.println("You've got a free shot, to the left or right? (l/r)- ");
        if(input.getChar() == 'l'){
            //Go backwards until a live player is found
            for(int c = players.size()-1; c > 0; c--){
                if(players.get(c).getHealth() > 0){
                    players.get(c).takeHit();
                    checkPlayer(players.get(c));
                    break;
                }
            }
        } else {
            //Go forward until a live player is found
            for(int c = 1; c < players.size(); c++){
                if(players.get(c).getHealth() > 0){
                    players.get(c).takeHit();
                    checkPlayer(players.get(c));
                    break;
                }
            }
        }
    }

    public static void bullsEyeTwo(ArrayList<Player> players, int livingPlayers){
        System.out.println("You've got a free shot, to the left or right? (l/r)- ");

        //Check if there are enough players
        if(livingPlayers > 4){
            if(input.getChar() == 'l'){
                //Go backwards until a live player is found, twice. Hence 2 for loops
                for(int c = players.size()-1; c > 0; c--){
                    if(players.get(c).getHealth() > 0){
                        for(int d = c; c> 0; c--){
                            if(players.get(d).getHealth() > 0){
                                players.get(d).takeHit();
                                checkPlayer(players.get(d));
                                c = 100;
                                System.out.println("You shot " + players.get(d).getName + "!");
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
                                checkPlayer(players.get(d));
                                c = 100;
                                System.out.println("You shot " + players.get(d).getName + "!");
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            //If there aren't enough players, run Bulls Eye 1
            bullsEyeOne(players);
        }

    }

    //Gatling gun
    public static void gatling(ArrayList<Player> players, Player currPlayer){
        System.out.println("Everyone gets the heat!");

        for(Player attackedPlayer:players){
            if(attackedPlayer == currPlayer || checkPlayer(attackedPlayer))
                continue;
            attackedPlayer.takeHit();
            System.out.println(attackedPlayer.getName() + " # Minus 1 HP #");
            //Check if they're alive
            if(checkPlayer(attackedPlayer))
                attackedPlayer.revealRole();
        }
    }



    //Player interactions
    //Checks if there are any arrows since they must be resolved immediately
    public static void checkArrows(Dice[] dice, Player currPlayer, ArrayList<Player> players){
        for(Dice current: dice)
            if(current.getFace().equals("arrow")){
                if(currPlayer.getPile() == 0){

                    //Display - Launch indian attack
                    System.out.println("## Look out! INDIAN ATTACK!! ##");
                    for(Player attackedPlayer:players){
                        if(checkPlayer(attackedPlayer))
                            continue;
                        //If theyre dead, we skip them. If not, we attack and check their health again
                        attackedPlayer.dropArrow();
                        checkPlayer(attackedPlayer);
                    }
                } else {currPlayer.takeArrow();}
            }
    }

    //Checks if the current player is still alive
    public static boolean checkPlayer(Player currPlayer){
        if(currPlayer.health <= 0){
            currPlayer.revealRole();
            //Return if they're alive
            return true;
        }
        return false;
    }




    //Another iteration of the gamesim method
    public static void play(int expanVersion, ArrayList<Player> players){
        Scanner input = new Scanner(System.in);

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

            //Gonna use this for Bulls eye dice
            int livingPlayers = players.size()-1;
            //Actual game logic
            while(!finished){
                //Button interactions will be noted with getChar();
                System.out.println("Roll the dice!");
                input.getChar();
                rollAllDice(dice);

                //Display dice and check arrows
                System.out.print("These are your current dice - ");
                showDice(dice);
                checkArrows(dice, players.get(currPlayer), players);

                //Handle rerolling
                System.out.println("Would you like to reroll? (y/n) - ");
                if(input.getChar() == 'y'){
                    for(int c = 0; c<3;c++){
                        System.out.println("Reroll the dice!");
                        input.getChar();
                        reroll(dice);
                        showDice(dice);
                        checkArrows(dice, players.get(currPlayer), players);

                        //Check if they have 3 dynamites and if they died
                        if(players.get(currPlayer.dynamiteRolls >= 3)){
                            System.out.println("Sorry, too many dynamites! ## KABLAM ##");
                            System.out.println("~ Minus 1 HP");
                            players.get(currPlayer.takeHit());

                            if(checkPlayer(players.get(currPlayer)))
                                livingPlayers--;
                            input.getChar();
                            break;
                        } else {
                            System.out.println("Would you like to reroll again? (y/n) - ");
                            if(input.getChar() == 'n')
                                break;
                        }
                    }
                }

                //End of player interaction, now we resolve dice
                int gatCount = 0;
                for(Dice current: dice){
                    //Handle Bull's eye 1
                    if(current.getFace().equals("be 1")){
                        bullsEyeOne();
                    }
                    //Handle Bull's eye 2
                    if(current.getFace().equals("be 2")){
                        bullsEyeOne();
                    }
                    //Handle beer
                    if(current.getFace().equals("beer")){
                        players.get(0).drinkUp();
                        System.out.println("You got a beer! # Plus 1 HP #");
                    }
                    //Handle gatiling guns
                    if(current.getFace().equals("gatling")){
                        gatCount++;
                        if(gatCount >= 3){
                            runTheGat(players);
                        }

                    }
                }

                //Reset dynamite rolls
                players.get(currPlayer).dynamiteRolls = 0;
                cpuSim(expanVersion, players);
            }
        }
    }

    //Method to handle CPU interactions
    public static void cpuSim(){

    }

    public static void main(String[] args){
        System.out.println("Welcome to Bang! now with expansions! Choose your experience - 0 (Vanilla Bang!) 1 (Old Saloon) 2 (Undead or Alive)") ==>;
        Scanner input = new Scanner(System.in);
        input.close();
        switch(selection){
            case 0: startGame(0);
                break;
            case 1: startGame(1);
                break;
            case 2: startGame(2);
                break;
            default : startGame(0);
        }

    }
}
