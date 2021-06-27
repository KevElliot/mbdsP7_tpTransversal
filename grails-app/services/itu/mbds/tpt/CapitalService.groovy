package itu.mbds.tpt

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Capital)
interface CapitalService {
    Capital get(Serializable id)

    List<Capital> list(Map args)

    Long count()

    void delete(Serializable id)

    Capital save(Capital capital)
}
