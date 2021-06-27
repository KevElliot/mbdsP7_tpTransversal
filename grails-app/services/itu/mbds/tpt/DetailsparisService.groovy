package itu.mbds.tpt

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Detailsparis)
interface DetailsparisService {
    Detailsparis get(Serializable id)

    List<Detailsparis> list(Map args)

    Long count()

    void delete(Serializable id)

    Detailsparis save(Detailsparis detailsparis)
}
