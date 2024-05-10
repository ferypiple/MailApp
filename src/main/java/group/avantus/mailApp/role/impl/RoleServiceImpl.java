package group.avantus.mailApp.role.impl;

import group.avantus.mailApp.role.RoleService;
import group.avantus.mailApp.role.model.Role;
import group.avantus.mailApp.role.repository.impl.jpa.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}