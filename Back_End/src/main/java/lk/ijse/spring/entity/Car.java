package lk.ijse.spring.entity;

import lk.ijse.spring.embeddable.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Car {
    @Id
    private String regNum;
    private String type;
    private String color;
    private String brand;
    private String availability;
    private String transmissionType;
    private String fuelType;
    private int passengers;
    @Embedded
    private Price price;
}
