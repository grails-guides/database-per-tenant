package example

import grails.gorm.transactions.ReadOnly
import org.grails.datastore.mapping.multitenancy.web.SessionTenantResolver

class ManufacturerController {

    @ReadOnly
    def index() {
        render view:'/index', model:[manufacturers: Manufacturer.list()]
    }

    @ReadOnly
    def select(String id) {
        Manufacturer m = Manufacturer.where {
            name == id
        }.first()
        if(m) {
            session.setAttribute(SessionTenantResolver.ATTRIBUTE, m.name.toLowerCase())
            redirect controller:'vehicle'
        }
        else {
            render status:404
        }
    }
}
