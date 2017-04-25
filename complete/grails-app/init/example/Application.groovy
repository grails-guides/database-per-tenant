package example

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.gorm.transactions.Transactional
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner

class Application extends GrailsAutoConfiguration implements ApplicationRunner {

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    @Transactional
    void run(ApplicationArguments args) throws Exception {
        Manufacturer.saveAll(
            new Manufacturer(name: "Audi"),
            new Manufacturer(name: "Ford"),
        )
    }
}