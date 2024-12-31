package com.teste.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiro_exemplo.shared.ProductDTO;
import com.teste.primeiro_exemplo.model.Product;
import com.teste.primeiro_exemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiro_exemplo.repository.ProductRepository;
import org.modelmapper.ModelMapper;


@Service
public class ProductService { // vão delvover produtoDTO em vez de produto

    @Autowired
    private ProductRepository productRepository;

    /**
     * Método que retorna uma lista de produtos
     * @return Lista de produtos
     */
    public List<ProductDTO> getAll() {
            // retorna lista de produto model
            List<Product> products = productRepository.findAll();
            // varre lista de produtos
            return products.stream()
            .map(product -> new ModelMapper().map(product, ProductDTO.class)) // converte objeto produto em objeto produtoDTO
            .collect(Collectors.toList()); // pega o que tiver dentro de map e transforma em lista
    }

    /**
     * Retorna um produto pelo Id
     * @param id do produto
     * @return produto caso seja encontrado
     */
    public Optional<ProductDTO> getById(Integer id) {
        // obtendo optional de product pelo id
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: "+ id + " não encontrado");
        }
        // Converte optional product em produtoDTO get tira o product de dentro do optional
        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        // criando e retornando optional de productDTO
        return Optional.of(dto);
    }

    /**
     * Adiciona produto na lista
     * @param product será adicionado
     * @return produto que foi adicionado na lista
     */
    public ProductDTO toAdd(ProductDTO productDto) {
        // removendo o id para fazer cadastro
        productDto.setId(null); 

        // criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // converte o productodto em produto
        Product product = mapper.map(productDto, Product.class);

        // salvar o product do banco
        product = productRepository.save(product);

        productDto.setId(product.getId());

        return productDto;
    }

    /**
     * Deleta produto pelo id
     * @param id do produto a ser deletado
     */
    public void deleteById(Integer id) {
        // verifica se o produto existe
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o produto com o id "+ id + " ");


        }
        // deleta o produto pelo id
        productRepository.deleteById(id);
    }

    /**
     * Atualiza o produto na lista
     * @param product que será atualizado
     * @return Retorna o mesmo produto após atualizar a lista
     */
    public ProductDTO toUpdate(Integer id, ProductDTO productDto){
        // passar id para o produto dto Define o ID
        productDto.setId(id);
        //criar um obj de mapeamento
        ModelMapper mapper = new ModelMapper();
        // converter o produtoDTO em produto
        Product product = mapper.map(productDto, Product.class);

        // atualiza o product no bd
        productRepository.save(product);

        return productDto;
        
    }
}
