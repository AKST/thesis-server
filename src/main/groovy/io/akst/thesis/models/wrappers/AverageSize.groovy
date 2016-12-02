package io.akst.thesis.models.wrappers

import java.math.BigDecimal

import javax.persistence.Column

import org.hibernate.annotations.Type

import io.akst.thesis.models.util.Semver
import io.akst.thesis.models.util.ProjectionWrapper

@groovy.transform.TypeChecked
class AverageSize implements Serializable, ProjectionWrapper {
  def int packageId

  def Semver version

  def BigDecimal averageSize

  def String fileExtention

  def AverageSize(int id, Semver version, String fileExtention, BigDecimal average) {
    this.packageId = id
    this.version = version
    this.fileExtention = fileExtention
    this.averageSize = average
  }

  def Map serialise() {
    return [
      id: "${this.packageId}-${this.version}-size:${this.fileExtention}",
      data: [
        'package-id': this.packageId,
        'file-extension': this.fileExtention,
        'average-size': this.averageSize,
        'ghc-version': this.version,
      ]
    ]
  }
}
