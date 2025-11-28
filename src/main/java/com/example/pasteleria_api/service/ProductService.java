package com.example.pasteleria_api.service;

import com.example.pasteleria_api.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<ProductDto> products = new ArrayList<>();
    private int nextId = 7;

    public ProductService() {
        products.add(new ProductDto(
                1,
                "Torta de Chocolate",
                "Deliciosa torta de chocolate con crema",
                15000.0,
                "Tortas",
                "https://res.cloudinary.com/riqra/image/upload/v1739382926/sellers/tortas-gaby/products/djenpatwiwbywtrzatej.jpg"
        ));

        products.add(new ProductDto(
                2,
                "Cheesecake",
                "Suave cheesecake de frambuesa",
                12000.0,
                "Tortas",
                "https://www.paulinacocina.net/wp-content/uploads/2025/01/receta-de-cheesecake-1742898428.jpg"
        ));

        products.add(new ProductDto(
                3,
                "Cupcakes Variados",
                "Pack de 6 cupcakes de diferentes sabores",
                8000.0,
                "Cupcakes",
                "https://www.mokacupcakes.com/cdn/shop/products/IMG_8116.jpg?v=1674069616"
        ));

        products.add(new ProductDto(
                4,
                "Torta Naranja",
                "Clásica torta de naranja casera",
                14000.0,
                "Tortas",
                "https://hazdeoros.com/familiar/wp-content/uploads/2021/02/torta-casera-de-naranja-con-cubierta-de-chocolate-una-receta-facil-sin-horno.png"
        ));

        products.add(new ProductDto(
                5,
                "Alfajores",
                "Pack de 12 alfajores artesanales",
                6000.0,
                "Galletas",
                "https://cdn11.bigcommerce.com/s-3stx4pub31/product_images/uploaded_images/alfajor-negro-relleno-de-dulce-de-leche.jpg"
        ));

        products.add(new ProductDto(
                6,
                "Brownies",
                "Brownies de chocolate con nueces",
                5000.0,
                "Postres",
                "https://www.justspices.es/media/recipe/brownie-chocolate.jpg"
        ));
    }

    public List<ProductDto> getAll() {
        return products;
    }

    public ProductDto getById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public ProductDto create(ProductDto dto) {
        dto.setId(nextId++);
        products.add(dto);
        return dto;
    }
    public ProductDto update(int id,ProductDto dto) {
        ProductDto existing = getById(id);
        if (existing == null) return null;

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setCategory(dto.getCategory());
        existing.setImageUrl(dto.getImageUrl());
        return existing;
    }
    public boolean delete(int id) {
        return products.removeIf(p -> p.getId() == id);
    }
}