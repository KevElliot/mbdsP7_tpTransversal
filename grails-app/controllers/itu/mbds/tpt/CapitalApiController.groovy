package itu.mbds.tpt

import grails.converters.JSON
import grails.converters.XML
import grails.gorm.transactions.Transactional
import org.springframework.web.bind.annotation.CrossOrigin

import javax.servlet.http.HttpServletResponse

@CrossOrigin(origins="*")
@Transactional
class CapitalApiController {

    CapitalApiService capitalApiService

    def capital() {
        switch (request.getMethod()) {
            case "GET":
                println new Date()
                if (!params.idclient)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def capitalInstance = Capital.findByIdclient(params.idclient)
                if (!capitalInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render capitalInstance as XML }
                    json { render capitalInstance as JSON }
                }
                serializeData(capitalInstance, request.getHeader("Accept"))
                break
            case "PUT":
                if (!params.idclient)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def capitalInstance = Capital.findByIdclient(params.idclient)
                if (!capitalInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                def capitalAsJson = request.getJSON()
                capitalInstance.capital = capitalAsJson.capital
                capitalApiService.save(capitalInstance)
                println "updated"
                response.withFormat {
                    xml { render capitalInstance as XML }
                    json { render capitalInstance as JSON }
                }
                serializeData(capitalInstance, request.getHeader("Accept"))
                return response.status = HttpServletResponse.SC_OK
                break
            case "POST":
                def capitalAsJson = request.getJSON()

                capitalApiService.save(capitalAsJson)
                return response.status = HttpServletResponse.SC_OK
                break
            default:
                return response.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED
                break
        }
        return response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
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
