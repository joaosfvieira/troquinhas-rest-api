package br.com.ufrn.troquinhasrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reputacao_colecionador")
public class ReputacaoColecionador {

    @OneToOne(mappedBy = "reputacao")
    @JsonBackReference
    private Colecionador colecionador;

    @Column(name = "reputacao")
    private int reputacao;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
