/* groovylint-disable JavaIoPackageAccess, LineLength, NoDef, NoWildcardImports, UnnecessaryDotClass, UnnecessaryGetter, UnnecessarySetter, VariableTypeRequired */
import jenkins.model.*
import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule

def instance = Jenkins.getInstance()

def user = System.getenv('JENKINS_USER') ?: 'admin'
def pass = System.getenv('JENKINS_PASSWD') ?: 'admin'

println "Creating user: [$user]"

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount(user, pass)
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
instance.setAuthorizationStrategy(strategy)
instance.save()

Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)

println "Admin user created: [$user]"
