package itu.mbds.tpt

import grails.gorm.transactions.Transactional

@Transactional
class CapitalApiService {

    def save(Capital capital){
        capital.save(failOnError: true)
        capital.errors.allErrors
    }
}
