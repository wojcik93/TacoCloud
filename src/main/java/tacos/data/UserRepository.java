package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Client;

public interface UserRepository extends CrudRepository<Client, Long>{

	Client findByUsername(String username);
}