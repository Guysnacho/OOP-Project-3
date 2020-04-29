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

    //Another iteration of the gameim method
    public static void play(int expanVersion, ArrayList<Player> players){
        //old Saloon expansion
        if(expanVersion==1){

        } else if(expanVersion == 2){
            //Then Undead or Alive
        } else {
            //Finally the only other option is vanilla Bang!

        }

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
