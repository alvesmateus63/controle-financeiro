package br.com.alvesmateus.controleFinanceiro.user;

import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.alvesmateus.controleFinanceiro.user.dtos.UserResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getLoggedUser() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findById(loggedUser.getId())
            .map(this::toResponseDTO)
            .orElseThrow();
    }

    private UserResponse toResponseDTO(User user) {
        return new UserResponse(
            user.getId(), 
            user.getName(), 
            user.getEmail()
        );
    }

}
