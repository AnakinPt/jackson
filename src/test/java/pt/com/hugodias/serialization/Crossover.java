package pt.com.hugodias.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Crossover extends Car {
    private double towingCapacity;

    @JsonCreator
    public Crossover(@JsonProperty("type") String type, @JsonProperty("make") String make, @JsonProperty("model") String model, @JsonProperty("seatingCapacity") int seatingCapacity,
                     double topSpeed, double towingCapacity) {
        super(type, make, model, seatingCapacity, topSpeed);
        this.towingCapacity = towingCapacity;
    }

    public Crossover(String make, String model, int seatingCapacity,
                     double topSpeed, double towingCapacity) {
        this("crossover", make, model, seatingCapacity, topSpeed, towingCapacity);
    }

}