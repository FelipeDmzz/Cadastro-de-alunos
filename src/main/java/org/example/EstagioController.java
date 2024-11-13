package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estagios")
@CrossOrigin(origins = "*")
public class EstagioController {

    private final EstagioDAO estagioDAO;

    public EstagioController() {
        this.estagioDAO = new EstagioDAO();
    }

    @GetMapping
    public ResponseEntity<List<Estagio>> listarEstagios() {
        try {
            List<Estagio> estagios = estagioDAO.obterTodosEstagios();
            return ResponseEntity.ok(estagios);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> adicionarEstagio(@RequestBody Estagio estagio, @RequestParam int alunoMatricula) {
        try {
            estagioDAO.inserirEstagio(estagio, alunoMatricula);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estagio> buscarEstagioPorId(@PathVariable int id) {
        try {
            Estagio estagio = estagioDAO.obterEstagioPorId(id);
            if (estagio != null) {
                return ResponseEntity.ok(estagio);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEstagio(@PathVariable int id, @RequestBody Estagio estagio) {
        try {
            estagio.setId(id);
            estagioDAO.atualizarEstagio(estagio);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEstagio(@PathVariable int id) {
        try {
            estagioDAO.apagarEstagio(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}