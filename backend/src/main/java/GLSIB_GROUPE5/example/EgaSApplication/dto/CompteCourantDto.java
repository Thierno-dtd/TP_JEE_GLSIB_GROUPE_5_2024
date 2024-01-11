package GLSIB_GROUPE5.example.EgaSApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Builder
@Data
public class CompteCourantDto extends CompteDto{
    private BigDecimal decouvertAutorise;

}
