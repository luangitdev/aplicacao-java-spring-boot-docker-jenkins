pipeline {
    agent any

    tools {
        maven 'Maven' // Use o Maven configurado no Jenkins
    }

    environment {
        APP_NAME = 'ola-mundo'
        WAR_FILE = "target/${APP_NAME}-1.0-SNAPSHOT.war" // Caminho do arquivo gerado pelo Maven
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
                            configName: 'servidor-tomcat', // Configuração SSH no Jenkins
                            transfers: [
                                sshTransfer(
                                    sourceFiles: "${WAR_FILE}",
                                    remoteDirectory: "", // Vazio pois ja defini no remoteDirectory do Publish Over SSH
                                    removePrefix: "target" // Evita que o arquivo vá para uma subpasta
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