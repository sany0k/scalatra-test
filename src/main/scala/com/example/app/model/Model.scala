package com.example.app.model

import org.neo4j.graphdb.{Node}
import org.neo4j.scala.{SingletonEmbeddedGraphDatabaseServiceProvider, Neo4jWrapper}
import com.typesafe.config.ConfigFactory

class Model {

}

object Model extends Model with Neo4jWrapper with SingletonEmbeddedGraphDatabaseServiceProvider {

  def getAll: Iterable[Node] = withTx {
    implicit neo =>
      getAllNodes
  }

  def neo4jStoreDir: String = ConfigFactory.load().getString("neo-db-path")
}
