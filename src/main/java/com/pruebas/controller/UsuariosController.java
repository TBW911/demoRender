package com.pruebas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebas.model.Usuarios;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.UsuariosRepository;

@RestController
@RequestMapping("/bdoklans")
public class UsuariosController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @GetMapping("/usuarios")
    public List<Usuarios> imprimirListaUsuarios() {
        return usuariosRepository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuarios> buscarUsuarioPorId(@PathVariable(value = "id") Long idUsuario)
            throws ResourceNotFoundException {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario para el id :: " + idUsuario));
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/usuarios")
    public Usuarios agregarUsuario(@RequestBody Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable(value = "id") Long idUsuario,
            @RequestBody Usuarios datosUsuario) throws ResourceNotFoundException {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario para el id :: " + idUsuario));

        usuario.setRol(datosUsuario.getRol());
        usuario.setNickName(datosUsuario.getNickName());
        usuario.setPassword(datosUsuario.getPassword());

        final Usuarios usuarioActualizado = usuariosRepository.save(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/usuarios/{id}")
    public Map<String, Boolean> eliminarUsuario(@PathVariable(value = "id") Long idUsuario)
            throws ResourceNotFoundException {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario con el id :: " + idUsuario));

        usuariosRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}