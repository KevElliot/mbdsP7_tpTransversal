package itu.mbds.tpt

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Equipe)
@Transactional
interface EquipeService {

    Equipe get(Serializable id)

    List<Equipe> list(Map args)

    Long count()

    void delete(Serializable id)

    Equipe save(Equipe equipe)
}
