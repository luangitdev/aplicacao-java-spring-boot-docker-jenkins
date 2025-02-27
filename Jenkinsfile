pipeline {
    agent any

    environment {
        APP_NAME = 'ola-mundo'
        TOMCAT_SERVER = '172.17.0.2'
        TOMCAT_PORT = '9090'
        WAR_FILE = 'target/ola-mundo.war' // Caminho do .war gerado pelo build
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Obtendo o código-fonte do repositório"
                git branch: 'main', url: 'https://github.com/luangitdev/aplicacao-java-spring-boot-docker-jenkins.git'
            }
        }

        stage('Build') {
            steps {
                echo "Gerando o artefato (.war)"
                sh 'mvn clean package'
            }
        }

        stage('Deploy no Tomcat') {
            steps {
                echo "Copiando o .war para o servidor Tomcat via SSH"
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'Servidor-Tomcat', // Configuração SSH no Jenkins
                            transfers: [
                                sshTransfer(
                                    sourceFiles: "${WAR_FILE}",
                                    remoteDirectory: "${TOMCAT_VOLUME}/webapps"
                                )
                            ],
                            usePromotionTimestamp: false,
                            verbose: true
                        )
                    ]
                )
            }
        }
    }

    post {
        success {
            echo "Deploy concluído com sucesso!"
        }
        failure {
            echo "Ocorreu um erro durante o deploy."
        }
    }
}