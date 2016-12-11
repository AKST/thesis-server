package io.akst.thesis.controllers

import groovy.transform.stc.ClosureParams

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.criteria.Path
import javax.persistence.criteria.Root
import javax.persistence.criteria.JoinType
import javax.persistence.criteria.Expression
import javax.persistence.criteria.AbstractQuery
import javax.persistence.criteria.CriteriaBuilder

import io.akst.thesis.controllers.query.AverageTimeQuery
import io.akst.thesis.controllers.query.AverageSizeQuery

import io.akst.thesis.models.wrappers.AverageTime
import io.akst.thesis.models.wrappers.AverageSize
import io.akst.thesis.models.FileOutput
import io.akst.thesis.models.Package
import io.akst.thesis.models.Result
import io.akst.thesis.models.Batch

import io.akst.thesis.models.util.Semver

class AverageDAO {
  private def EntityManagerFactory factory

  /**
   * Generates the average sizes grouped on
   *  - file-extension
   *  - package-id
   *  - ghc-version
   */
  def Collection<AverageSize> findAllSizes(AverageSizeQuery query) {
    return this.withSession({ EntityManager session ->
      def builder = session.getCriteriaBuilder()
      def criteria = builder.createQuery(AverageSize.class)

      Root<Package> packageRoot = criteria.from(Package.class)
      Root<Batch> batchRoot = criteria.from(Batch.class)
      Root<Result> resultRoot = criteria.from(Result.class)
      Root<FileOutput> fileoutRoot = criteria.from(FileOutput.class)

      Path<Integer> packageIdPath = packageRoot.get("id")
      Path<Semver> resultVerPath = resultRoot.get("version")
      Path<String> fileoutFExtPath = fileoutRoot.get('fileExtension')
      Expression<Double> averageSize = builder.sum(fileoutRoot.get("fileSize"))

      def constraints = query.predicateWith(builder, fileoutRoot, [
        builder.equal(packageIdPath, batchRoot.get('packageId')),
        builder.equal(batchRoot.get('id'), resultRoot.get('batchId')),
        builder.equal(resultRoot.get('id'), fileoutRoot.get('resultId'))
      ])

      criteria.multiselect(packageIdPath, resultVerPath, fileoutFExtPath, averageSize)
      criteria.groupBy(fileoutFExtPath, packageIdPath, resultVerPath)
      criteria.where(constraints)

      return session.createQuery(criteria).getResultList()
    })
  }

  /**
   * Generates the average compiliation times grouped on
   *  - file-extension
   *  - package-id
   *  - ghc-version
   */
  def Collection<AverageTime> findAllTimes(AverageTimeQuery query) {
    return this.withSession({ EntityManager session ->
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
      criteria.where(query.predicateWith(builder, [
        builder.equal(packageIdPath, batchRoot.get('packageId')),
        builder.equal(batchRoot.get('id'), resultRoot.get('batchId')),
      ]))

      return session.createQuery(criteria).getResultList()
    })
  }

  def private <U> U withSession(Closure<U> closure) {
    def session = this.factory.createEntityManager()
    def results = closure(session)
    session.close()
    return results
  }
}
