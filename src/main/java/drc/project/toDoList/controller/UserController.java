package drc.project.toDoList.controller;


import at.favre.lib.crypto.bcrypt.BCrypt;
import drc.project.toDoList.entitys.User;
import drc.project.toDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/cadastro-de-usuarios")
    public ResponseEntity create(@RequestBody User user){
        var usuario = this.userRepository.findByUsername(user.getUsername());
        if (usuario != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Escolha outro username, por favor, esse já existe!");
        }

        // cripto bd - password
        var passwordHashred = BCrypt.withDefaults().hashToString(12 , user.getPassword().toCharArray());
        user.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

}
