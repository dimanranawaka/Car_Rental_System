package lk.ijse.spring.dto;

import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.Rent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RentDetailsDTO {
    private String rentId;
    private String regNum;
    private String nic;
    private BigDecimal driverCost;
    private BigDecimal carCost;
    private Rent rent;
    private Car car;
    private Driver driver;
}
