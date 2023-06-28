package com.crud.services.impl;

import com.crud.dtos.request.PurchaseRequest;
import com.crud.dtos.response.ProductResponse;
import com.crud.dtos.response.PurchaseResponse;
import com.crud.entities.ProductEntity;
import com.crud.entities.PurchaseEntity;
import com.crud.exceptions.ResourceNotFoundException;
import com.crud.repositories.ProductRepository;
import com.crud.repositories.PurchaseRepository;
import com.crud.security.entity.Usuario;
import com.crud.security.service.UsuarioService;
import com.crud.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final UsuarioService usuarioService;


    @Override
    public List<PurchaseResponse> getCustomerPurchases() {
        // Obtener el nombre de usuario del usuario autenticado desde el contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();

        // Obtener las compras asociadas al nombre de usuario desde el repositorio
        List<PurchaseEntity> purchases = purchaseRepository.findByUsuario_NombreUsuario(nombreUsuario);

        if (purchases.isEmpty()) {
            throw new ResourceNotFoundException("El usuario no tiene compras registradas");
        }

        // Mapear las compras a PurchaseResponse
        List<PurchaseResponse> purchaseResponses = new ArrayList<>();
        for (PurchaseEntity purchaseEntity : purchases) {
            PurchaseResponse purchaseResponse = new PurchaseResponse();
            purchaseResponse.setId(purchaseEntity.getId());
            purchaseResponse.setCantidad(purchaseEntity.getCantidad());
            purchaseResponse.setPrecio(purchaseEntity.getPrecio());
            purchaseResponse.setTotal(purchaseEntity.getTotal());
            //purchaseResponse.setFecha(purchaseEntity.getFecha());

            List<ProductResponse> productResponses = new ArrayList<>();
            for (ProductEntity productEntity : purchaseEntity.getProducts()) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setId(productEntity.getId());
                productResponse.setName(productEntity.getName());
                productResponse.setPrice(productEntity.getPrice());
                // Otros atributos del producto que desees incluir en la respuesta

                productResponses.add(productResponse);
            }

            purchaseResponse.setProducts(productResponses);
            purchaseResponses.add(purchaseResponse);
        }
        return purchaseResponses;
    }

    @Override
    @Transactional
    public void purchaseProduct(PurchaseRequest purchaseRequest) {
        // Crear una instancia de PurchaseEntity utilizando los datos de PurchaseRequest
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setCantidad(purchaseRequest.getCantidad());
        purchase.setPrecio(purchaseRequest.getPrecio());
        // Calcular el total multiplicando la cantidad por el precio
        double total = purchaseRequest.getCantidad() * purchaseRequest.getPrecio();
        purchase.setTotal(total);


        // Obtener el nombre de usuario del usuario autenticado desde el contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();

        // Obtener el objeto Usuario utilizando el nombre de usuario
        Optional<Usuario> optionalUsuario = usuarioService.getByNombreUsuario(nombreUsuario);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            // Establecer el usuario asociado a la compra utilizando el objeto Usuario completo
            purchase.setUsuario(usuario);

            // Obtener el producto asociado a la compra utilizando el ID proporcionado
            Optional<ProductEntity> optionalProduct = productRepository.findById(purchaseRequest.getProductId());
            if (optionalProduct.isPresent()) {
                ProductEntity product = optionalProduct.get();

                // Agregar el producto a la lista de productos asociados a la compra
                purchase.getProducts().add(product);

                // Guardar la compra en la base de datos
                purchaseRepository.save(purchase);
            } else {
                throw new ResourceNotFoundException("El producto no existe");
            }
        }
    }
}