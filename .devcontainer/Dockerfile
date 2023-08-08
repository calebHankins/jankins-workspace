ARG BASE_IMAGE=ghcr.io/calebhankins/jankins
FROM $BASE_IMAGE
ARG BASE_IMAGE=ghcr.io/calebhankins/jankins
ENV BASE_IMAGE=${BASE_IMAGE}
RUN echo "BASE_IMAGE:${BASE_IMAGE}"

USER root
# Persist command history through container rebuilds
RUN SNIPPET="export PROMPT_COMMAND='history -a' && export HISTFILE=/command_history/.bash_history" \
    && echo $SNIPPET >> "/root/.bashrc" \
    && mkdir -p /command_history \
    && touch /command_history/.bash_history

# Trust self-signed certs in the chain for schemastore.azurewebsites.net:443 for intellisense
# Comment this out for non-corporate envs where you might have MitM attacks from IP loss prevention software like Netskope
# @See: https://en.wikipedia.org/wiki/Netskope
# If you need this kind of mitigation at home on personal hardware, someone might be doing a legit MitM attack against you
# @see: https://en.wikipedia.org/wiki/Man-in-the-middle_attack
# Debian version
RUN openssl s_client -showcerts  -connect schemastore.azurewebsites.net:443 2>&1 < /dev/null |\
 sed -n '/-----BEGIN/,/-----END/p' >\
 /usr/local/share/ca-certificates/schemastore.azurewebsites.net.crt \
 && chmod 644 /usr/local/share/ca-certificates/*.crt \
 && update-ca-certificates --verbose

# Tell node to trust the self-signed netskope cert we just pulled down
ENV NODE_EXTRA_CA_CERTS /usr/local/share/ca-certificates/schemastore.azurewebsites.net.crt

# Install dos2Unix in case we get a user that checked out CRLF line endings
RUN apt-get update && apt-get install -y dos2unix

# Set whether we boot jenkins in the background as part of the container bootstrap
ENV JENKINS_ON_BOOT=true

# Persist command history through container rebuilds
RUN SNIPPET="export PROMPT_COMMAND='history -a' && export HISTFILE=/var/jenkins_home/.bash_history" \
    && echo $SNIPPET >> "/var/jenkins_home/.bashrc" \
    && mkdir -p /var/jenkins_home/command_history \
    && touch /var/jenkins_home/.bash_history

USER root

# Move the og jenkins .gitconfig and replace it with ours so our host's tooling will work
RUN mv /root/.gitconfig /root/.gitconfig.og

# Install Release Management Tooling Globally (if npm is available)
RUN if [ -x "$(command -v npm)" ]; then npm install -g release-it; fi

# Install gh cli tooling
RUN type -p curl >/dev/null || (apt update && apt install curl -y)
RUN curl -fsSL https://cli.github.com/packages/githubcli-archive-keyring.gpg | dd of=/usr/share/keyrings/githubcli-archive-keyring.gpg \
&& chmod go+r /usr/share/keyrings/githubcli-archive-keyring.gpg \
&& echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/githubcli-archive-keyring.gpg] https://cli.github.com/packages stable main" | tee /etc/apt/sources.list.d/github-cli.list > /dev/null \
&& apt update \
&& apt install gh -y
RUN apt update
RUN apt install gh

ENTRYPOINT [ "bash" ]
LABEL name=jankins-workspace maintainer="Caleb Hankins <caleb.hankins@acxiom.com>"
