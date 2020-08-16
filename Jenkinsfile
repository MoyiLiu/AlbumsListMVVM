pipeline {
    agent any
    stages {
        stage('Unit test') {
            sh './gradlew test'
        }
        stage('Cleanup') {
            sh './gradlew clean'
        }
    }
}
