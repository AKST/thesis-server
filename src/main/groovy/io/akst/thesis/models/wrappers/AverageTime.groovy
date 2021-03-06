package io.akst.thesis.models.wrappers

import java.math.BigDecimal

import javax.persistence.Column

import org.hibernate.annotations.Type

import io.akst.thesis.models.util.Semver
import io.akst.thesis.models.util.ProjectionWrapper

@groovy.transform.TypeChecked
class AverageTime implements Serializable, ProjectionWrapper {
  def int packageId

  def Semver version

  def double averageTime

  def AverageTime(int id, Semver version, double average) {
    this.packageId = id
    this.version = version
    this.averageTime = average
  }

  def Map serialise() {
    return [
      id: "${this.packageId}-${this.version}-time",
      data: [
        'package-id': this.packageId,
        'ghc-version': this.version,
        'average-time': this.averageTime,
      ]
    ]
  }
}
