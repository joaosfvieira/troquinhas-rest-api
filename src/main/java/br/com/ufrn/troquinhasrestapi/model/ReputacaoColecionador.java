package br.com.ufrn.troquinhasrestapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "reputacao_colecionador")
public class ReputacaoColecionador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "reputacao")
    private Colecionador colecionador;

    @Column(name = "reputacao")
    private int reputacao;
    
}
