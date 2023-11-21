package Factory;

import model.Flower;
import model.Rose;
import model.Tulip;

public class FlowerFactory {
    public Flower getFlower(String flowerType) {
        if ("ROSE".equalsIgnoreCase(flowerType)) {
            return new Rose();
        } else if ("TULIP".equalsIgnoreCase(flowerType)) {
            return new Tulip();
        }
        throw new IllegalArgumentException("Unknown flower type");
    }
}
