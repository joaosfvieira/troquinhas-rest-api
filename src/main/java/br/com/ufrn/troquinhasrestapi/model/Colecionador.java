package br.com.ufrn.troquinhasrestapi.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "colecionador")
public class Colecionador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String nome;
    
    @Column(length = 50)
    private String sobrenome;
    
    @Column(unique=true, length = 50)
    private String email;
    
	private String senha;

    /*
    @Column
    private boolean admin;
    */

    @ManyToOne
    @JoinColumn(name="pontos_troca_id")
    private PontoTroca pontoTroca;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id", referencedColumnName = "id")
    @JsonManagedReference
    Contato contato;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reputacao_colecionador_id", referencedColumnName = "id")
    ReputacaoColecionador reputacao;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE})
    @JoinTable(name="colecionador_has_figurinhas",
    joinColumns=@JoinColumn(name="colecionador_id"),
    inverseJoinColumns=@JoinColumn(name="figurinha_id"))
    private Set<Figurinha> figurinhasAdquiridas;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE})
	@JoinTable(name="colecionador_wants_figurinhas",
			joinColumns=@JoinColumn(name="colecionador_id"),
			inverseJoinColumns=@JoinColumn(name="figurinha_id"))
	private Set<Figurinha> figurinhasDesejadas;
}