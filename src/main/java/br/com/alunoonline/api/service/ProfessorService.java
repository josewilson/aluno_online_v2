package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository repository;

    public void create(Professor professor) {

        repository.save(professor);

    }

    public List<Professor> findAll() {

        return repository.findAll();

    }

    public Optional<Professor> findById(Long id) {

        return repository.findById(id);

    }

    public Professor findProfessorPorNomeAndEmail(String nome, String email) {
        return repository.findProfessorPorNomeAndEmail(nome, email);
    }

    public List<Professor> findAllAndOrderByNome() {
        return repository.findAllAndOrderByNome();
    }

    public void deleteById(Long id) {

        repository.deleteById(id);

    }

    public void update(Long id, Professor professor) {

        Optional<Professor> professorFromBD = findById(id);

        if (professorFromBD.isEmpty()) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PROFESSOR NÃO ENCONTRADO");

        }

        Professor professorUpdated = professorFromBD.get();
        professorUpdated.setNome(professor.getNome());
        professorUpdated.setEmail(professor.getEmail());
        professorUpdated.setRg(professor.getRg());
        professorUpdated.setCpf(professor.getCpf());
        professorUpdated.setAreaDoConhecimento(professor.getAreaDoConhecimento());
        professorUpdated.setDataDaContratacao(professor.getDataDaContratacao());

        repository.save(professorUpdated);

    }

}
