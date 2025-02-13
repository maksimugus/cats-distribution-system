package itmo.tech.lab5owner.contracts;

import itmo.tech.lab5domain.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
