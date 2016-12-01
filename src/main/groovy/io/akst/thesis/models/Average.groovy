package io.akst.thesis.models

import java.math.BigDecimal

import javax.persistence.Column

import org.hibernate.annotations.Type

import io.akst.thesis.models.util.Semver


class Average implements Serializable {
  def int packageId

  def String version

  def BigDecimal averageTime

  def BigDecimal totalSize

  def Average(int id, String version, BigDecimal average, BigDecimal totalSize) {
    this.packageId = id
    this.version = version
    this.averageTime = average
    this.totalSize = totalSize
  }

  def serialise() {
    def version = this.version
      .substring(1, version.length() - 1)
      .replaceAll(',', '.')

    // I just prefer explict return, with
    // datastructure literal like this...
    return [
      id: "${this.packageId}-${version}",
      data: [
        package_id: this.packageId,
        ghc_version: version,
        average_time: this.averageTime,
        total_size: this.totalSize
      ]
    ]
  }
}
