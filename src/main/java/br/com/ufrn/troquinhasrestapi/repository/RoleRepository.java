package br.com.ufrn.troquinhasrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufrn.troquinhasrestapi.model.Role;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByName(String name);
}
