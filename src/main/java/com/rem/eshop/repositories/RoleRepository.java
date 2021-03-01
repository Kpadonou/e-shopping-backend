package com.rem.eshop.repositories;

import java.util.Optional;

import com.rem.eshop.models.ERole;
import com.rem.eshop.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
