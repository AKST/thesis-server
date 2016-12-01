package io.akst.thesis.models

import java.sql.Timestamp

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.OneToMany
import javax.persistence.FetchType
import javax.persistence.JoinTable
import javax.persistence.JoinColumn
import javax.persistence.GeneratedValue

@Entity
@Table(name="benchmark_script")
@groovy.transform.TypeChecked
class BenchmarkScript implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  byte[] id

  @Column(name="repr")
  String repr

  @Column(name="activity_timestamp")
  def Timestamp last_modified

  @OneToMany()
  @JoinColumn(name="checksum", referencedColumnName="id", insertable=false, updatable=false)
  def Collection<Batch> batches
}

