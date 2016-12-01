package io.akst.thesis.models.wrappers

import java.math.BigDecimal

import javax.persistence.Column

import org.hibernate.annotations.Type

import io.akst.thesis.models.util.Semver

@groovy.transform.TypeChecked
class AverageTime implements Serializable {
  def @Lazy String id = { "${this.packageId}-${this.version}-time" }()

  def int packageId

  def Semver version

  def double averageTime

  def AverageTime(int id, Semver version, double average) {
    this.packageId = id
    this.version = version
    this.averageTime = average
  }

  def serialise() {
    return [
      id: this.id,
      data: [
        package_id: this.packageId,
        ghc_version: this.version,
        average_time: this.averageTime,
      ]
    ]
  }
}
