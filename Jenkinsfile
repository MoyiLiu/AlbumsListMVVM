pipeline {
    agent any
    stages {
        stage('Unit test') {
            script {
                sh './gradlew test'
            }
        }
        stage('Cleanup') {
            script {
                sh './gradlew clean'
            }
        }
    }
}
