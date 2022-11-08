package br.com.ufrn.troquinhasrestapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties("colecionador")
public class ReputacaoColecionador {

    @OneToOne(mappedBy = "reputacaoColecionador")
    @JsonBackReference(value="colecionador-reputacao")
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
