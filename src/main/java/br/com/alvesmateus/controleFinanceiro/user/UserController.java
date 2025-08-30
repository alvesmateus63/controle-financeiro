package br.com.alvesmateus.controleFinanceiro.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alvesmateus.controleFinanceiro.user.dtos.UserResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<UserResponse> getLoggedUser() {
        UserResponse response = userService.getLoggedUser();

        return ResponseEntity.ok(response);
    }

}
