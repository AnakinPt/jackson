package pt.com.hugodias.serialization;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class SingleCar {
    @EqualsAndHashCode.Include private String type;
    @EqualsAndHashCode.Include private String make;
    @EqualsAndHashCode.Include private String model;
    private int seatingCapacity;
    private double topSpeed;
}
