package br.com.alvesmateus.controleFinanceiro.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    public String getUser(@PathVariable Long id) {
        return "User ai em";
    }

}
