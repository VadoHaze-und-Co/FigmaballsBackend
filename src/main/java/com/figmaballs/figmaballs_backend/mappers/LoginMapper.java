package com.figmaballs.figmaballs_backend.mappers;

import com.figmaballs.figmaballs_backend.dtos.AccountDTO;
import com.figmaballs.figmaballs_backend.dtos.LoginDTO;
import com.figmaballs.figmaballs_backend.entities.LoginEntity;
import com.figmaballs.figmaballs_backend.services.LoginService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LoginMapper extends Mapper {

    private final LoginService service;

    public LoginMapper(LoginService service) {
        this.service = service;
    }

    /*public LoginEntity loginDtoToEntity(LoginDTO dto) {
        LoginEntity entity = new LoginEntity();
        List<LoginEntity> entities = this.service.findAll();
        entity = entities.stream().filter(e -> Objects.equals(e.user.getUserName(), dto.getUsername())).findAny().orElse(null);
    }*/

    public AccountDTO loginEntityToAccountDTO(LoginEntity entity, boolean isPasswordCorrect) {
        if (entity == null) {
            return new AccountDTO(
                    0L,
                    0L,
                    0L,
                    false,
                    isPasswordCorrect
            );
        }
        return new AccountDTO(
                entity.getId(),
                entity.getUser().getId(),
                entity.getLastLogin(),
                entity.getSecuredPassword(),
                isPasswordCorrect
        );
    }
}
