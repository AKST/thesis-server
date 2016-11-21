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
@Table(name="batch")
class Batch implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  def UUID id

  @Column(name="package")
  def int packageId

  @Column(name="start_time")
  def Timestamp startTime

  @Column(name="checksum")
  def byte[] checksum

  //@ManyToOne(fetch=FetchType.LAZY)
  //@JoinTable(name="benchmark_script",
  //  joinColumns=@JoinColumn(name="checksum"),
  //  inverseJoinColumns=@JoinColumn(name="id"))
  //def BenchmarkScript script

  //@ManyToOne(fetch=FetchType.LAZY)
  //@JoinTable(name="package",
  //  joinColumns=@JoinColumn(name="package"),
  //  inverseJoinColumns=@JoinColumn(name="id"))
  //def Package parentPackage

  //@OneToMany(fetch=FetchType.LAZY)
  //@JoinTable(name="result",
  //  joinColumns=@JoinColumn(name="id"),
  //  inverseJoinColumns=@JoinColumn(name="batch"))
  //def Collection<Result> batches
}

