package com.diemdt.trainb3.service;

import com.diemdt.trainb3.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDTO createProduct (ProductDTO productDTO);
    ProductDTO updateProduct (Long id , ProductDTO productDTO);
    void deleteProduct ( Long id );
    ProductDTO getProduct ( Long id);
    Page<ProductDTO> getAllProducts(String search, Pageable pageable);
}
