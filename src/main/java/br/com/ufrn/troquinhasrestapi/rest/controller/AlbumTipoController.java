package br.com.ufrn.troquinhasrestapi.rest.controller;

import br.com.ufrn.troquinhasrestapi.model.AlbumTipo;
import br.com.ufrn.troquinhasrestapi.service.AlbumTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequestMapping("/album-tipo")
@RestController
public class AlbumTipoController {
    @Autowired
    AlbumTipoService albumTipoService;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public AlbumTipo getAlbumTipoById(@PathVariable Integer id) {
        return albumTipoService.getAlbumTipoById(id).orElseThrow();
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<AlbumTipo> getAllAlbumTipo() {
        return albumTipoService.getAllAlbumTipo();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public AlbumTipo save(@RequestBody @Valid AlbumTipo albumTipo) {
        return albumTipoService.save(albumTipo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        albumTipoService.removeAlbumTipo(id);
    }
}
