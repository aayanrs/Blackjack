

import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {

        Scanner next = new Scanner(System.in);
        P1Random rng = new P1Random();

        //p = player d = dealer

        int numGame = 0;
        int totalNumGame = 0;
        int pCardNum = 0;
        int pHandVal = 0;
        int pHandValPrevious = 0;
        int dHandVal = 0;
        double pWins = 0;
        int dWins = 0;
        int numTies = 0;
        double winRatio = 0;
        int pChoice = 0;

        while (pChoice != 4){
            if(pCardNum == 0){ //this is here to restart my game, because at every ending i have pcardnum being set to 0
                System.out.println("START GAME #" + (numGame + 1) + "\n");
                numGame += 1;

                pCardNum = rng.nextInt(13) + 1; //random generated from p1Random to be my cardnum
                whatCard(pCardNum); //method defined underneath main, lists all the cards from Ace to King in if-elses
                if (pCardNum >= 1 && pCardNum <= 10){
                    pHandVal = pCardNum ;     // Ace to 10 is all 10, but J Q K are recognized as 11,12,13 but value = 10
                }
                else{
                    pHandVal = 10;
                }
                pHandValPrevious = pHandVal; // since first turn, they equal
                System.out.println("Your hand is: " + pHandVal + "\n");
            }

            System.out.println("1. Get another card"); //set up menu
            System.out.println("2. Hold hand");
            System.out.println("3. Print statistics");
            System.out.println("4. Exit" + "\n");
            System.out.print("Choose an option: ");
            System.out.println("");
            pChoice = next.nextInt(); //scanner used for menu

            switch(pChoice) { //where i utilize the input given to handle each menu choice
                // switch also used over if else for conciseness
                case 1:
                    pCardNum = rng.nextInt(13) + 1;
                    whatCard(pCardNum); //self made method called again to define cards
                    if(pCardNum >= 1 && pCardNum <=10){
                        pHandVal = pHandValPrevious + pCardNum; //creates hand val if not a face card
                    }
                    else{
                        pHandVal = pHandValPrevious + 10; //adds 10 for facecards
                    }
                    pHandValPrevious = pHandVal;
                    System.out.println("Your hand is: " + pHandVal + "\n");
                    if (pHandVal == 21){
                        System.out.println("BLACKJACK! You win!" + "\n"); //quits game when 21 reached by player immediately
                        pWins += 1; //stores this value for use in statistic option (case3)
                        pCardNum = 0; //first example of reset to restart
                    }
                    else if(pHandVal > 21){
                        System.out.println("You exceeded 21! You lose." + "\n"); //terminates when 21 is exceeded
                        dWins += 1; //increments dealer win for case3
                        pCardNum = 0; //reset
                    }

                    break;
                case 2:
                    dHandVal = rng.nextInt(11) + 16; //only have to create dealers hand in this case
                    //which is why the dealers hand is only being created in this case
                    System.out.println("Dealer's hand: " + dHandVal);
                    System.out.println("Your hand is: " + pHandVal);
                    if(dHandVal > pHandVal && dHandVal <= 21){ // if dealer is 21 or closer to it then us he wins
                        System.out.println("Dealer wins!");
                        dWins += 1;
                        pCardNum = 0; //resets at the end of each if
                        break;
                    }
                    else if(dHandVal < pHandVal){ //if we are closer to 21
                        System.out.println("You win!");
                        pWins += 1;
                        pCardNum = 0;
                    }
                    else if(dHandVal > 21){ //if Dealer exceeds
                        System.out.println("You win!");
                        pWins += 1;
                        pCardNum = 0;
                    }
                    else{ //if nothing else, then it must mean its a tie and we both have same card val
                        System.out.println("It's a tie! No one wins!");
                        numTies += 1;
                        pCardNum = 0;
                    }
                    totalNumGame += 1;
                    break;
                case 3:
                    System.out.println("Number of Player wins: " + (int)pWins); //simply print stored values
                    System.out.println("Number of Dealer wins: " + dWins);
                    System.out.println("Number of tie games: " + numTies);
                    int sumGames = (int)pWins + dWins + numTies; //for some reason totalNumGame didnt work so I created a new int
                    // and it worked fine
                    System.out.println("Total # of games played is: " + sumGames);
                    if(sumGames == 0){
                        System.out.println("");
                    }
                    if(sumGames > 0){ //provides your winratio if you have at least 1 game played, otherwise divide by 0
                        winRatio = (pWins / (double)sumGames) * 100;
                        System.out.println("Percentage of Player wins: " + winRatio + "%" + "\n");
                    }
                    break;
                case 4: //exit
                    break;
                default:
                    System.out.println("Invalid input!");
                    System.out.println("Please enter an integer value between 1 and 4.");

            }

        }

    }
    public static void whatCard(int pCardNum){ //i didnt want to type this twice so i experimented with methods
        // and wrote this so i could reduce redundancy, simple if else statements stating each card name for case1
        if(pCardNum == 1){
            System.out.println("Your card is a ACE!");
        }
        else if(pCardNum >= 2 && pCardNum <= 10){
            System.out.println("Your card is a " + pCardNum + "!");
        }
        else if(pCardNum == 11){
            System.out.println("Your card is a JACK!");
        }
        else if(pCardNum == 12){
            System.out.println("Your card is a QUEEN!");
        }
        else if(pCardNum == 13){
            System.out.println("Your card is a KING!");
        }
    }

}