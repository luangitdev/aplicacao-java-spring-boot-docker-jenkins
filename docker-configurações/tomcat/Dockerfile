FROM tomcat:9.0

# Atualize os pacotes e instale o openssh-server
RUN apt-get update && apt-get install -y openssh-server

# Crie o diretório para o SSH
RUN mkdir /var/run/sshd

# Crie o usuário 'deploy' com uma senha (ex.: 'deploy123')
RUN useradd -m -s /bin/bash deploy && \
    echo 'deploy:q1w2e3r4' | chpasswd

# Permita login SSH para o usuário 'deploy'
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin no/' /etc/ssh/sshd_config && \
    echo "AllowUsers deploy" >> /etc/ssh/sshd_config

# Exponha a porta SSH
EXPOSE 22

# Inicie o SSH como comando padrão
CMD ["/usr/sbin/sshd", "-D"]