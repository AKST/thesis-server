package io.akst.thesis.models

import java.util.UUID
import java.math.BigDecimal

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

  //@ManyToOne(fetch=FetchType.LAZY)
  //@JoinTable(name="batch",
  //  joinColumns=@JoinColumn(name="batch"),
  //  inverseJoinColumns=@JoinColumn(name="id"))
  //def Batch batch

  //@OneToMany(fetch=FetchType.LAZY)
  //@JoinTable(name="file_output",
  //  joinColumns=@JoinColumn(name="id"),
  //  inverseJoinColumns=@JoinColumn(name="result"))
  //def Collection<FileOutput> outputs
}
