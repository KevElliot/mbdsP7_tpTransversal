package itu.mbds.tpt

import grails.converters.JSON
import grails.converters.XML
import grails.gorm.transactions.Transactional
import org.springframework.web.bind.annotation.CrossOrigin

@CrossOrigin(origins="*")
@Transactional
class EquipeApiController {

    EquipeService equipeService
    EquipeApiService equipeApiService
    def equipe() {
        switch (request.getMethod()) {
            case "GET":
                println new Date()
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def equipeInstance = Equipe.get(params.id)
                if (!equipeInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render equipeInstance as XML }
                    json { render equipeInstance as JSON }
                }
                serializeData(equipeInstance, request.getHeader("Accept"))
                break
            case "PUT":
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def equipeInstance = Equipe.get(params.id)
                println equipeInstance.nom
                if (!equipeInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                def equipeAsJson = request.getJSON()
                equipeInstance.nom = equipeAsJson.nom
                equipeInstance.note = equipeAsJson.note
                println equipeInstance.nom
                equipeApiService.save(equipeInstance)
                println "updated"
                response.withFormat {
                    xml { render equipeInstance as XML }
                    json { render equipeInstance as JSON }
                }
                serializeData(equipeInstance, request.getHeader("Accept"))
                //return response.status = HttpServletResponse.SC_OK
                break
            case "PATCH":
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def equipeInstance = Equipe.get(params.id)
                def equipeAsJson = request.getJSON()
                if (equipeAsJson.nom != null) {
                    equipeInstance.nom = equipeAsJson.nom
                }
                if (equipeAsJson.note != null && equipeAsJson.note.toString() != "") {
                    equipeInstance.note = tryParseToInteger(equipeAsJson.note.toString())
                }
                equipeService.save(equipeInstance)
                if (!equipeInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render equipeInstance as XML }
                    json { render equipeInstance as JSON }
                }
                serializeData(equipeInstance, request.getHeader("Accept"))
                break
            case "DELETE":
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def equipeInstance = Equipe.get(params.id)
                if (!equipeInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render equipeInstance as XML }
                    json { render equipeInstance as JSON }
                }
                equipeService.delete(params.id)
                return response.status = HttpServletResponse.SC_OK
                break
            case "POST":
                def equipeAsJson = request.getJSON()

                equipeApiService.save(equipeAsJson)
                return response.status = HttpServletResponse.SC_OK
                break
            default:
                return response.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED
                break
        }
        //return response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }

    def equipes() {
        switch (request.getMethod()) {
            case "POST":
                println "POST"
                def equipeAsJson = request.getJSON()
                println equipeAsJson
                //List<Equipe> listes = JSON.parse(equipeAsJson) as List<Equipe>
                equipeApiService.saveAll(equipeAsJson)
                break
            case "GET":
                def equipesInstance = Equipe.getAll()
                if (!equipesInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render equipesInstance as XML }
                    json { render equipesInstance as JSON }
                }
                //serializeData(equipesInstance, request.getHeader("Accept"))
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
