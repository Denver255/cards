import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Collections;


public class War extends CardGame {
    static boolean showp1play=false;
    Card p1card;
    Card p2card;
    Card p1warcard;
    Card p2warcard;
    Card curcard;
    Card curcard2;
    int index=0;
    int yo=0;
    

    public War() {
        initializeGame();
        dealCards(6);
    }

    @Override
    protected void createDeck() {
        // Create a standard deck of cards (for simplicity, using numbers and suits)
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Card(value, suit));
            }
        }
    }

    @Override
    protected void dealCards(int numCards){
        Collections.shuffle(deck);
        for (int i = 0; i < 26; i++) {
            playerOneHand.addCard(deck.remove(0));
            Card card = deck.remove(0);
            card.setTurned(true);
            playerTwoHand.addCard(card);
        }
        nukecounter=-1;
        // position cards
        playerOneHand.positionCards(50, 450, 80, 120, 20);
        playerTwoHand.positionCards(50, 50, 80, 120, 20);
    }

    @Override
    public void handleDrawButtonClick(int mouseX, int mouseY) {
        if (drawButton.isClicked(mouseX, mouseY) && playerOneTurn) {
            //cardContestants();
            //drawCard(playerOneHand);
            // Switch turns after drawing
            curcard=playerOneHand.getCard(0);
            curcard.setSelected(true,selectedCardRaiseAmount);
            switchTurns();
             if(winner==true || loser==true){
            Reset();
        }
        }
    }

    void cardContestants(){
        index=0;
        p1card=playerOneHand.getCard(0);
        p2card=playerTwoHand.getCard(0);
        // if(nukecounter<=3 && nukecounter>0){
        //     playerOneHand.addCard(p2card);
        //     playerTwoHand.removeCard(p2card);
        //     playerOneHand.addCard(p1card);
        //     playerOneHand.removeCard(p1card);
        //     curcard.selected=false;
        //     curcard2.selected=false;
        //     return;
        // }
        if(p1card.rvalue>p2card.rvalue){
            playerOneHand.addCard(p2card);
            playerTwoHand.removeCard(p2card);
            playerOneHand.removeCard(p1card);
            playerOneHand.addCard(p1card);
            curcard.selected=false;
            curcard2.selected=false;
            // nuke++;
        }
         if(p2card.rvalue>p1card.rvalue && backup<10){
            playerTwoHand.addCard(p1card);
            playerOneHand.removeCard(p1card);
            playerTwoHand.removeCard(p2card);
            playerTwoHand.addCard(p2card);
            curcard.turned=true;
            curcard.selected=false;
            curcard2.selected=false;
            curcard2.turned=true;
        }
         if(p1card.rvalue==p2card.rvalue){
            Battle();
        }
        if(p2card.rvalue>p1card.rvalue && backup>=10){
            playerOneHand.addCard(p2card);
            playerTwoHand.removeCard(p2card);
            playerOneHand.removeCard(p1card);
            playerOneHand.addCard(p1card);
            curcard.selected=false;
            curcard2.selected=false;
            backup=0;
        }
    }

    void Battle(){
        index=index+4;
        p1warcard=playerOneHand.getCard(index);
        p2warcard=playerTwoHand.getCard(index);

          for(int i=0; i<=index; i++){
                if(playerOneHand.getCard(i)==null){
                    for(int z=0;z<=playerOneHand.getSize();z++){
                        playerTwoHand.addCard(playerOneHand.getCard(z));
                        playerOneHand.getCard(z).turned=true;
                        playerOneHand.removeCard(playerOneHand.getCard(z));
                        curcard.selected=false;
                        curcard.turned=true;
                        playerTwoHand.getCard(0).turned=true;
                        playerTwoHand.getCard(0).selected=false;
                        playerTwoHand.addCard(playerTwoHand.getCard(z));
                        playerTwoHand.removeCard(playerTwoHand.getCard(z));
                        yo=100;
                    }
                    }
          
                if(playerTwoHand.getCard(i)==null){
                    for(int z=0;z<=playerTwoHand.getSize();z++){
                        playerOneHand.addCard(playerTwoHand.getCard(z));
                        playerTwoHand.getCard(z).turned=false;
                        playerTwoHand.removeCard(playerTwoHand.getCard(z));
                        curcard2.selected=false;
                        curcard2.turned=false;
                        playerOneHand.getCard(0).selected=false;
                        playerOneHand.addCard(playerOneHand.getCard(z));
                        playerOneHand.removeCard(playerOneHand.getCard(z));
                        yo=100;
                    }
                }
            }
            if(yo>=100){
                return;
            }
         if(p1warcard.rvalue>p2warcard.rvalue){
            for(int i=0; i<=index; i++){
                if(playerOneHand.getCard(i)==null){
                    for(int z=0;z<=playerOneHand.getSize();z++){
                        playerTwoHand.addCard(playerOneHand.getCard(z));
                        playerOneHand.getCard(z).turned=true;
                        playerOneHand.removeCard(playerOneHand.getCard(z));
                        curcard.selected=false;
                        curcard.turned=true;
                        playerTwoHand.getCard(0).turned=true;
                        playerTwoHand.getCard(0).selected=false;
                        playerTwoHand.addCard(playerTwoHand.getCard(z));
                        playerTwoHand.removeCard(playerTwoHand.getCard(z));
                        i=100;
                    }
                    }
                if(playerTwoHand.getCard(i)==null){
                    for(int z=0;z<=playerTwoHand.getSize();z++){
                        playerOneHand.addCard(playerTwoHand.getCard(z));
                        playerTwoHand.getCard(z).turned=false;
                        playerTwoHand.removeCard(playerTwoHand.getCard(z));
                        curcard2.selected=false;
                        curcard2.turned=false;
                        playerOneHand.getCard(0).selected=false;
                        playerOneHand.addCard(playerOneHand.getCard(z));
                        playerOneHand.removeCard(playerOneHand.getCard(z));
                        i=100;
                    }
                }
                if(i>=100){
                    break;
                }
                playerOneHand.addCard(playerTwoHand.getCard(i));
                playerTwoHand.getCard(i).turned=false;
                playerTwoHand.removeCard(playerTwoHand.getCard(i));
                curcard2.selected=false;
                curcard2.turned=false;
                playerOneHand.getCard(0).selected=false;
                playerOneHand.addCard(playerOneHand.getCard(i));
                playerOneHand.removeCard(playerOneHand.getCard(i));
                nuke++;
            }
        }
            if(p2warcard.rvalue>p1warcard.rvalue){
            for(int i=0; i<=index; i++){
                if(playerOneHand.getCard(i)==null){
                    for(int z=0;z<=playerOneHand.getSize();z++){
                        playerTwoHand.addCard(playerOneHand.getCard(z));
                        playerOneHand.getCard(z).turned=true;
                        playerOneHand.removeCard(playerOneHand.getCard(z));
                        curcard.selected=false;
                        curcard.turned=true;
                        playerTwoHand.getCard(0).turned=true;
                        playerTwoHand.getCard(0).selected=false;
                        playerTwoHand.addCard(playerTwoHand.getCard(z));
                        playerTwoHand.removeCard(playerTwoHand.getCard(z));
                        i=100;
                    }
                    }
                if(playerTwoHand.getCard(i)==null){
                    for(int z=0;z<=playerTwoHand.getSize();z++){
                        playerOneHand.addCard(playerTwoHand.getCard(z));
                        playerTwoHand.getCard(z).turned=false;
                        playerTwoHand.removeCard(playerTwoHand.getCard(z));
                        curcard2.selected=false;
                        curcard2.turned=false;
                        playerOneHand.getCard(0).selected=false;
                        playerOneHand.addCard(playerOneHand.getCard(z));
                        playerOneHand.removeCard(playerOneHand.getCard(z));
                        i=100;
                    }
                }
                if(i>=100){
                    break;
                }
              playerTwoHand.addCard(playerOneHand.getCard(i));
                playerOneHand.getCard(i).turned=true;
              playerOneHand.removeCard(playerOneHand.getCard(i));
              curcard.selected=false;
              curcard.turned=true;
              playerTwoHand.getCard(0).turned=true;
              playerTwoHand.getCard(0).selected=false;
               playerTwoHand.addCard(playerTwoHand.getCard(i));
              playerTwoHand.removeCard(playerTwoHand.getCard(i));
               
            }
        }
         if(p1warcard.rvalue==p2warcard.rvalue){
            //Weird error
            Battle();
        }
    }
    @Override
    public void showP2Card(){
        curcard2=playerTwoHand.getCard(0);
        curcard2.turned=false;
        curcard2.setSelected(true,selectedCardRaiseAmount);
    }

    public void Win(){
        if(playerOneHand.getSize()==0){
            loser=true;
        }else{
            winner=true;
        }
    }
    public void Reset(){
        initializeGame();
        dealCards(6);
    }

    @Override
     public void handleComputerTurn() {
        cardContestants();
        backup++;
        //nukecounter--;
        // if(nukecounter==0){
        //     nuke=0;
        //     nukecounter=-1;
        // }
        if(playerOneHand.getSize()>0 && playerTwoHand.getSize()>0){
        switchTurns();
        } else{
            Win();
        }
    }

   
    


}
