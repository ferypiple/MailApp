package group.avantus.mailApp.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);
}
