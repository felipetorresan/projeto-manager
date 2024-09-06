package com.example.demo.controller;

import com.example.demo.model.Projeto;
import com.example.demo.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService  projetoService;

    @PostMapping
    public ResponseEntity<Projeto > criarProjeto(@RequestBody Projeto projeto) {
        Projeto novoProjeto = projetoService.criarProjeto(projeto);
        return ResponseEntity.ok(novoProjeto);
    }

    @GetMapping
    public List<Projeto> listarProjetos() {
        return projetoService.listarProjetos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjetoPorId(@PathVariable Long id) {
        return projetoService.buscarProjetoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id,
                                                    @RequestBody Projeto projetoAtualizado) {
        try {
            Projeto projeto = projetoService.atualizarProjeto(id, projetoAtualizado);
            return ResponseEntity.ok(projeto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }
}

