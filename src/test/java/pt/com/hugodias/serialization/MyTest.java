package pt.com.hugodias.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyTest {

    @Test
    void test_Serialize() throws JsonProcessingException, JSONException {
        String expectedJson = """
                     [
                          {
                            "type": "sedan",
                            "make": "Mercedes-Benz"
                          },
                          {
                              "type": "crossover",
                              "make": "BMW",
                              "towingCapacity": 6000.0
                          }
                      ]
                """;
        ObjectMapper mapper = new ObjectMapper();

        Sedan sedan = new Sedan("sedan", "Mercedes-Benz", "S500", 5, 250.0);
        Crossover crossover = new Crossover("BMW", "X6", 5, 250.0, 6000.0);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(sedan);
        vehicles.add(crossover);

        String jsonDataString = mapper.writeValueAsString(vehicles);
        JSONAssert.assertEquals(expectedJson, jsonDataString, JSONCompareMode.STRICT);

    }

    @Test
    void test_deserialize() throws JsonProcessingException {
        String serializedString = """
                [
                {
                  "type": "sedan",
                  "make": "Mercedes-Benz",
                    "model": "S500",
                    "seatingCapacity": 5,
                    "topSpeed": 250.0
                },
                {
                    "type": "crossover",
                  "make": "BMW",
                    "model": "X6",
                    "seatingCapacity": 5,
                    "topSpeed": 250.0,
                    "towingCapacity": 6000.0
                }
                ]
                """;
        ObjectMapper mapper = new ObjectMapper();
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("pt.com.hugodias.serialization")
                .allowIfSubType("java.util.ArrayList")
                .build();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        Sedan sedan = new Sedan("sedan", "Mercedes-Benz", "S500", 5, 250.0);
        Crossover crossover = new Crossover("BMW", "X6", 5, 250.0, 6000.0);
        List<Vehicle> expectedVehicles = new ArrayList<>();
        expectedVehicles.add(sedan);
        expectedVehicles.add(crossover);

        TypeReference<List<Vehicle>> typeRef = new TypeReference<>() {
        };

        List<Vehicle> vehicles = mapper.readValue(serializedString, typeRef);

        assertThat(vehicles).hasSameElementsAs(expectedVehicles);

    }

    @Test
    void test_singleVehicle() throws JsonProcessingException {
        String serializedString = """
                {
                  "topSpeed": 250.0,
                  "type": "sedan",
                  "model": "S500",
                  "make": "Mercedes-Benz",
                  "seatingCapacity": 5
                }
                """;

        ObjectMapper mapper = new ObjectMapper();

        Sedan sedan = new Sedan("sedan", "Mercedes-Benz", "S500", 5, 250.0);

        Vehicle vehicle = mapper.readValue(serializedString, Vehicle.class);

        assertThat(vehicle).isEqualTo(sedan);
    }

    @Test
    void test_singleCar() throws JsonProcessingException {
        String serializedString = """
                {
                  "type": "SingleCar",
                  "make": "Mercedes-Benz",
                    "model": "S500",
                    "seatingCapacity": 5,
                    "topSpeed": 250.0
                }
                """;
        ObjectMapper mapper = JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                .build();

        SingleCar singleCar = new SingleCar("SingleCar", "Mercedes-Benz", "S500", 5, 250.0);

        SingleCar vehicle = mapper.readValue(serializedString, SingleCar.class);

        assertThat(vehicle).isEqualTo(singleCar);
    }


}
