package io.akst.thesis.models


import java.math.BigDecimal
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.FetchType
import javax.persistence.JoinTable
import javax.persistence.JoinColumn
import javax.persistence.GeneratedValue


@Entity
@Table(name="file_output")
class FileOutput implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  def int id

  @Column(name="result")
  def int resultId

  @Column(name="relative_path")
  def String relativePath

  @Column(name="file_extension")
  def String fileExtension

  @Column(name="file_size")
  def BigDecimal fileSize

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinTable(name="OUTPUT_RESULT",
    joinColumns=@JoinColumn(name="result"),
    inverseJoinColumns=@JoinColumn(name="id"))
  def Result result
}
