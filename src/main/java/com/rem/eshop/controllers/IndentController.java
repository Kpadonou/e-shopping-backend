package com.rem.eshop.controllers;

import java.util.List;
import java.util.Optional;

import com.rem.eshop.models.Indent;
import com.rem.eshop.repositories.IndentRepository;

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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class IndentController {
    @Autowired
    private IndentRepository indentRepository;

    @GetMapping("/indents")
    public List<Indent> getAllIndents() {
        return indentRepository.findAll();
    }

    @GetMapping("/indents/{id}")
    public Indent getIndentByID(@PathVariable Long id) {
        Optional<Indent> optIndent = indentRepository.findById(id);
        if (optIndent.isPresent()) {
            return optIndent.get();
        } else {
            throw new RuntimeException("Indent not found with id " + id);
        }
    }

    @PostMapping("/indents")
    public Indent createIndent(@RequestBody Indent indent) {
        return indentRepository.save(indent);
    }

    @PutMapping("/indents/{id}")
    public Indent updateIndent(@PathVariable Long id, @RequestBody Indent indentUpdated) {
        return indentRepository.findById(id).map(indent -> {
            indent.setDatePlaced(indentUpdated.getDatePlaced());
            return indentRepository.save(indent);
        }).orElseThrow(() -> new RuntimeException("Indent not found with id " + id));
    }

    @DeleteMapping("/indents/{id}")
    public StringResponseFormat deleteIndent(@PathVariable Long id) {
        return indentRepository.findById(id).map(indent -> {
            indentRepository.delete(indent);
            return new StringResponseFormat("Delete Successfully!");
        }).orElseThrow(() -> new RuntimeException("Indent not found with id " + id));
    }

    @GetMapping("/user/{id}/indents")
    public List<Indent> getIdentsByUser(@PathVariable Long id) {
        return indentRepository.findByUserId(id);
    }
}
