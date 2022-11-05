package br.com.ufrn.troquinhasrestapi.dto;

import br.com.ufrn.troquinhasrestapi.model.Contato;

public class SignUpRequestDTO {
    
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    Contato contato;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Contato getContato() {
        return contato;
    }
    
    public void setContato(Contato contato) {
        this.contato = contato;
    }

    
}
