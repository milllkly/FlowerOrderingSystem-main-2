package decorator;

import model.Flower;

public abstract class FlowerDecorator implements Flower {
    protected Flower decoratedFlower;

    public FlowerDecorator(Flower decoratedFlower) {
        this.decoratedFlower = decoratedFlower;
    }

    public void displayInfo() {
        decoratedFlower.displayInfo();
    }
}
