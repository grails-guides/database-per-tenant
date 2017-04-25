package example

import grails.validation.ValidationErrors
import groovy.transform.CompileStatic

import javax.validation.ConstraintViolationException

@CompileStatic
trait ConstraintViolationHandler {
    ValidationErrors asErrors(Object object, ConstraintViolationException e) {
        ValidationErrors errors = new ValidationErrors(object)
        for (violation in e.constraintViolations) {
            String property = violation.propertyPath.last().name
            String code = "${object.getClass().simpleName}.${violation.propertyPath}"
            String message = "${property} $violation.message"
            errors.rejectValue(property, code, message)
        }
        return errors
    }
}