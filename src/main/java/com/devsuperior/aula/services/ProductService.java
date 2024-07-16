package com.devsuperior.aula.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.dto.ProductDTO;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
		
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product(); 
		
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		
		for(CategoryDTO catDto : dto.getCategories()) {
			
			//Category category = new Category();
			//category.setId(catDto.getId());
			
			Category category = categoryRepository.getReferenceById(catDto.getId());
			entity.getCategories().add(category);
		};
		
		entity = productRepository.save(entity);
		
		return new ProductDTO(entity);
	}
	
	

}