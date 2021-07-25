package itu.mbds.tpt

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray

@Transactional
class MatchApiService {

    ParisApiService parisApiService

    def save(Match match) {
        match.save(failOnError: true)
        match.errors.allErrors
    }

    def saveAll(JSONArray matchs) {
        println matchs.size()
        matchs.each {
            match ->
                println match.equipe1
                println match.equipe2
                def equipe1 = Equipe.get(match.equipe1.id)
                def equipe2 = Equipe.get(match.equipe2.id)
                def matchInstance = new Match()
                matchInstance.equipe1 = equipe1
                matchInstance.equipe2 = equipe2
                println matchInstance.equipe2.id
                matchInstance.datematch = match.datematch
                matchInstance.lieumatch = match.lieumatch
                matchInstance.cotev1 = match.cotev1
                matchInstance.cotex = match.cotex
                matchInstance.cotev2 = match.cotev2
                matchInstance.datematch = null
                matchInstance.resultat = null
                matchInstance.save(failOnError: true)
                matchInstance.errors.allErrors
        }
    }

    def resultat(Match match) {
        def matchOld = Match.get(match.id)
        matchOld.but1 = match.but1
        matchOld.but2 = match.but2
        matchOld.fini = 1
        def retour = new InfoResultat()
        def montantPaiement = 0
        def montantGagne = 0
        def nombreparis = 0
        List<Capital> listCapital = new ArrayList<Capital>()
        if (matchOld.but1 > matchOld.but2) {
            matchOld.resultat = "V1"
            Equipe equipe1 = matchOld.equipe1
            equipe1.note = equipe1.note + 3
            equipe1.save()
        }
        if (matchOld.but1 < matchOld.but2) {
            matchOld.resultat = "V2"
            Equipe equipe2 = matchOld.equipe2
            equipe2.note = equipe2.note + 3
            equipe2.save()
        }
        if (matchOld.but1 == matchOld.but2) {
            matchOld.resultat = "X"
            Equipe equipe1 = matchOld.equipe1
            equipe1.note = equipe1.note + 1
            equipe1.save()
            Equipe equipe2 = matchOld.equipe2
            equipe2.note = equipe2.note + 1
            equipe2.save()
        }
        matchOld.save()
        println matchOld.resultat + " " + matchOld.id
        def detailsParis = Detailsparis.findAllByMatch(matchOld)
        println "Size detailsParis " + detailsParis.size()
        //Parcourir detailsParis
        detailsParis.each {
            Detailsparis detail ->
                nombreparis += 1
                Paris paris = Paris.get(detail.paris.id)
                //Si prono vrai
                println "pronoVSresultat " + detail.prono + " " + matchOld.resultat
                if (detail.prono.equals(matchOld.resultat)) {
                    detail.gain = 'OK'
                    paris.nbgain = paris.nbgain + 1
                    //Si nombre de detail gain == nombre de match
                    if (paris.nbgain == paris.nbmatch) {
                        paris.gain = 'OK'
                        Capital capital = new Capital()
                        capital.idclient = paris.idclient
                        capital.capital = paris.gainpossible
                        montantPaiement += paris.gainpossible
                        listCapital.add(capital)
                    }
                }
                //Si prono faux
                else {
                    detail.gain = 'KO'
                    paris.gain = 'KO'
                    paris.nbperdu = paris.nbperdu + 1
                    montantGagne += paris.gainpossible
                }
                detail.save()
                paris.save()

        }
        //Appel web service mise à jour Jeton
        println "Size capital " + listCapital.size()
        //parisApiService.updateDetailsParisKO(matchOld.resultat,matchOld.id)
        retour.capitals = listCapital
        retour.montantPaiement = montantPaiement
        retour.nombreParis = nombreparis
        retour.montantGagne = montantGagne
        return retour
    }

    def updateCote(Match match, String prono) {

    }
    def listeTotalPagine(Integer idEquipe, Integer fini,Integer first, Integer max){
        def listeFinal = null;
        if(idEquipe!=0){
            def equipe = Equipe.get(idEquipe)
            def liste1 = Match.findAllByEquipe1AndFini(equipe,fini,[max: max, offset: first])
            def liste2 = Match.findAllByEquipe2AndFini(equipe,fini,[max: max, offset: first])
            listeFinal = liste1 + liste2
        }else{
            listeFinal = Match.findAllByFini(fini,[max: max, offset: first])
        }
        return listeFinal
    }
    def listeTotalNonPagine(Integer idEquipe, Integer fini){
        def listeFinal = null
        if(idEquipe!=0){
            def equipe = Equipe.get(idEquipe)
            def liste1 = Match.findAllByEquipe1AndFini(equipe,fini)
            def liste2 = Match.findAllByEquipe2AndFini(equipe,fini)
            listeFinal = liste1 + liste2
        }else{
            listeFinal = Match.findAllByFini(fini)
        }
        return listeFinal
    }
    def contListeTotal(Integer idEquipe, Integer fini){
        def countTotal=0
        if(idEquipe!=0) {
            def equipe = Equipe.get(idEquipe)
            def countMatch1 = Match.countByEquipe1AndFini(equipe, fini)
            println countMatch1
            def countMatch2 = Match.countByEquipe2AndFini(equipe, fini)
            println countMatch2
            countTotal = countMatch1 + countMatch2
        }else{
            countTotal= Match.countByFini(fini)
        }
        return countTotal
    }

    /**def updateDetailsParisKO(String prono, Long match_id) {def sql = new Sql(sessionFactory.currentSession.connection())
     println "update dp KO"
     String query = "update detailsparis set gain='KO' where prono <>'" + prono + "' and match_id=" + match_id
     sql.executeUpdate(query);
     println "KO dp updated"}**/
}
class InfoResultat {
    Capital[] capitals
    Double montantPaiement
    Integer nombreParis
    Double montantGagne
}
