package com.epam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PositiveOrZero;
import java.time.ZonedDateTime;

@Data
public class LotDto {

    private Integer id;
    private ProductDto productId;
    private ClientDto bidderId;
    @DecimalMin("0.1")
    private Double bestPrice;
    @PositiveOrZero
    private Double startPrice;
    @PositiveOrZero
    private Double bidMin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+4")
    private ZonedDateTime stopDate;

}
