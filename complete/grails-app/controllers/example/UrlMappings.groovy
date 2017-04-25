package example

class UrlMappings {

    static mappings = {
        "/"(controller: "manufacturer")
        "/manufacturer/$id"(controller: "manufacturer",action: "select")
        "/vehicles"(resources:'vehicle')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
