package tech.itpark.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalePositionDto {
    private long productId;
    private int price;
    private int count;

}
