package br.com.ufrn.troquinhasrestapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "figurinhas")
public class Figurinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "raridade")
    private String raridade;
    
    @ManyToMany(mappedBy = "figurinhasAdquiridas")
    @JsonBackReference
//    joinColumns=@JoinColumn(name="figurinha_id"),
//    inverseJoinColumns=@JoinColumn(name="colecionador_id"))
    private Set<Colecionador> colecionadoresPossuem;

    @ManyToMany(mappedBy = "figurinhasDesejadas")
    @JsonBackReference
//    joinColumns=@JoinColumn(name="figurinha_id"),
//    inverseJoinColumns=@JoinColumn(name="colecionador_id"))
    private Set<Colecionador> colecionadoresDesejam;

}