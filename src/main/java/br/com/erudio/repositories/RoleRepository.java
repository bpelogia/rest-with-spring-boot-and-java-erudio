package br.com.erudio.repositories;

import br.com.erudio.data.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
   Role findByName(String name);
}
