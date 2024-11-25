package pt.com.hugodias.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @Type(value = Car.class, name = "car"),
        @Type(value = Truck.class, name = "truck"),
        @Type(value = Crossover.class, name = "crossover"),
        @Type(value = Sedan.class, name = "sedan")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public abstract class Vehicle {
    @EqualsAndHashCode.Include @JsonProperty("type") private String type;
    @EqualsAndHashCode.Include @JsonProperty("make") private String make;
    @EqualsAndHashCode.Include @JsonProperty("model") private String model;

    public Vehicle(String type, String make, @JsonProperty("model") String model) {
        this.type = type;
        this.make = make;
        this.model = model;
    }
}
