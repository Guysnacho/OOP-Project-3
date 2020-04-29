//CS 2365
//Author - Samuel Adetunji
//Project 3
//April 29 2020

public class Dice{

    //Array to hold the names of the faces
    String[] faces = new String[6];
    int currentFace;
    int type;
    public Dice(int selection){
        type = selection;
        switch(selection){
            case 0: vanillaDie();
                break;
            case 1: oldSaloonDie();
                break;
            case 2: undeadDie();
                break;
        }
    }

    //Build a vanilla bang die
    private void vanillaDie(){
        faces = new String[6];
        faces[0] = "arrow";
        faces[1] = "dynamite";
        faces[2] = "be 1";
        faces[3] = "be 2";
        faces[4] = "beer";
        faces[5] = "gatling";
    }

    //build a old saloon die
    private void oldSaloonDie(){
        faces = new String[6];
        faces[0] = "broken arrow";
        faces[1] = "bullet";
        faces[2] = "be 1";
        faces[3] = "be 2";
        faces[4] = "double beer";
        faces[5] = "double gatling";
    }

    //build an undead or alive die
    private void undeadDie(){
        faces = new String[6];
        faces[0] = "arrow";
        faces[1] = "dynamite";
        faces[2] = "be 1";
        faces[3] = "duel";
        faces[4] = "whiskey";
        faces[5] = "gatling";
    }

    //Multiple ways to know what type of die we are currently using
    public int getType(){ return type; }

    public void roll(){ currentFace = (int)(Math.random() * 6); }

    public String getFace(){ return faces[currentFace]; }

    public String toString(){
        if(getType() == 0)
            return "Vanilla Band Die";
        if(getType() == 1)
            return "Old Saloon Expansion Die";
        if(getType() == 2)
            return "Undead or Alive Expansion Die";
    }
}
