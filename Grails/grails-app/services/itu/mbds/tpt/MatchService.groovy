package itu.mbds.tpt

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Match)
interface MatchService {
    Match get(Serializable id)

    List<Match> list(Map args)

    Long count()

    void delete(Serializable id)

    Match save(Match match)
}
