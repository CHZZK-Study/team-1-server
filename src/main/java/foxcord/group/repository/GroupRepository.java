package foxcord.group.repository;

import foxcord.group.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups, Long> {

}
