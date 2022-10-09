package com.ufc.dspersist.lab7.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Aluno {
    @NonNull
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nome;

    @NonNull
    @Getter
    @Setter
    private String cpf;

    @Getter
    @Setter
    private int matricula;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String telefone;
}
