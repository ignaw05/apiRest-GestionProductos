package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;

public record ProductoResponseDTO (
    Long id,
    String nombre,
    String descripcion,
    Double precio,
    Integer stock,
    Categoria categoria
){
    public static ProductoResponseDTO fromEntity(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria()
        );
    }
}
