package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {

    private final AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        try {
            List<Aluno> alunos = alunoDAO.listarTodos();
            return ResponseEntity.ok(alunos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> adicionarAluno(@RequestBody Aluno aluno) {
        try {
            alunoDAO.inserirAluno(aluno);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> buscarAlunoPorMatricula(@PathVariable int matricula) {
        try {
            Aluno aluno = alunoDAO.obterAlunoPorMatricula(matricula);
            if (aluno != null) {
                return ResponseEntity.ok(aluno);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Void> atualizarAluno(@PathVariable int matricula, @RequestBody Aluno aluno) {
        try {
            aluno.setMatricula(matricula);
            alunoDAO.atualizarAluno(aluno);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> removerAluno(@PathVariable int matricula) {
        try {
            alunoDAO.apagarAluno(matricula);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}