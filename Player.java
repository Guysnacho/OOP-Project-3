//CS 2365
//Author - Samuel Adetunji, Mushfique Khan
//Project 3
//March 28 2020

public class Player{
    //Attributes
    public static int arrowPile;
    //Removed initHealth because we never actually used it
    public int health, initHealth;
    public int arrows;
    public String[] attributes;
    public int dynamiteRolls = 0;

    //Constructor
    public Player(int hp, String name){
        health = initHealth = hp;
        attributes = new String[2];
        //Each player has a name and a role. We'll use this to keep track of their special abilities.
        //Gonna need a lot of if statements to check but its simple
        attributes[0] = name;
        attributes[1] = "";
        arrows = 0;
        arrowPile = 9;
    }

    //Health can only be changed by taking damage or by drinking Keeping simple methods on one line.
    public void takeHit(){health--;}

    public void drinkUp(){health++;}

    public int getHealth(){return health;}

    //Arrow methods
    public void takeArrow(){
        arrows++;
        arrowPile--;
    }
    // Drops players arrows and returns them to the pile.
    public void dropArrow(){
        //We can't have negative arrows and we start at zero
        health = health - arrows;
        arrowPile += arrows;
        arrows = 0;
    }

    //Variation just for Jourdonnais
    public void jourdArrows(){
        health = health - 1;
        arrowPile += arrows;
        arrows = 0;
    }

    public static int getPile() {return arrowPile;}

    public int getArrows(){return arrows;}

    //Attribute methods
    public String getName(){return attributes[0];}

    //Removed setName because we don't need it. When we initialize a player the name is already set.

    public String getRole(){return attributes[1];}

    public void setRole(String role){attributes[1]=role;}

    public void revealRole() {
        System.out.println(getName() + " has been eliminated");
        System.out.println(getName() + " was a " + getRole());
    }
}
