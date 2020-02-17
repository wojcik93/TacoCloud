package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityLinks;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner dataLoader(IngredientRepository repo) {
	    return new CommandLineRunner() {
	      @Override
	      public void run(String... args) throws Exception {
	        repo.save(new Ingredient("FLTO", "pszenna", Type.WRAP));
	        repo.save(new Ingredient("COTO", "kukurydziana", Type.WRAP));
	        repo.save(new Ingredient("GRBF", "mielona wolowina", Type.PROTEIN));
	        repo.save(new Ingredient("CARN", "kawalki miesa", Type.PROTEIN));
	        repo.save(new Ingredient("TMTO", "pomidory pokrojone w kostke", Type.VEGGIES));
	        repo.save(new Ingredient("LETC", "salata", Type.VEGGIES));
	        repo.save(new Ingredient("CHED", "cheddar", Type.CHEESE));
	        repo.save(new Ingredient("JACK", "monterey jack", Type.CHEESE));
	        repo.save(new Ingredient("SLSA", "pikantny sos pomidorowy", Type.SAUCE));
	        repo.save(new Ingredient("SRCR", "smietana", Type.SAUCE));
	      }
	    };
	  }

}
