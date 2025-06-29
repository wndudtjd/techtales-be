package techtales.techtalesbe.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techtales.techtalesbe.domain.entity.Role;
import techtales.techtalesbe.domain.enums.UserRole;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, UserRole> {
}
