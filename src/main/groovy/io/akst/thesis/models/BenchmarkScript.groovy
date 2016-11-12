package io.akst.thesis.models

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.GeneratedValue

@Entity
@Table(name="benchmark_script")
class BenchmarkScript implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  byte[] id

  @Column(name="repr")
  String repr
}

