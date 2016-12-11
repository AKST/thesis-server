package io.akst.thesis.models

import java.util.UUID
import java.sql.Timestamp

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.OneToMany
import javax.persistence.ManyToOne
import javax.persistence.JoinTable
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.GeneratedValue

@Entity
@Table(name="thesis.batch")
@groovy.transform.TypeChecked
class Batch implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  def UUID id

  @Column(name="package")
  def int packageId

  @Column(name="start_time")
  def Timestamp startTime

  @Column(name="checksum")
  def byte[] scriptChecksum

  @Column(name="activity_timestamp")
  def Timestamp last_modified

  // relations

  @ManyToOne(fetch=FetchType.LAZY, optional=true)
  @JoinColumn(name="checksum", referencedColumnName="id", insertable=false, updatable=false)
  def BenchmarkScript script

  @ManyToOne(fetch=FetchType.LAZY, optional=true)
  @JoinColumn(name="package", referencedColumnName="id", insertable=false, updatable=false)
  def Package parentPackage

  @OneToMany(fetch=FetchType.LAZY)
  @JoinColumn(name="batch", referencedColumnName="id", insertable=false, updatable=false)
  def Collection<Result> results
}

