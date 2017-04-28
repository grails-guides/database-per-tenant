package example

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.services.Join
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

@Service(Vehicle) // <1>
@CurrentTenant // <2>
@CompileStatic
abstract class VehicleService {

    @Join('engines') // <3>
    abstract List<Vehicle> list(Map args )

    abstract Integer count()

    @Join('engines')
    abstract Vehicle find(Serializable id)

    abstract Vehicle save(String model, // <4>
                            Integer year)

    @Transactional
    Vehicle update( Serializable id, // <5>
                    String model,
                    Integer year) {
        Vehicle vehicle = find(id)
        if(vehicle != null) {
            vehicle.model = model
            vehicle.year = year
            vehicle.save(failOnError:true)
        }
        return vehicle
    }

    abstract Vehicle delete(Serializable id)
}