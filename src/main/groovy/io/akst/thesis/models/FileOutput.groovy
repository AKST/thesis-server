package io.akst.thesis.models

import java.util.UUID
import java.math.BigDecimal

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.GeneratedValue


@Entity
@Table(name="file_output")
class FileOutput implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  int id

  @Column(name="result")
  int result

  @Column(name="relative_path")
  String relativePath

  @Column(name="file_extension")
  String fileExtension

  @Column(name="file_size")
  BigDecimal fileSize
}
