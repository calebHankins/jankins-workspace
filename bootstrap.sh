#!/bin/sh

# Create logs dir if it doesn't exist
mkdir -p ./logs

# Install node dependencies and fix ssh files
echo 'Running npm install... check' `readlink -f ./logs/npmInstall.log` 'for more info'
npm install > ./logs/npmInstall.log 2>&1

# Fix .ssh files so Windows hosts can still use version control tools despite the Win file permission mapping issue
echo 'Running configureSSH... check' `readlink -f ./logs/configureSSH.log` 'for more info'
npm run configureSSH > ./logs/configureSSH.log 2>&1

# Start Jenkins in the background (since we are overriding the normal entrypoint)
JENKINS_ON_BOOT_TRUTHINESS=$(node -e "const yn = require('yn'); yn(process.env.JENKINS_ON_BOOT) ? console.log('true') : console.log('false')")
if $JENKINS_ON_BOOT_TRUTHINESS; then
    echo 'Starting Jenkins in the background (since we are overriding the normal entrypoint)... check' `readlink -f ./logs/jenkins.log` 'for more info'
    /usr/local/bin/jenkins.sh > ./logs/jenkins.log 2>&1 &
fi
