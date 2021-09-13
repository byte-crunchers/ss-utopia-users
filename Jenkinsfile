pipeline {
    agent any

    stages {
      stage('checkout') {
        steps {
          git branch: 'feature_jenkins', credentialsId: 'git_login', url: 'https://github.com/byte-crunchers/ss-utopia-users.git'
        }
      }
        
        stage("SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('SonarQube') {
                dir('user') {
                  sh 'mvn clean package sonar:sonar'
                }
              }
            }
          }
          stage("Quality Gate") {
            steps {
              echo message: "can not do on local machine "
             /* timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }*/
            }
          }
          stage('Build') {
            steps {
                  dir('user') {
                    sh 'docker build . -t jbnilles/ss-utopia-users:latest'

                  }

                 
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker push jbnilles/ss-utopia-users:latest'
            }
        }
    }

}