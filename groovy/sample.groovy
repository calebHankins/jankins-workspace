#!/usr/bin/env groovy

// Shout out to Better Comments https://marketplace.visualstudio.com/items?itemName=aaron-bond.better-comments
// This is a regular comment
// ! This is an important comment
// * This is a highlighted comment
// ? Is this a question comment?
// TODO: write a todo comment
// // This is a double comment

String salutation = 'Hello'
String who = returnTrimmedCommandOutput('whoami')
String time = returnTrimmedCommandOutput('date')

println "${salutation}, $who! It is ${time} 👋"

String returnTrimmedCommandOutput (String cmd) {
    StringBuilder sOut = new StringBuilder(), sErr = new StringBuilder()
    Process proc = cmd .execute()
    proc.consumeProcessOutput(sOut, sErr)
    proc.waitForOrKill(1000)
    String trimmedOut = sOut.toString().trim()
    return trimmedOut
}
