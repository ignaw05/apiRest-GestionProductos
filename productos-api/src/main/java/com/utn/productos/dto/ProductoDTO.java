package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductoDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripcion no puede tener mas de 500 caracteres")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio minimo es 0.01")
    private Double precio;

    @NotNull(message = "El stock no puede estar vacio")
    @Min(value = 0, message = "El precio minimo es 0.01")
    private Integer stock;

    @NotNull(message = "La categoria no puede estar vacia")
    private Categoria categoria;

    public Producto toEntity(){
        Producto prod = new Producto();
        prod.setNombre(this.nombre);
        prod.setDescripcion(this.descripcion);
        prod.setPrecio(this.precio);
        prod.setStock(this.stock);
        prod.setCategoria(this.categoria);
        return prod;
    }

    public void updateEntity (Producto prod){
        prod.setNombre(this.nombre);
        prod.setDescripcion(this.descripcion);
        prod.setPrecio(this.precio);
        prod.setStock(this.stock);
        prod.setCategoria(this.categoria);
    }

}
