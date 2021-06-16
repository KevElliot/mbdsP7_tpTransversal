package itu.mbds.tpt

import grails.converters.JSON
import grails.converters.XML
import grails.validation.ValidationException
import org.springframework.web.bind.annotation.CrossOrigin

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

@CrossOrigin(origins="*")
class MatchApiController {
    MatchService matchService

    def Match() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def matchInstance = Match.get(params.id)
                if (!matchInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render matchInstance as XML }
                    json { render matchInstance as JSON }
                }
                serializeData(matchInstance, request.getHeader("Accept"))
                break
            case "PUT":
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def matchInstance = Match.get(params.id)
                def matchAsJson = request.getJSON()
                matchInstance.equipe1 = matchAsJson.equipe1
                matchInstance.equipe2 = matchAsJson.equipe2
                matchInstance.datematch = matchAsJson.datematch
                matchInstance.cotev1 = matchAsJson.cotev1
                matchInstance.cotev2 = matchAsJson.cotev2
                matchInstance.cotex = matchAsJson.cotex
                matchInstance.resultat = matchAsJson.resultat
                matchService.save(matchInstance)
                if (!matchInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render matchInstance as XML }
                    json { render matchInstance as JSON }
                }
                serializeData(matchInstance, request.getHeader("Accept"))
                break
            case "PATCH":
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def matchInstance = Match.get(params.id)
                def matchAsJson = request.getJSON()
                if (matchAsJson.equipe1 != null) {
                    matchInstance.equipe1 = matchAsJson.equipe1
                }
                if (matchAsJson.equipe2 != null) {
                    matchInstance.equipe2 = matchAsJson.equipe2
                }
                if (matchAsJson.cotev1 != null) {
                    matchInstance.cotev1 = tryParseToFloat(matchAsJson.cotev1.toString())
                }
                if (matchAsJson.cotev2 != null) {
                    matchInstance.cotev2 = tryParseToFloat(matchAsJson.cotev2.toString())
                }
                if (matchAsJson.cotex != null) {
                    matchInstance.cotex = tryParseToFloat(matchAsJson.cotex.toString())
                }
                matchService.save(matchInstance)
                if (!matchInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render matchInstance as XML }
                    json { render matchInstance as JSON }
                }
                serializeData(matchInstance, request.getHeader("Accept"))
                break
            case "DELETE":
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def matchInstance = Match.get(params.id)
                if (!matchInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    xml { render matchInstance as XML }
                    json { render matchInstance as JSON }
                }
                matchService.delete(params.id)
                return response.status = HttpServletResponse.SC_OK
                break
            default:
                return response.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED
                break
        }
        return response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }
}
