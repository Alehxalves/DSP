package com.ufc.dspesist.lab9.controllers;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import com.ufc.dspesist.lab9.dao.TurmaJPADAO;
import com.ufc.dspesist.lab9.entity.Turma;
import com.ufc.dspesist.lab9.interfaces.TurmaDAO;

public class TurmaController {
    private TurmaDAO baseTurmas;

    public TurmaController() {
        baseTurmas = new TurmaJPADAO();
    }

    public void saveTurma(Turma turma) {
        try {
            if (turma.getId() != null) { // UPDATE ALUNO
                baseTurmas.save(turma);
                JOptionPane.showMessageDialog(null, "Turma " + turma.getDisciplina() + " Atualizado.");
            } else { // NEW ALUNO
                baseTurmas.save(turma);
                JOptionPane.showMessageDialog(null, "Turma " + turma.getDisciplina() + " Salva.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void deleteTurmaById(int id) throws Exception {
        Turma turma = findById(id);
        baseTurmas.delete(turma);
        JOptionPane.showMessageDialog(null, "Turma deletada.");
    }

    public Turma findById(int id) throws Exception {
        Turma turma = new Turma();
        turma = baseTurmas.findById(id);

        if (Objects.nonNull(turma))
            return turma;
        else
            throw new Exception("Turma não encontrada");
    }

    public List<Turma> findAll() {
        return baseTurmas.findAll();
    }
}
