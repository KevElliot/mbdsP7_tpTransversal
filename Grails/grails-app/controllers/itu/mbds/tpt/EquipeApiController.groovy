package itu.mbds.tpt

import grails.converters.JSON
import grails.converters.XML
import org.springframework.web.bind.annotation.CrossOrigin

@CrossOrigin(origins="*")
class EquipeApiController {

    EquipeService equipeService

    def equipe() {
        switch (request.getMethod()) {
            case "GET":
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
                def equipeAsJson = request.getJSON()
                equipeInstance.nom = equipeAsJson.nom
                equipeInstance.note = equipeAsJson.note
                equipeService.save(equipeInstance)
                if (!equipeInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render equipeInstance as XML }
                    json { render equipeInstance as JSON }
                }
                serializeData(equipeInstance, request.getHeader("Accept"))
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
            default:
                return response.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED
                break
        }
        return response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }

    def equipes() {
        switch (request.getMethod()) {
            case "POST":
                def equipeAsJson = request.getJSON()
                equipeAsJson.each {
                    equipe ->
                        def equipeInstance = new Equipe(
                                nom: equipe.nom,
                                note: equipe.note
                        )
                }
                break
            case "GET":
                def equipesInstance = Equipe.getAll()
                if (!equipesInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render EquipesInstance as XML }
                    json { render EquipesInstance as JSON }
                }
                serializeData(equipesInstance, request.getHeader("Accept"))
                break
            default:
                return response.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED
                break
        }
    }
}
