package br.com.ufrn.troquinhasrestapi.model;

import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Figurinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "raridade")
    private String raridade;
    
    @ManyToMany(mappedBy = "figurinhasAdquiridas")
    @JsonIgnore
    private Set<AlbumPessoal> colecionadoresPossuem;

    @ManyToMany(mappedBy = "figurinhasDesejadas")
    @JsonIgnore
    private Set<AlbumPessoal> colecionadoresDesejam;

    @ManyToOne
    private AlbumTipo albumTipo;

}