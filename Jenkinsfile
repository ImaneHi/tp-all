pipeline {
    agent any

    
    environment {
        SONARQUBE_SERVER = 'SonarQube'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ImaneHi/tp-all.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Tests unitaires') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                    mvn sonar:sonar \
                      -Dsonar.projectKey=todo-app \
                      -Dsonar.projectName=todo-app
                    '''
                }
            }
        }



        stage('Build Docker') {
            steps {
                sh 'docker build -t todo-app:latest .'
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeded'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}
