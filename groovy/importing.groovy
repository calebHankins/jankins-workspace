#!/usr/bin/env groovy

// @see: https://code-maven.com/groovy-import-functions-from-another-file
GroovyShell shell = new GroovyShell()
/* groovylint-disable-next-line JavaIoPackageAccess, NoDef, VariableTypeRequired */
def hello = shell.parse(new File('hello.groovy'))

println hello()
