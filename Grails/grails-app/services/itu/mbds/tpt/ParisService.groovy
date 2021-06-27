package itu.mbds.tpt

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Paris)
interface ParisService {

    Paris get(Serializable id)

    List<Paris> list(Map args)

    Long count()

    void delete(Serializable id)

    Paris save(Paris paris)
}
