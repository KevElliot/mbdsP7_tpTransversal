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
               // if (!params.id)
                    //return response.status = HttpServletResponse.SC_BAD_REQUEST
                //def matchInstance = Match.get(params.id)
                println request.getJSON()
                def matchAsJson = request.getJSON()
                def match = request.getJSON() as Match
                println matchAsJson.id
                if(!matchAsJson.id){
                    println "BAD REQUEST"
                    return response.status = HttpServletResponse.SC_BAD_REQUEST
                }
                def matchInstance = Match.get(matchAsJson.id)
                if (!matchInstance){
                    return response.status = HttpServletResponse.SC_NOT_FOUND
                }
                Equipe equipe1 = Equipe.get(matchAsJson.equipe1.id)
                Equipe equipe2 = Equipe.get(matchAsJson.equipe2.id)
                matchInstance.equipe1=equipe1
                matchInstance.equipe2=equipe2
                matchInstance.cotev1=matchAsJson.cotev1
                matchInstance.cotex=matchAsJson.cotex
                matchInstance.cotev2=matchAsJson.cotev2
                println match.datematch
                matchInstance.datematch=match.datematch
                matchInstance.lieumatch=matchAsJson.lieumatch
                matchApiService.save(matchInstance)

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
                matchApiService.save(matchInstance)
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
                matchInstance.delete()
                return response.status = HttpServletResponse.SC_OK
                break
            case "POST":
                def matchAsJson = request.getJSON()
                def matchInstance = matchAsJson as Match
                matchApiService.save(matchInstance)
                return response.status = HttpServletResponse.SC_OK
                break
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
    def count() {
        def matchsInstance = matchApiService.contListeTotal(Integer.parseInt(params.idequipe),Integer.parseInt(params.fini))
        println matchsInstance
        render matchsInstance
    }
    def filtre() {
        def matchsInstance = matchApiService.listeTotalPagine(Integer.parseInt(params.idequipe),Integer.parseInt(params.fini),Integer.parseInt(params.first),Integer.parseInt(params.max))
        response.withFormat {
            json { render matchsInstance as JSON }
        }
    }
    def resultat(){
        if(request.getMethod()=="PUT"){
            def matchAsJson = request.getJSON()
            def match = new Match()

            println matchAsJson
            if(!matchAsJson.id){
                println "BAD REQUEST"
                return response.status = HttpServletResponse.SC_BAD_REQUEST
            }
            match.id=matchAsJson.id
            match.but1=matchAsJson.but1
            match.but2=matchAsJson.but2
            def listCapital=matchApiService.resultat(match)
            if(listCapital){
                response.withFormat {
                    json { render listCapital as JSON }
                }
                return response.status = HttpServletResponse.SC_OK
            }

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
