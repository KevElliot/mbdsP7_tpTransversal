package itu.mbds.tpt

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray

@Transactional
class MatchApiService {

    def save(Match match){
        match.save(failOnError: true)
        match.errors.allErrors
    }
    def saveAll(JSONArray matchs){
        println matchs.size()
        matchs.each {
            match ->
                println match.equipe1
                println match.equipe2
                def equipe1 = Equipe.get(match.equipe1.id)
                def equipe2 = Equipe.get(match.equipe2.id)
                def matchInstance = new Match()
                matchInstance.equipe1=equipe1
                matchInstance.equipe2=equipe2
                println matchInstance.equipe2.id
                matchInstance.datematch=match.datematch
                matchInstance.lieumatch=match.lieumatch
                matchInstance.cotev1=match.cotev1
                matchInstance.cotex=match.cotex
                matchInstance.cotev2=match.cotev2
                matchInstance.datematch= null
                matchInstance.resultat = null
                matchInstance.save(failOnError: true)
                matchInstance.errors.allErrors
        }
    }
    def resultat(Match match){

    }
    def updateCote(Match match, String prono){

    }
}
