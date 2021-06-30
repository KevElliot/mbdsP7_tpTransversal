package itu.mbds.tpt

import grails.converters.JSON
import grails.converters.XML
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import org.springframework.web.bind.annotation.CrossOrigin

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import javax.servlet.http.HttpServletResponse

@CrossOrigin(origins="*")
@Transactional
class MatchApiController {
    MatchService matchService
    MatchApiService matchApiService

    def match() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                def matchInstance = Match.get(params.id)
                println "Nom : "+matchInstance
                if (!matchInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
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
                matchApiService.save(matchInstance)
                if (!matchInstance)
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
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
                    json { render matchInstance as JSON }
                }
                matchService.delete(params.id)
                return response.status = HttpServletResponse.SC_OK
                break
            case "POST":

            default:
                return response.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED
                break
        }
        //return response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }

    def matchs() {
        switch (request.getMethod()) {
            case "POST":
                println "POST"
                def matchsAsJson = request.getJSON()
                println matchsAsJson
                //List<Equipe> listes = JSON.parse(equipeAsJson) as List<Equipe>
                matchApiService.saveAll(matchsAsJson)
                break
            case "GET":
                println "GET matchs"
                def matchsInstance = Match.findAllByFini(0)
                if (!matchsInstance)
                    println "Nothing"
                    //return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    json { render matchsInstance as JSON }
                }
                serializeData(matchsInstance, request.getHeader("Accept"))
                break
            default:
                return response.status = HttpServletResponse.SC_METHOD_NOT_ALLOWED
                break
        }
    }
    def listeTotale() {
        switch (request.getMethod()) {
            case "GET":
                println "GET matchs"
                def matchsInstance = Match.getAll()
                if (!matchsInstance)
                    println "Nothing"
                //return response.status = HttpServletResponse.SC_NOT_FOUND
                response.withFormat {
                    json { render matchsInstance as JSON }
                }
                serializeData(matchsInstance, request.getHeader("Accept"))
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
