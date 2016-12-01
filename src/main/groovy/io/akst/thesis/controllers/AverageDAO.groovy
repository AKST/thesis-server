package io.akst.thesis.controllers

import javax.persistence.criteria.Path
import javax.persistence.criteria.Root
import javax.persistence.criteria.JoinType
import javax.persistence.criteria.Expression
import javax.persistence.criteria.AbstractQuery
import javax.persistence.criteria.CriteriaBuilder

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

import io.akst.thesis.models.wrappers.AverageTime
import io.akst.thesis.models.Average
import io.akst.thesis.models.Package
import io.akst.thesis.models.Result
import io.akst.thesis.models.Batch

import io.akst.thesis.models.util.Semver

@groovy.transform.TypeChecked
class AverageDAO {
  private def EntityManagerFactory factory

  def Collection<Average> findAll() {
    def session = this.factory.createEntityManager()
    def result = session.createNamedQuery("Average.timesVSize", Average.class).getResultList()
    session.close()
    return result
  }

  def Collection<AverageTime> findAllTimes() {
    def session = this.factory.createEntityManager()
    def builder = session.getCriteriaBuilder()
    def criteria = builder.createQuery(AverageTime.class)

    Root<Package> packageRoot = criteria.from(Package.class)
    Root<Batch> batchRoot = criteria.from(Batch.class)
    Root<Result> resultRoot = criteria.from(Result.class)

    Path<Integer> packageIdPath = packageRoot.get("id")
    Path<Semver> resultVerPath = resultRoot.get("version")
    Expression<Double> averageTime = builder.avg(resultRoot.get("seconds"))

    criteria.multiselect(packageIdPath, resultVerPath, averageTime)
    criteria.groupBy(packageIdPath, resultVerPath)
    criteria.where(
      builder.equal(packageIdPath, batchRoot.get('packageId')),
      builder.equal(batchRoot.get('id'), resultRoot.get('batchId')))

    def result = session.createQuery(criteria).getResultList()
    session.close()
    return result
  }
}
