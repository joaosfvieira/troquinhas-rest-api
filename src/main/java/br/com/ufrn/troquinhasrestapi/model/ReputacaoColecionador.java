package br.com.ufrn.troquinhasrestapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "reputacao_colecionador")
public class ReputacaoColecionador {

    @OneToOne(mappedBy = "reputacao")
    private Colecionador colecionador;

    @Column(name = "reputacao")
    private int reputacao;
    @Id
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
