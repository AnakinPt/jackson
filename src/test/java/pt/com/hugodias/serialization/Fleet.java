package pt.com.hugodias.serialization;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

@Getter
@Setter
@Builder

public class Fleet {
    @Singular
    private List<Vehicle> vehicles;
}
