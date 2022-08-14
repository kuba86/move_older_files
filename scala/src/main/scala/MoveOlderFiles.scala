package com.kuba86

import scala.concurrent.duration._
import scala.util.Try
import os._
import pprint.{pprintln => println}
import caseapp._

@AppName("Move Older Files")
@AppVersion("0.0.1")
@ProgName("move_older_files")
case class CliOptions(
  @HelpMessage("How old a file should be to be, moved to a backup directory")
  older_than_in_days: Int,
  @HelpMessage("Path to where the files will be scanned and moved from")
  source: String,
  @HelpMessage(
    "Path where files will be moved to (where you would like to backup the files)"
  )
  destination: String
)

object MoveOlderFiles extends CaseApp[CliOptions] {

  def run(options: CliOptions, arg: RemainingArgs): Unit = {
    println(options)
    val older_than_in_days = options.older_than_in_days
    val source = options.source
    val destination = options.destination

    val now = System.currentTimeMillis()
    val listOfPaths = walk.attrs(
      path = Path(source),
      skip = { (path, stats) =>
        val fileModifiedTimestamp = stats.mtime.toMillis
        val createdDaysAgo =
          Duration(now - fileModifiedTimestamp, MILLISECONDS).toDays
        val isDotFile = path.wrapped.getFileName.toString.startsWith(".")
        (stats.isDir && isDotFile) || (stats.isFile && createdDaysAgo < older_than_in_days)
      }
    )

    val listOfFiles = listOfPaths.filter(x => x._2.isFile)

    val operationsResult: Seq[Try[Unit]] = listOfFiles.map(
      x =>
        Try(
          move(
            from = x._1,
            to = Path(destination) / x._1.relativeTo(Path(source)),
            replaceExisting = false,
            createFolders = true
          )
      )
    )
    // print only errors to console
    println(operationsResult.filter(_.isFailure))
  }
}
