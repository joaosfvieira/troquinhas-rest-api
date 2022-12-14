package br.com.ufrn.troquinhasrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@Table(name = "contatos")
@JsonIgnoreProperties({"colecionador"})
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "contato")
    @JsonBackReference(value="colecionador-contato")
    private Colecionador colecionador;

    @Column(name = "contato", length=50)
    private String numeroOuEmail;

    @Column(name = "descricao", length=50)
    private String descricao;

}