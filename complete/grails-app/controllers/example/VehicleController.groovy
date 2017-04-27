package example

import grails.compiler.GrailsCompileStatic

import javax.validation.ConstraintViolationException

import static org.springframework.http.HttpStatus.*

@GrailsCompileStatic
class VehicleController implements ConstraintViolationHandler {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    VehicleService vehicleService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond vehicleService.list(params), model:[vehicleCount: vehicleService.count()]
    }

    def show(Long id) {
        Vehicle vehicle = id ? vehicleService.find(id) : null
        respond vehicle
    }

    def create() {
        respond new Vehicle(params)
    }

    def edit(Long id) {
        show id
    }

    def save(String model, Integer year) {
        try {
            Vehicle vehicle = vehicleService.save(model, year)
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                    redirect vehicle
                }
                '*' { respond vehicle, [status: CREATED] }
            }
        } catch (ConstraintViolationException e) {
            Vehicle vehicle = new Vehicle(model: model, year:year)
            vehicle.errors = asErrors(vehicle, e)
            respond vehicle.errors, view:'create'
        }
    }

    def update(Long id, String model, Integer year) {
        try {
            Vehicle vehicle = vehicleService.update(id, model, year)
            if (vehicle == null) {
                notFound()
            }
            else {
                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.updated.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                        redirect vehicle
                    }
                    '*'{ respond vehicle, [status: OK] }
                }
            }
        } catch (ConstraintViolationException e) {
            Vehicle vehicle = vehicleService.find(id)
            vehicle.errors  = asErrors(vehicle, e)
            respond vehicle.errors, view:'edit'
        }
    }

    def delete(Long id) {
        Vehicle vehicle = vehicleService.delete(id)
        if (vehicle == null) {
            notFound()
        }
        else {
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
