package lk.ijse.spring.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class FreeMileage {
    private String front;
    private String back;
    private String side;
    private String interior;
}
