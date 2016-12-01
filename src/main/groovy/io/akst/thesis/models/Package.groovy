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
import javax.persistence.NamedQuery
import javax.persistence.NamedQueries
import javax.persistence.GeneratedValue

import org.hibernate.annotations.Type

import io.akst.thesis.models.util.Semver


@Entity
@Table(name="package")
@NamedQueries([
  @NamedQuery(name="Package.findAll", query = "SELECT p FROM Package p"),
  @NamedQuery(name="Package.find", query = "SELECT p FROM Package p WHERE p.id = :id"),
])
@groovy.transform.TypeChecked
class Package implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  def int id

  @Column(name="name")
  def String name

  @Column(name="commit_hash")
  def String hash

  @Column(name="repo_url")
  def String repository_url

  @Column(name="min_ghc")
  @Type(type="io.akst.thesis.models.util.SemverType")
  def Semver min_ghc_compat

  @Column(name="max_ghc")
  @Type(type="io.akst.thesis.models.util.SemverType")
  def Semver max_ghc_compat

  @Column(name="activity_timestamp")
  def Timestamp last_modified

  @OneToMany(fetch=FetchType.LAZY)
  @JoinColumn(name="package", referencedColumnName="id", insertable=false, updatable=false)
  def Collection<Batch> batches

  def serialise() {
    return [
      id: this.id,
      meta: [last_modified: this.last_modified],
      data: [
        name: this.name,
        'source-control': [
          type: 'git',
          commit_hash: this.hash,
          repository_url: this.repository_url
        ],
        'ghc-compatibility': [
          min: this.min_ghc_compat,
          max: this.max_ghc_compat,
        ]
      ]
    ]
  }
}
