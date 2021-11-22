pipeline{

     agent any

    environment
    {
        IMG_NAME = "user_service"
        AWS_ID = "422288715120"
    }

  tools
  {
            maven 'maven'
            jdk 'java'
  }

  stages
  {
  stage("Package")
      {
            steps
            {
                sh 'mvn clean package'
            }
      }
      stage("Docker Build") {

           steps {
              echo "Docker Build...."
              withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'jenkins_credentials', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                        sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin ${AWS_ID}.dkr.ecr.us-east-1.amazonaws.com"
              }
              sh "docker build -t ${IMG_NAME} ."
               sh "docker tag ${IMG_NAME}:latest ${AWS_ID}.dkr.ecr.us-east-1.amazonaws.com/${IMG_NAME}:latest"
              echo "Docker Push..."
               sh "docker push ${AWS_ID}.dkr.ecr.us-east-1.amazonaws.com/${IMG_NAME}:latest"
          }
          }
    }
  post
  {
          always
          {
              sh 'mvn clean'
          }
  }

}