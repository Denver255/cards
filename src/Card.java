import processing.core.PImage;
import processing.core.PApplet;

public class Card extends ClickableRectangle {
    String value;
    String suit;
    PImage img;
    boolean turned = false;
    private int clickableWidth = 30; // Width of the left sliver that is clickable
    boolean selected = false;
    private int baseY;
    private boolean hasBaseY = false;
    int rvalue;

    Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
        if(value=="2"){
            rvalue=2;
        }
         if(value=="3"){
            rvalue=3;
        }
         if(value=="4"){
            rvalue=4;
        }
         if(value=="5"){
            rvalue=5;
        }
         if(value=="6"){
            rvalue=6;
        }
         if(value=="7"){
            rvalue=7;
        }
         if(value=="8"){
            rvalue=8;
        }
         if(value=="9"){
            rvalue=9;
        }
         if(value=="10"){
            rvalue=10;
        }
         if(value=="J"){
            rvalue=11;
        }
         if(value=="Q"){
            rvalue=12;
        }
         if(value=="K"){
            rvalue=13;
        }
         if(value=="A"){
            rvalue=14;
        }
    }

    Card(String value, String suit, PImage img) {
        this.value = value;
        this.suit = suit;
        this.img = img;
    }

    public void setTurned(boolean turned) {
        this.turned = turned;
    }

    public void setClickableWidth(int width) {
        this.clickableWidth = width;
    }

    public void setSelected(boolean selected, int raiseAmount) {
        if (selected && !this.selected) {
            baseY = y;
            hasBaseY = true;
            y = baseY - raiseAmount;
        } else if (!selected && this.selected && hasBaseY) {
            y = baseY;
        }
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean isClicked(int mouseX, int mouseY) {
        // Only the left sliver of the card is clickable
        return mouseX >= x && mouseX <= x + clickableWidth &&
                mouseY >= y && mouseY <= y + height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawFront(PApplet sketch) {
        if (img != null) {
            sketch.image(img, x, y, width, height);
        } else {
            sketch.fill(255);
            sketch.rect(x, y, width, height);
            sketch.fill(0);
            sketch.text(value, x + 10, y + 10);
        }
    }

    public void draw(PApplet sketch) {
        if (turned) {
            sketch.fill(150);
            sketch.rect(x, y, width, height);
            return;
        }
        if (isSelected()) {
            sketch.stroke(0);
            sketch.strokeWeight(4);
        } else {
            sketch.stroke(0);
        }
        drawFront(sketch);
        sketch.strokeWeight(1);
    }
}
