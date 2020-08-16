pipeline {
    agent any
    stages {
        stage('Unit test') {
            steps {
                script {
                    sh './gradlew test'
                }
            }
        }
        stage('Cleanup') {
            steps {
                cleanWs()
            }
        }
    }
}
