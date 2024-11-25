package pt.com.hugodias.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Sedan extends Car {
    @JsonCreator
    public Sedan(@JsonProperty("type") String type, @JsonProperty("make") String make, @JsonProperty("model") String model, @JsonProperty("seatingCapacity") int seatingCapacity, @JsonProperty("topSpeed") double topSpeed) {
        super(type, make, model, seatingCapacity, topSpeed);
    }

}

