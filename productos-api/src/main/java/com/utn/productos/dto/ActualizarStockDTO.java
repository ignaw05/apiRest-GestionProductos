package com.utn.productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Schema
public class ActualizarStockDTO {
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El minimo es 0")
    private Integer stock;
}
