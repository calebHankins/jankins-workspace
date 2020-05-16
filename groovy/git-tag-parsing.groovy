#!/usr/bin/env groovy

// def sampleString = ''
StringBuilder sOut = new StringBuilder(), sErr = new StringBuilder()
String cmd = 'git describe --tags'
// String cmd = 'echo 0.0.1'
Process proc = cmd .execute()
proc.consumeProcessOutput(sOut, sErr)
proc.waitForOrKill(1000)
println "out> $sOut err> $sErr"
String sOutString = sOut // convert from StringBuilder to String
println "sOutString: $sOutString"
if (sOut) {
    // println sOutString.split()
    // String justVersion = sOutSplit[0]
    String[] sOutSplit = sOut.toString().split('-')
    String justVersion = sOutSplit[0]
    // def range = (1..-1)
    String everythingElse = 'default'
    if (sOutSplit.size() > 1) {
        everythingElse = sOutSplit[1..-1].join('-')
    }
    println "Just Version: $justVersion Everything Else: $everythingElse"
}
