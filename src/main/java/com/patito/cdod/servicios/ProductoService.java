package com.patito.cdod.servicios;

import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.dto.ProductoDetallePayload;
import com.patito.cdod.dto.ProductoPayload;
import com.patito.cdod.entities.Producto;

import java.util.List;

public interface ProductoService {

    Producto obtenerProductoPorHawa(String hawa);

    CustomResponseEntity<List<ProductoPayload>> getAllProducts();

    CustomResponseEntity<ProductoPayload> crearProducto(ProductoDetallePayload productoPayload);

    CustomResponseEntity<ProductoDetallePayload> getProductoDetalle(String HAWA);

    CustomResponseEntity<List<ProductoDetallePayload>> getListProductoDetalle(List<String> listHAWA);

    ProductoPayload convertirAProductoPayload(Producto producto);
}
