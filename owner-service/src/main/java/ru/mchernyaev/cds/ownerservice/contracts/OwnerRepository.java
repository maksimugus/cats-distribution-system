package ru.mchernyaev.cds.ownerservice.contracts;

import ru.mchernyaev.cds.domain.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
