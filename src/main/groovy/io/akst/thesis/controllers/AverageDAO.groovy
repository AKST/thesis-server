package io.akst.thesis.controllers

import javax.persistence.EntityManagerFactory as SessionFactory

import io.akst.thesis.models.Average

class AverageDAO {
  private def SessionFactory factory

  def Collection<Average> findAll() {
    def session = this.factory.openSession()
    session.createNamedQuery("Average.times", Average.class).getResultList()
  }
}
