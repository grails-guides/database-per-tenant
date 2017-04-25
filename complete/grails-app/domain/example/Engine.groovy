package example

import grails.gorm.MultiTenant

class Engine implements MultiTenant<Engine> {
    Integer cylinders

    static constraints = {
        cylinders nullable: false
    }
}
