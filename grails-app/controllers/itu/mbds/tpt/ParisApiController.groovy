package itu.mbds.tpt

import grails.converters.JSON
import grails.converters.XML
import grails.gorm.transactions.Transactional
import org.springframework.web.bind.annotation.CrossOrigin
import javax.servlet.http.HttpServletResponse

@CrossOrigin(origins="*")
@Transactional
class ParisApiController {

    ParisService parisService
    ParisApiService parisApiService

    def paris() {
        switch (request.getMethod()) {
            case "GET":
                println new Date()
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def parisInstance = Paris.get(params.id)
                serializeData(parisInstance, request.getHeader("Accept"))
                break
                break
            case "POST":
                def parisAsJson = request.getJSON()
                def parisInstances= parisAsJson as Paris

                //parisInstances.idclient= parisAsJson.idclient
                //parisInstances.detailsparis = parisAsJson.detailsparis.toSet()
                parisApiService.placeParis(parisInstances)
//                return response.status = HttpServletResponse.SC_OK
                break
            default:
                return response.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED
                break
        }
        //return response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }
    def listeParis() {
        switch (request.getMethod()) {
            case "GET":
                println "GET matchs"
                if (params.idclient){
                    def liste= Paris.findAllByIdclient(params.idclient)
                    response.withFormat {
                        json { render liste as JSON }
                    }
                    serializeData(liste, request.getHeader("Accept"))
                }

                def parisInstance = Paris.getAll()
                if (!parisInstance)
                    println "Nothing"
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    json { render parisInstance as JSON }
                }
                serializeData(parisInstance, request.getHeader("Accept"))
                break
            default:
                return response.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED
                break
        }
    }
    def serializeData(object, format) {
        switch (format) {
            case 'json':
            case 'application/json':
            case 'text/json':
                render object as JSON
                break
            case 'xml':
            case 'application/xml':
            case 'text/xml':
                render object as XML
                break
        }
    }
}
