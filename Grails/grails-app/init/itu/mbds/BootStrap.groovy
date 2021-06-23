package itu.mbds

class BootStrap {

    def init = { servletContext ->
    }
    def destroy = {
    }

    /**static authorMarshaller = { Match match ->
        return [
                equipe1: author.firstName,
                lastName: author.lastName,
                numberOfBooks: author.books?.size()
        ]
    }**/
}
