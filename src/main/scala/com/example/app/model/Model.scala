package com.example.app.model

import org.neo4j.graphdb.{Node}
import org.neo4j.scala.{SingletonEmbeddedGraphDatabaseServiceProvider, Neo4jWrapper}

class Model {

}

object Model extends Model with Neo4jWrapper with SingletonEmbeddedGraphDatabaseServiceProvider {

  def getAll: Iterable[Node] = withTx {
    implicit neo =>
      getAllNodes
  }

  def neo4jStoreDir: String = "C:\\Development\\neo4j-community-1.9.RC1\\data\\graph.db"
}
