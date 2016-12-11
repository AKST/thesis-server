package io.akst.thesis.controllers

import javax.persistence.EntityManagerFactory as SessionFactory

import io.akst.thesis.models.Package

class PackageDAO {
  private SessionFactory factory

  Collection<Package> findAll(int count) {
    def session = this.factory.openSession()
    session.createNamedQuery("Package.findAll", Package.class).getResultList()
  }
}
