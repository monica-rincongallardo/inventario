package com.prueba.inventario.service.security;

import com.prueba.inventario.entity.Usuario;
import com.prueba.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

// Servicio que permite autenticar usuarios desde mysql

@Service
public class UsuarioSecurityService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(username).orElseThrow(() ->
        new UsernameNotFoundException("Usuario no encontrado"));

        return new User(
                usuario.getCorreo(),
                usuario.getContrasena(),
                List.of(new SimpleGrantedAuthority(
                        usuario.getRol().getNombreRol()
                ))
        );
    }
}
