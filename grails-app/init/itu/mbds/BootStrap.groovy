package itu.mbds

import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        JSON.registerObjectMarshaller(Date) {
           return new java.text.SimpleDateFormat('yyy-MM-dd HH:mm').format(it)
        }
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
