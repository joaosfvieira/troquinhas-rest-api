package br.com.ufrn.troquinhasrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album")
public class AlbumPessoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Colecionador colecionador;

    @ManyToOne
    private AlbumTipo albumTipo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE})
    @JoinTable(name="album_has_figurinhas",
            joinColumns=@JoinColumn(name="album_id"),
            inverseJoinColumns=@JoinColumn(name="figurinha_id"))
    private Set<Figurinha> figurinhasAdquiridas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE})
    @JoinTable(name="album_wants_figurinhas",
            joinColumns=@JoinColumn(name="album_id"),
            inverseJoinColumns=@JoinColumn(name="figurinha_id"))
    private Set<Figurinha> figurinhasDesejadas;
}
