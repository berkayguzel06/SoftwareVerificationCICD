pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('SCM Checkout') {
            steps {
                // Fetch code from source control
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'maven'
                    sh "${mvnHome}/bin/mvn clean package"
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    def mvnHome = tool 'maven'
                    sh "${mvnHome}/bin/mvn package"
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying to the target environment..."
            }
        }
    }

    post {
        failure {
            echo "Pipeline failed!"
        }
        success {
            echo "Pipeline completed successfully!"
        }
    }
}
