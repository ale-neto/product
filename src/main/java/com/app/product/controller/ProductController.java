package com.app.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.product.model.Product;
import com.app.product.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	
	@GetMapping("/products")
	public List<Product> listaProdutos(){
		return productRepository.findAll();
	}

	@GetMapping("/products/{id}")
	public Product listaProdutosUnico(@PathVariable(value="id")long id){
		return productRepository.findById(id);
	}
	
	@PostMapping("/products")
	public Product salvaProduto(@Valid @RequestBody Product product){
		return  productRepository.save(product);
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduto(@PathVariable(value="id")long id){
		Product product =  productRepository.findById(id);
		productRepository.delete(product);
	}
	
	@PutMapping("/products/{id}")
	public Product atualizaProduto(@PathVariable(value="id")long id, @Valid @RequestBody Product productDetails){
		Product product =  productRepository.findById(id);
		product.setName(productDetails.getName());
		product.setQtd(productDetails.getQtd());
		product.setVlr(productDetails.getVlr());
		return productRepository.save(product);
	}
}
