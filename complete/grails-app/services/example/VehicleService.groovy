package example

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.services.Join
import grails.gorm.services.Service
import org.hibernate.validator.constraints.NotBlank

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Service(Vehicle) // <1>
@CurrentTenant // <2>
interface VehicleService {

    @Join('engines') // <3>
    List<Vehicle> list(Map args )

    Integer count()

    @Join('engines')
    Vehicle find(Serializable id)

    Vehicle save(@NotBlank String model, // <4>
                 @NotNull @Min(1980l) Integer year)

    Vehicle update(Long id,
                   @NotBlank String model,
                   @NotNull @Min(1980l) Integer year)

    Vehicle delete(Long id)
}