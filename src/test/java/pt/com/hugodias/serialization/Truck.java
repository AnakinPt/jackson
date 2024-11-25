package pt.com.hugodias.serialization;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Truck extends Vehicle{
    private double payloadCapacity;

    public Truck(String type, String make, String model, double payloadCapacity) {
        super(type, make, model);
        this.payloadCapacity = payloadCapacity;
    }

}
