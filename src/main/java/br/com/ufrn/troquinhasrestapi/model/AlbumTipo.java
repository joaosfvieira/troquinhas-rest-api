package br.com.ufrn.troquinhasrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class AlbumTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    @JsonIgnore
    private Date dataLancamento;

    @OneToMany
    @JsonIgnore
    private Set<Figurinha> figurinhasDoAlbum;

    @OneToMany
    @JoinColumn(name = "album_tipo_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<AlbumPessoal> albunsPessoais;
}
