package io.akst.thesis.models

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
class BenchmarkScript implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  byte[] id

  @Column(name="repr")
  String repr

  //@OneToMany(fetch=FetchType.LAZY)
  //@JoinTable(name="batch",
  //  joinColumns=@JoinColumn(name="id"),
  //  inverseJoinColumns=@JoinColumn(name="checksum"))
  //def Collection<Batch> batches
}

