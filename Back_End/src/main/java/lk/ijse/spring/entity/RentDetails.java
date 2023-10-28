package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RentDetails {
    @Id
    private String rentId;
    @Id
    private String regNum;
    private String nic;
    private BigDecimal driverCost;
    private BigDecimal carCost;

    @ManyToOne
    @JoinColumn(name = "rentId", referencedColumnName = "rentId", insertable = false, updatable = false)
    private Rent rent;

    @ManyToOne
    @JoinColumn(name = "regNum",referencedColumnName = "regNum",insertable = false,updatable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "nic",referencedColumnName = "nic",insertable = false,updatable = false)
    private Driver driver;

}
