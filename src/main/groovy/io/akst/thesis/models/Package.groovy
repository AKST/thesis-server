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
@Table(name="package")
class Package implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  def int id

  @Column(name="name")
  def String name

  @OneToMany(fetch=FetchType.LAZY)
  @JoinTable(name="BATCH_PACKAGES",
    joinColumns=@JoinColumn(name="id"),
    inverseJoinColumns=@JoinColumn(name="package"))
  def Collection<Batch> batches
}
