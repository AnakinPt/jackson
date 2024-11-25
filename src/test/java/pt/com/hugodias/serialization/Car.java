package pt.com.hugodias.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"model", "seatingCapacity"})
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Car extends Vehicle {
    @JsonProperty("seatingCapacity")
    private int seatingCapacity;

    @JsonProperty("topSpeed")
    private double topSpeed;


    public Car(@JsonProperty("type") String type, @JsonProperty("make") String make, @JsonProperty("model") String model, @JsonProperty("seatingCapacity") int seatingCapacity, @JsonProperty("topSpeed") double topSpeed) {
        super(type, make, model);
        this.seatingCapacity = seatingCapacity;
        this.topSpeed = topSpeed;
    }

}
