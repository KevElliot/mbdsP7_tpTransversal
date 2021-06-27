package itu.mbds.tpt

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray

@Transactional
class EquipeApiService {

    def save(Equipe equipe){
        equipe.save(failOnError: true)
        equipe.errors.allErrors
    }
    def saveAll(JSONArray equipes){
        equipes.each {
            equipe ->
                def equipeInstance = new Equipe(
                        nom: equipe.nom,
                        note: equipe.note
                ).save(failOnError:true)
                println "Saved "+equipeInstance.nom
        }
    }
}
