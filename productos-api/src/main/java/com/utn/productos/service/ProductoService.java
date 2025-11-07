package com.utn.productos.service;

import com.utn.productos.dto.ActualizarStockDTO;
import com.utn.productos.dto.ProductoDTO;
import com.utn.productos.dto.ProductoResponseDTO;
import com.utn.productos.exception.ProductoNotFoundException;
import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import com.utn.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {
    private final  ProductoRepository repo;

    @Autowired
    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public ProductoResponseDTO crearProducto(ProductoDTO productoDTO){
        Producto prod = productoDTO.toEntity();
        repo.save(prod);
        return ProductoResponseDTO.fromEntity(prod);
    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerPorId(Long id){
        Producto prod = repo.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("No se encontro el producto con id:"+id));
        return ProductoResponseDTO.fromEntity(prod);
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerTodos(){
        List<ProductoResponseDTO> productos = repo.findAll().stream().
                map(ProductoResponseDTO::fromEntity).toList();
        return productos;
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerPorCategoria(Categoria categoria){
        List<ProductoResponseDTO> productos = repo.findByCategoria(categoria).stream()
                .map(ProductoResponseDTO::fromEntity).toList();
        return productos;
    }

    public ProductoResponseDTO actualizarProducto(Long id, ProductoDTO productoDTO){
        Producto prod = repo.findById(id).orElseThrow(() -> new ProductoNotFoundException("No se encontro el producto con id:"+id));
        productoDTO.updateEntity(prod);
        Producto prodAct = repo.save(prod);
        return ProductoResponseDTO.fromEntity(prodAct);
    }

    public ProductoResponseDTO actualizarStock(Long id, ActualizarStockDTO nuevoStock){
        Producto prod = repo.findById(id).orElseThrow(() -> new ProductoNotFoundException("No se encontro el producto con id:"+id));
        prod.setStock(nuevoStock.getStock());
        Producto prodAct = repo.save(prod);
        return ProductoResponseDTO.fromEntity(prodAct);
    }

    public void eliminarProducto(Long id) {
        repo.deleteById(id);
    }

}
