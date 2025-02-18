package prography.table_tennis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TableTennisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TableTennisApplication.class, args);
	}

}
