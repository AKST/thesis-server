package io.akst.thesis.models

import java.math.BigDecimal

import javax.persistence.Column

import org.hibernate.annotations.Type

import io.akst.thesis.models.util.Semver


class Average implements Serializable {
  @Column(name="package_id")
  def int packageId

  @Type(type="io.akst.thesis.models.util.SemverType")
  @Column(name="ghc_version")
  def String version

  @Column(name="average_time")
  def BigDecimal averageTime

  @Column(name="total_size")
  def BigDecimal totalSize

  def Average(int id, String version, BigDecimal average, BigDecimal totalSize) {
    this.packageId = id
    this.version = version
    this.averageTime = average
    this.totalSize = totalSize
  }

  def serialise() {
    [
      package_id: this.packageId,
      ghc_version: version.substring(1, version.length() - 1),
      average_time: this.averageTime,
      total_size: this.totalSize
    ]
  }
}
