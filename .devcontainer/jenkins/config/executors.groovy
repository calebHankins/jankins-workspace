/* groovylint-disable NoWildcardImports, UnnecessarySetter */
import jenkins.model.*
Integer executors = System.getenv('JENKINS_EXECUTORS').toInteger() ?: 1
Jenkins.instance.setNumExecutors(executors)
println "Jenkins Executor Count: [$executors]"
