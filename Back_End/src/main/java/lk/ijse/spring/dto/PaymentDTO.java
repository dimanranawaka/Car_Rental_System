package lk.ijse.spring.dto;

import lk.ijse.spring.entity.Rent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PaymentDTO {

    Integer paymentId;
    Rent rentId;
    String type;
    String description;
    LocalTime date;
    LocalTime time;
    BigDecimal total;
    BigDecimal cash;
    BigDecimal balance;
}
