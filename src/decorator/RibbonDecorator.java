package decorator;

import model.Flower;

public class RibbonDecorator extends FlowerDecorator {
    public RibbonDecorator(Flower decoratedFlower) {
        super(decoratedFlower);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        addRibbonDecoration();
    }

    private void addRibbonDecoration() {
        System.out.println("Decorated with a beautiful ribbon.");
    }
}
