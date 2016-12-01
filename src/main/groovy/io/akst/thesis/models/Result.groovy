package io.akst.thesis.models

import java.util.UUID
import java.math.BigDecimal
import java.sql.Timestamp

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.OneToMany
import javax.persistence.ManyToOne
import javax.persistence.FetchType
import javax.persistence.JoinTable
import javax.persistence.JoinColumn
import javax.persistence.GeneratedValue

import org.hibernate.annotations.Type

import io.akst.thesis.models.util.Semver


@Entity
@Table(name="result")
@groovy.transform.TypeChecked
class Result implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  def int id

  @Column(name="batch")
  def UUID batchId

  @Column(name="version")
  @Type(type="io.akst.thesis.models.util.SemverType")
  def Semver version

  @Column(name="seconds")
  def BigDecimal seconds

  @Column(name="activity_timestamp")
  def Timestamp last_modified

  @ManyToOne(fetch=FetchType.LAZY, optional=true)
  //@JoinColumn(name="batch", referencedColumnName="id", insertable=false, updatable=false)
  def Batch batch

  @OneToMany(fetch=FetchType.LAZY)
  @JoinColumn(name="result", referencedColumnName="id", insertable=false, updatable=false)
  def Collection<FileOutput> outputs
}
