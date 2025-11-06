package com.utn.productos.controller;

import com.utn.productos.dto.ActualizarStockDTO;
import com.utn.productos.dto.ProductoDTO;
import com.utn.productos.dto.ProductoResponseDTO;
import com.utn.productos.model.Categoria;
import com.utn.productos.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController{
    private final ProductoService service;

    @Autowired
    public ProductoController(ProductoService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos(){
        List<ProductoResponseDTO> productos = service.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id){
        ProductoResponseDTO producto = service.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@PathVariable Categoria categoria){
        List<ProductoResponseDTO> productos = service.obtenerPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> agregarProducto(@Valid @RequestBody ProductoDTO productoDTO){
        ProductoResponseDTO producto = service.crearProducto(productoDTO);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long id,@Valid @RequestBody ProductoDTO productoDTO){
        ProductoResponseDTO producto = service.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(producto);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(@PathVariable Long id, @RequestBody ActualizarStockDTO actualizarStockDTO){
        ProductoResponseDTO producto = service.actualizarStock(id,actualizarStockDTO);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarProducto(@PathVariable Long id){
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

}
