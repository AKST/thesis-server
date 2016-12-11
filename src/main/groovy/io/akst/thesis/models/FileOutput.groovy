package io.akst.thesis.models


import java.math.BigDecimal
import java.sql.Timestamp

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.FetchType
import javax.persistence.JoinTable
import javax.persistence.JoinColumn
import javax.persistence.NamedQuery
import javax.persistence.ColumnResult
import javax.persistence.NamedQueries
import javax.persistence.GeneratedValue
import javax.persistence.NamedNativeQuery
import javax.persistence.ConstructorResult
import javax.persistence.SqlResultSetMapping

import io.akst.thesis.models.util.Semver


@Entity
@Table(name="thesis.file_output")
@groovy.transform.TypeChecked
class FileOutput implements Serializable {
  @Id @GeneratedValue @Column(name="id", unique = true)
  def int id

  @Column(name="result")
  def int resultId

  @Column(name="relative_path")
  def String relativePath

  @Column(name="file_extension")
  def String fileExtension

  @Column(name="file_size")
  def BigDecimal fileSize

  @Column(name="activity_timestamp")
  def Timestamp activity_timestamp

  @ManyToOne(fetch=FetchType.LAZY, optional=true)
  @JoinColumn(name="result", referencedColumnName="id", insertable=false, updatable=false)
  def Result result
}
