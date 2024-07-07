package com.patito.cdod.servicios;

import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.dto.ProductoDetallePayload;
import com.patito.cdod.dto.ProductoPayload;
import com.patito.cdod.entities.Producto;

import java.util.List;

public interface ProductoService {

    public Producto obtenerProductoPorHawa(String hawa);

    public CustomResponseEntity<List<ProductoPayload>> getAllProducts();

    public CustomResponseEntity<ProductoPayload> crearProducto(ProductoDetallePayload productoPayload);

    public CustomResponseEntity<ProductoDetallePayload> getProductoDetalle(String HAWA);

    public CustomResponseEntity<List<ProductoDetallePayload>> getListProductoDetalle(List<String> listHAWA);

    public ProductoPayload convertirAProductoPayload(Producto producto);
}
