package com.diemdt.trainb3.mapper;

import com.diemdt.trainb3.dto.ProductDTO;
import com.diemdt.trainb3.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);
}
