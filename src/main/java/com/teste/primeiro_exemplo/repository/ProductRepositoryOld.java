package com.teste.primeiro_exemplo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.teste.primeiro_exemplo.model.Product;
import com.teste.primeiro_exemplo.model.exception.ResourceNotFoundException;

import org.springframework.stereotype.Repository;


// o spring toma conta do repository
@Repository // faz o import do spring para usar inversão de dependência e injeção de controle
public class ProductRepositoryOld {
   private ArrayList<Product> products = new ArrayList<Product>();
   private Integer lastId = 0;

   /**
    * Método que retorna uma lista de produtos
    * @return Lista de produtos
    */
   public List<Product> getAll(){
    return products;
   }
   /**
    * Retorna um produto pelo Id
    * @param id do produto
    * @return produto caso seja encontrado
    */
   public Optional<Product> getById(Integer id){ // 
        return products
            .stream()
            .filter(product -> product.getId() == id )
            .findFirst();
        }

    /**
     * Adiciona produto na lista
     * @param product será adicionado
     * @return produto que foi adicionado na lista
     */
    public Product toAdd(Product product){
        lastId++;
        product.setId(lastId);
        products.add(product);
        return product; 
    }
    /**
     * Deleta produto pelo id
     * @param id do produto a ser deletado
     */

    public void deleteById(Integer id){
        products.removeIf(product -> product.getId() == id);
    }

    /**
     * Atualiza o produto na lista
     * @param product que será atualizado
     * @param id do produto
     * @return Retorna o mesmo produto após atualizar a lista
     */
    public Product toUpdate(Product product){

        // encontra o produto na lista
        Optional<Product> productFound = getById(product.getId());

        if (productFound.isEmpty()){
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        
        // deleta o produto antigo e o novo produto vai ter o msm id
        deleteById(product.getId()); 

        // adiciona o produto atualizado na lista
        products.add(product);

        return product;

    }
}
