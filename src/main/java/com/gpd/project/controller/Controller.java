package com.gpd.project.controller;

import com.gpd.project.Repositorys.ProdutoRepository;
import com.gpd.project.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> getProduto() {
        try{
            List<Produto> produtos = produtoRepository.findAll();
            return ResponseEntity.ok(produtos);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping ("/produto")
    public ResponseEntity<String> postProduto(@RequestBody Produto produto) {
        try{
            produtoRepository.save(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar produto");
        }
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
        try{
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar produto");
        }
    }

}