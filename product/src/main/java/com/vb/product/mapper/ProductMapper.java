package com.gorillalogic.product.mapper;

import com.gorillalogic.product.dto.ProductDto;
import com.gorillalogic.product.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto productToProductDto(Product product);
}
