node {
    stages {
        stage('Unit test') {
            sh './gradlew test'
        }
        stage('Cleanup') {
            sh './gradlew clean'
        }
    }
}