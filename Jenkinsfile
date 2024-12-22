pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        SONARQUBE_SERVER = 'http://172.19.0.3:9000'
        SONARQUBE_PROJECT_KEY = 'verification'
        SONARQUBE_PROJECT_NAME = 'verification'
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
        
        stage('Unit Tests') {
            steps {
                script {
                    def mvnHome = tool 'maven'
                    sh "${mvnHome}/bin/mvn test"
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    script {
                        def mvnHome = tool 'maven'
                        sh "${mvnHome}/bin/mvn clean verify sonar:sonar " +
                            "-Dsonar.projectKey=${SONARQUBE_PROJECT_KEY} " +
                            "-Dsonar.projectName=${SONARQUBE_PROJECT_NAME} " +
                            "-Dsonar.host.url=${SONARQUBE_SERVER}"
                    }
                }
            }
        }

        stage('Code Quality Check') {
            steps {
                timeout(time: 3, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
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
