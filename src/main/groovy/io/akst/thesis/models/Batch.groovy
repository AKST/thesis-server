package io.akst.thesis.models

import java.util.UUID
import java.sql.Timestamp

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.GeneratedValue

@Entity
@Table(name="batch")
class Batch implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  UUID id

  @Column(name="package")
  int package_

  @Column(name="start_time")
  Timestamp startTime

  @Column(name="checksum")
  byte[] checksum
}

