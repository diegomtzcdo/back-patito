package com.patito.cdod.controladores;

import com.patito.cdod.dto.CustomResponseEntity;
import com.patito.cdod.dto.ProductoDetallePayload;
import com.patito.cdod.dto.ProductoPayload;
import com.patito.cdod.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/todos")
    @Secured("ROLE_CLIENTE")
    public CustomResponseEntity<List<ProductoPayload>> getAll() {
        return productoService.getAllProducts();
    }

    @PostMapping("/crear")
    @Secured("ROLE_CLIENTE")
    @ResponseBody
    public CustomResponseEntity<ProductoPayload> crearProducto(@Valid @RequestBody ProductoDetallePayload producto) {
        return productoService.crearProducto(producto);
    }

    @GetMapping("/{hawa}")
    @Secured("ROLE_CLIENTE")
    @ResponseBody
    public CustomResponseEntity<ProductoDetallePayload> getDetalleProducto(@PathVariable("hawa") String hawa) {
        return productoService.getProductoDetalle(hawa);
    }

    @PostMapping("/detalles")
    @Secured("ROLE_CLIENTE")
    @ResponseBody
    public CustomResponseEntity<List<ProductoDetallePayload>> getDetalleProductoByHawas(@RequestBody List<String> hawas) {
        return productoService.getListProductoDetalle(hawas);
    }
}
