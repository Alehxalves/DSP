package com.ufc.dspesist.lab9.dao;

import com.ufc.dspesist.lab9.entity.Turma;
import com.ufc.dspesist.lab9.interfaces.TurmaDAO;

public class TurmaJPADAO extends GenericJPADAO<Turma> implements TurmaDAO {

    public TurmaJPADAO() {
        this.persistentClass = Turma.class;
    }
}
