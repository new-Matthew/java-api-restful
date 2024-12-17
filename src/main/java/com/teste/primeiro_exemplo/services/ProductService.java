package com.teste.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiro_exemplo.model.Product;
import com.teste.primeiro_exemplo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired // volto a ter controle sobre o ProductRepository
    private ProductRepository productRepository;

       /**
    * Método que retorna uma lista de produtos
    * @return Lista de produtos
    */
   public List<Product> getAll(){
    return productRepository.getAll();
   }
   /**
    * Retorna um produto pelo Id
    * @param id do produto
    * @return produto caso seja encontrado
    */
   public Optional<Product> getById(Integer id){ // 
        return productRepository.getById(id);
        }

    /**
     * Adiciona produto na lista
     * @param product será adicionado
     * @return produto que foi adicionado na lista
     */
    public Product toAdd(Product product){
       return productRepository.toAdd(product); 
    }
    /**
     * Deleta produto pelo id
     * @param id do produto a ser deletado
     */

    public void deleteById(Integer id){ // deletar não retorna nada
        productRepository.deleteById(id);
    }

    /**
     * Atualiza o produto na lista
     * @param product que será atualizado
     * @return Retorna o mesmo produto após atualizar a lista
     */
    public Product toUpdate(Integer id, Product product){ // 

        // 
        product.setId(id); // 


        return productRepository.toUpdate(product);

    }
}
