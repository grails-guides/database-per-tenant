package example

class UrlMappings {

    static mappings = {
        // tag::home[]
        "/"(controller: "manufacturer")
        // end::home[]
        // tag::manufacturerSelect[]
        "/manufacturer/$id"(controller: "manufacturer",action: "select")
        // end::manufacturerSelect[]
        // tag::vehicles[]
        "/vehicles"(resources:'vehicle')
        // end::vehicles[]
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
