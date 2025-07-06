package techtales.techtalesbe.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techtales.techtalesbe.user.domain.model.Role;
import techtales.techtalesbe.user.domain.enums.UserRole;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, UserRole> {
}
