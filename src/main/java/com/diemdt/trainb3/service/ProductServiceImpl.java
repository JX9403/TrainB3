package com.diemdt.trainb3.service;

import com.diemdt.trainb3.dto.ProductDTO;
import com.diemdt.trainb3.mapper.ProductMapper;
import com.diemdt.trainb3.model.Product;
import com.diemdt.trainb3.repository.ProductRepository;
import com.diemdt.trainb3.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper ;
    private final ProductRepository productRepository ;
    @Override
    @Transactional
    public ProductDTO createProduct (ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);


        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }
    @Override
    @Transactional
    public ProductDTO updateProduct (Long id , ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        existingProduct.setName(productDTO.getName());
        existingProduct.setCategory(productDTO.getCategory());
        existingProduct.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }
    @Override
    @Transactional
    public  void deleteProduct ( Long id ){
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
    @Override
    public ProductDTO getProduct ( Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        return  productMapper.toDTO(product);
    }
    @Override
    public Page<ProductDTO> getAllProducts(String search , Pageable pageable) {
        if (search != null && !search.isEmpty()) {
            return productRepository.findByNameContainingIgnoreCase(search, pageable)
                    .map(productMapper::toDTO);
        } else {
            return productRepository.findAll(pageable)
                    .map(productMapper::toDTO);
        }
    }



}
